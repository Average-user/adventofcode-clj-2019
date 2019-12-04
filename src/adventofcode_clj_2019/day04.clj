(ns adventofcode-clj-2019.day04
  (:require [adventofcode-clj-2019.util :as u]
            [clojure.string :as cs]))

(defn digits [n] (->> n str (mapv (comp u/parse-int str))))

(defn gen-numbers [n]
  (loop [n (dec n), ns (map vector (range 10))]
    (if (zero? n)
      ns
      (recur (dec n) (mapcat (fn [i] (map #(conj i %) (range (peek i) 10)))
                             ns)))))

(defn check-criteria [[a b] f n]
   (and (neg? (compare a n))
        (pos? (compare b n))
        (some (comp f count) (partition-by identity n))))

(def my-range
  (->> (cs/split (u/input 4) #"-") (map u/parse-int) sort (mapv digits)))

(defn part-1 []
  (->> (gen-numbers 6)
       (filter (partial check-criteria my-range #(<= 2 %)))
       (count)))
      
(defn part-2 []
  (->> (gen-numbers 6)
       (filter (partial check-criteria my-range #(= 2 %)))
       (count)))

