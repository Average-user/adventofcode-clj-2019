(ns adventofcode-clj-2019.day07
  (:require [adventofcode-clj-2019.util :as u]
            [adventofcode-clj-2019.day05 :as d5]
            [clojure.string :as cs]
            [clojure.math.combinatorics :as cm]
            [clojure.core.async :as as]
            [clojure.core.match :refer [match]]))

(def program
  (mapv u/parse-int (cs/split (u/input 7) #",")))

(defn parse-opcode [n]
  (vec (reverse (take 4 (-> n u/digits reverse (concat (repeat 0)))))))

(defn run [program phases]
  (last (reduce (fn [out p] (d5/execute (cons p out) program))
                [0] phases)))
           
(defn part-1 []
  (apply max (map (partial run program) (cm/permutations [0 1 2 3 4]))))

(defn execute' [tape input output]
  (letfn [(mkop [tape i op f1 f2] (assoc tape (tape (+ i 3))
                                    (op (f1 (+ i 1)) (f2 (+ i 2)))))
          (newi [tape i f0 f1 f2]
            (if (f0 (f1 (inc i))) (f2 (+ i 2)) (+ i 3)))
          (setv [tape i f0 f1 f2]
            (assoc tape (tape (+ i 3)) (if (f0 (f1 (+ i 1)) (f2 (+ i 2))) 1 0)))]
    (as/go-loop [tape tape, i 0]
      (let [m (partial d5/mode 0 tape)]
        (match (parse-opcode (get tape i))
          [_,_,9,9] :exit
          [a,b,0,1] (recur (mkop tape i + (m b) (m a)) (+ i 4))
          [a,b,0,2] (recur (mkop tape i * (m b) (m a)) (+ i 4))
          [_,_,0,3] (recur (assoc tape (tape (inc i)) (as/<! input)) (+ i 2))
          [_,_,0,4] (do (as/>! output ((comp tape tape) (inc i)))
                        (recur tape (+ i 2)))
          [a,b,0,5] (recur tape (newi tape i #(not= 0 %) (m b) (m a)))
          [a,b,0,6] (recur tape (newi tape i zero? (m b) (m a)))
          [a,b,0,7] (recur (setv tape i < (m b) (m a)) (+ i 4))
          [a,b,0,8] (recur (setv tape i = (m b) (m a)) (+ i 4)))))))

(defn run2 [a b c d e]
  (let [[ca cb cc cd ce] (repeatedly as/chan)]
    (doall (map (partial apply as/put!)
                [[ce a] [ce 0] [ca b] [cb c] [cc d] [cd e]]))
    (doall (map (partial apply execute' program)
                [[ce ca] [ca cb] [cb cc] [cc cd] [cd ce]]))
    ce))

(def outputs
  (as/merge (doall (map (partial apply run2)
                        (cm/permutations (range 5 10))))
            120))

(defn part-2 []
  (Thread/sleep 100)
  (as/close! outputs)
  (apply max (as/<!! (as/into [] outputs))))
