(ns adventofcode-clj-2019.day06
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]))

(def pairs
  (into {} (->> (cs/split (u/input 6) #"\n")
                (map (comp (fn [[x y]] [y x]) #(cs/split % #"\)"))))))
               
(defn greater-than [n V]
  (take-while identity (rest (iterate V n))))

(defn part-1 []
  (->> (set (apply concat pairs))
       (map #(count (greater-than % pairs)))
       (reduce +)))

(defn part-2 []
  (let [p1    (greater-than "YOU" pairs)
        p2    (greater-than "SAN" pairs)
        is    (clojure.set/intersection (set p1) (set p2))
        i     (first (filter is p1))]
    (+ (count (take-while #(not= i %) p1))
       (count (take-while #(not= i %) p2)))))
