(ns adventofcode-clj-2019.day03
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]
            [clojure.set :as sets]))

(def move
  {\L (fn [[x y] n] [(- x n) y])
   \R (fn [[x y] n] [(+ x n) y])
   \U (fn [[x y] n] [x (+ y n)])
   \D (fn [[x y] n] [x (- y n)])})

(defn path-coordinates [instructions]
  (loop [[x y] [0 0], [i & is] instructions, cs []]
    (if (nil? i)
      cs
      (let [n   (u/parse-int (apply str (rest i)))
            f   (move (first i))
            ncs (map #(f [x y] %) (range 1 (inc n)))]
        (recur (f [x y] n) is (reduce conj cs ncs))))))

(def paths
  (->> (cs/split (u/input 3) #"\n")
       (map (comp path-coordinates #(cs/split % #",")))))

(def intersections
  (->> paths
       (map set)
       (reduce sets/intersection)))  

(defn part-1 []
  (->> intersections
       (map #(apply + (map (fn [x] (Math/abs x)) %)))
       (apply min)))

(defn part-2 []
  (letfn [(d [path coord] (inc (count (take-while #(not= coord %) path))))
          (total-d [coord] (apply + (map #(d % coord) paths)))]
    (->> intersections
         (map total-d)
         (apply min))))
  
