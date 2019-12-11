(ns adventofcode-clj-2019.day10
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]))

(def points
  (let [in (mapv vec (cs/split (u/input 10) #"\n"))
        h  (count in)
        w  (count (first in))]
    (set (for [x (range h) y (range w) :when (= \# (get-in in [y x]))]
           [x y]))))     

(defn distance[[x1 y1] [x2 y2]]
  (let [[a b] [(- x1 x2) (- y1 y2)]]
    (Math/sqrt (+ (* a a) (* b b)))))

(defn angles [points p]
  (letfn [(a [[x1 y1] [x2 y2]]
            (let [alpha (-> (Math/atan2 (- y2 y1) (- x2 x1)) (+ (/ Math/PI 2)))]
              (if (neg? alpha) (+ (* 2 Math/PI) alpha) alpha)))]
    (group-by (partial a p) (disj points p))))

(def best-coord
  (->> points
       (map (juxt identity (comp count (partial angles points))))
       (apply max-key second)))

(defn part-1 [] (second best-coord))

(defn part-2 []
  (let [encode (fn [[x y]] (+ (* 100 x) y))
        laser (first best-coord) 
        step   (fn [[c cs]] (let [[p & ps] (peek cs)] 
                              [p (if (empty? ps) (pop cs) (conj (pop cs) ps))]))]
    (->> (angles points laser)
         (sort-by key <)
         (map second)
         (map #(sort-by (partial distance laser) < %))
         (reduce conj clojure.lang.PersistentQueue/EMPTY)
         (conj [nil])
         (iterate step)
         (map first)
         (drop 200)
         (first)
         (encode))))
