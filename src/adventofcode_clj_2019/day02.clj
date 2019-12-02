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

(def enum-pairs
  (letfn [(pairs [i] (map (fn [j] [j (- i j)]) (range (inc i))))]
    (mapcat pairs (range))))

(defn part-2 []
  (let [tape  (mapv u/parse-int(cs/split (u/input 2) #","))
        f     (fn [[x y]] (+ (* 100 x) y))
        check (fn [[x y]] (= 19690720 (first (execute (assoc tape 1 x 2 y) 0))))]
    (->> enum-pairs
         (filter check)
         (first)
         (f))))
