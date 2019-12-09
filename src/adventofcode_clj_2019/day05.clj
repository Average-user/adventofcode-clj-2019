(ns adventofcode-clj-2019.day05
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]
            [clojure.core.match :refer [match]]))

(defn parse-opcode [n]
  (vec (reverse (take 5 (-> n u/digits reverse (concat (repeat 0)))))))

(defn mode [base tape k]
  (case k
    0 (fn [i] (get tape (get tape i) 0))
    1 (fn [i] (get tape i 0))
    2 (fn [i] (get tape (+ base (get tape i 0)) 0))))

(defn mode' [base tape k]
  (case k
    0 (fn [i] (get tape i 0))
    1 (fn [i] i)
    2 (fn [i] (+ base (get tape i 0)))))

(defn mkop [tape i op f1 f2 f3]
  (assoc tape (f3 (+ i 3))
    (op (f1 (+ i 1)) (f2 (+ i 2)))))

(defn newi [tape i f0 f1 f2]
  (if (f0 (f1 (inc i))) (f2 (+ i 2)) (+ i 3)))

(defn setv [tape i f0 f1 f2 f3]
  (assoc tape (f3 (+ i 3))
         (if (f0 (f1 (+ i 1)) (f2 (+ i 2))) 1 0)))

(defn execute [input tape]
  (loop [tape tape, i 0, out [], in input, base 0]
    (let [m (partial mode base tape)
          m' (partial mode' base tape)]
      (match (parse-opcode (tape i))
        [_,_,_,9,9] out
        [a,b,c,0,1] (recur (mkop tape i + (m c) (m b) (m' a)) (+ i 4) out in base)
        [a,b,c,0,2] (recur (mkop tape i * (m c) (m b) (m' a)) (+ i 4) out in base)
        [a,b,c,0,3] (recur (assoc tape ((m' c) (inc i)) (first in)) (+ i 2) out (rest in) base)
        [a,b,c,0,4] (recur tape (+ i 2) (conj out ((m c) (inc i))) in base)
        [a,b,c,0,5] (recur tape (newi tape i #(not= 0 %) (m c) (m b)) out in base)
        [a,b,c,0,6] (recur tape (newi tape i zero? (m c) (m b)) out in base)
        [a,b,c,0,7] (recur (setv tape i < (m c) (m b) (m' a)) (+ i 4) out in base)
        [a,b,c,0,8] (recur (setv tape i = (m c) (m b) (m' a)) (+ i 4) out in base)
        [a,b,c,0,9] (recur tape (+ i 2) out in (+ base ((m c) (inc i))))))))

(defn run-opcodes [input]
  (->> (cs/split (u/input 5) #",")
       (mapv #(Integer/parseInt (cs/trim %)))
       (execute input)))

(defn part-1 [] (peek (run-opcodes [1])))

(defn part-2 [] (peek (run-opcodes [5])))
