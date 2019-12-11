(ns adventofcode-clj-2019.day11
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]
            [adventofcode-clj-2019.day05 :as d5]
            [clojure.core.match :refer [match]]))

(def turn-right (into {} (map vec (partition 2 1 [:north :east :south :west :north]))))
(def turn-left (comp turn-right turn-right turn-right))
(def intcode (into {} (map-indexed vector (mapv read-string (cs/split (u/input 11) #",")))))

(defn move [facing [x y]]
  (case facing
    :north [x (inc y)]
    :south [x (dec y)]
    :east  [(inc x) y]
    :west  [(dec x) y]))

(defn run-until-out [tape i in b]
  (let [f #(and (not= :finished (first %))
                (> 2 (count (get % 2))))]              
    (first (drop-while f (iterate (partial apply d5/step) [tape i [] in b])))))
  
(defn robot-painting [program grid]
  (loop [[x y] [0 0], grid grid, dir :north, program program, i 0, b 0]
    (let [color (get grid [x y] 0)]
      (match (run-until-out program i (repeat color) b)
        [:finished out] (if-not (empty? out) (conj grid [[x y] (first out)]) grid)
        [program' i' [newc newd] _ b']    
        (let [newdir (case newd 0 (turn-left dir) 1 (turn-right dir))
              newcoord  (move newdir [x y])]
          (recur newcoord (conj grid [[x y] newc]) newdir program' i' b'))))))
        
(defn part-1 []
  (count (robot-painting intcode {})))

(defn part-2 []
  (let [grid (robot-painting intcode {[0 0] 1})
        ones (map first (filter #(= 1 (second %)) grid))
        [minx maxx miny maxy] (mapv #(apply %1 (map %2 ones))
                                    [min max min max] [first first second second])]
    (->> (mapv (fn [y] (cs/join (map (fn [x] ({0 \space 1 \#} (grid [x y] 0)))
                                     (range minx (inc maxx)))))
               (reverse (range miny (inc maxy)))))))
