(ns adventofcode-clj-2019.day05
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]
            [clojure.core.match :refer [match]]))

(defn parse-opcode [n]
  (vec (reverse (take 4 (-> n u/digits reverse (concat (repeat 0)))))))

(defn mode [tape i]
  (case i
    0 (comp tape tape)
    1 tape))

(defn execute [input tape]
  (letfn [(mkop [tape i op f1 f2] (assoc tape (tape (+ i 3))
                                    (op (f1 (+ i 1)) (f2 (+ i 2)))))
          (newi [tape i f0 f1 f2]
            (if (f0 (f1 (inc i))) (f2 (+ i 2)) (+ i 3)))
          (setv [tape i f0 f1 f2]
            (assoc tape (tape (+ i 3)) (if (f0 (f1 (+ i 1)) (f2 (+ i 2))) 1 0)))]
    (loop [tape tape, i 0, out [], in input]
      (let [m (partial mode tape)]
        (match (parse-opcode (get tape i))
          [_,_,9,9] out
          [a,b,0,1] (recur (mkop tape i + (m b) (m a)) (+ i 4) out in)
          [a,b,0,2] (recur (mkop tape i * (m b) (m a)) (+ i 4) out in)
          [_,_,0,3] (recur (assoc tape (tape (inc i)) (first in)) (+ i 2) out (rest in))
          [_,_,0,4] (recur tape (+ i 2) (conj out ((comp tape tape) (inc i))) in)
          [a,b,0,5] (recur tape (newi tape i #(not= 0 %) (m b) (m a)) out in)
          [a,b,0,6] (recur tape (newi tape i zero? (m b) (m a)) out in)
          [a,b,0,7] (recur (setv tape i < (m b) (m a)) (+ i 4) out in)
          [a,b,0,8] (recur (setv tape i = (m b) (m a)) (+ i 4) out in))))))

(defn run-opcodes [input]
  (->> (cs/split (u/input 5) #",")
       (mapv #(Integer/parseInt (cs/trim %)))
       (execute input)))

(defn part-1 [] (peek (run-opcodes [1])))

(defn part-2 [] (peek (run-opcodes [5])))
