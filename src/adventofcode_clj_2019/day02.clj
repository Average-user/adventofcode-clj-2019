(ns adventofcode-clj-2019.day02
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]))

(defn execute [tape i]
  (letfn [(f [tape i op] (assoc tape (tape (+ i 3))
                                (op (tape (tape (+ i 1)))
                                    (tape (tape (+ i 2))))))]
    (case (get tape i)
      99 tape
      1  (recur (f tape i +) (+ i 4))
      2  (recur (f tape i *) (+ i 4)))))
              
(defn part-1 []
  (-> (mapv u/parse-int(cs/split (u/input 2) #","))
      (assoc 1 12 2 2)
      (execute 0)
      (first)))

(defn part-2 []
  (let [tape   (mapv u/parse-int (cs/split (u/input 2) #","))
        check  (fn [x y] (= 19690720 (first (execute (assoc tape 1 x 2 y) 0))))]
    (first (for [x (range 99), y (range 99) :when (check x y)]
             (+ (* 100 x) y)))))
