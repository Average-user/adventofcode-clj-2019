(ns adventofcode-clj-2019.day08
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]))

(def layers
  (partition 150 (cs/trim (u/input 8))))

(defn part-1 []
  (let [frs (->> layers                 
                 (sort-by #(get (frequencies %) \0))
                 (first)
                 (frequencies))]
    (* (frs \2) (frs \1))))

(defn part-2 []
  (->> (apply (partial map vector) layers)
       (map (comp {\0 \space \1 \#} #(first (drop-while (partial = \2) %))))
       (partition 25)
       (mapv (partial apply str))))
