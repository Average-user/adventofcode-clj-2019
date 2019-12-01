(ns adventofcode-clj-2019.day01
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]))

(defn fuel [n] (- (int (/ n 3)) 2))

(defn part-1 []
  (->> (cs/split (u/input 1) #"\n")
       (map (comp fuel u/parse-int))
       (reduce +)))

(defn part-2 []
 (letfn [(g [n] (apply + (take-while #(> % 0) (rest (iterate fuel n)))))]
   (->> (cs/split (u/input 1) #"\n")
        (map (comp g u/parse-int))
        (reduce +))))
   
      
