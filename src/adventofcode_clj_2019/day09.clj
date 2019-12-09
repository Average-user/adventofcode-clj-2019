(ns adventofcode-clj-2019.day09
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]
            [adventofcode-clj-2019.day05 :as d5]))

(def program
  (into {} (map-indexed vector (mapv read-string (cs/split (u/input 9) #",")))))

(defn part-1 [] (first (d5/execute [1] program)))

(defn part-2 [] (first (d5/execute [2] program)))
