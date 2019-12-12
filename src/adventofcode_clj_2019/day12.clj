(ns adventofcode-clj-2019.day12
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]
            [clojure.math.numeric-tower :as math]))

(def moons
  (let [m (->> (cs/split (reduce (fn [s [a b]] (cs/replace s a b))
                                 (u/input 12) [[#"<" "{"] [#">" "}"] [#"=" " "]
                                               [#"x" ":x"] [#"y" ":y"] [#"z" ":z"]])
                         #"\n")
               (mapv read-string))]
    [(map (fn [m] [(:x m) 0]) m)
     (map (fn [m] [(:y m) 0]) m)
     (map (fn [m] [(:z m) 0]) m)]))
       
(defn update-gravity [[p1 v1] [p2 v2]]
  [p1 (+ v1 (compare p2 p1))])
 
(defn update-velocity [[p1 v1]]
  [(+ p1 v1) v1])

(defn step [axis]
  (map (comp update-velocity #(reduce update-gravity % axis)) axis))

(defn part-1 []
  (let [[ax-x ax-y ax-z] (map #(first (drop 1000 (iterate step %)))
                              moons)
        potential        (map #(reduce + (map (comp math/abs first) [%1 %2 %3])) ax-x ax-y ax-z)
        kinetic          (map #(reduce + (map (comp math/abs second) [%1 %2 %3])) ax-x ax-y ax-z)]
    (reduce + (map * potential kinetic))))

(defn axis-cycle [axis]
  (loop [seen #{}, i 0, axis axis]
    (if (seen axis)
      i
      (recur (conj seen axis) (inc i) (step axis)))))

(defn part-2 [] (reduce math/lcm (pmap axis-cycle moons)))
