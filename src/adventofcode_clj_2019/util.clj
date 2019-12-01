(ns adventofcode-clj-2019.util)


(defn input [n]
  (let [n' (if (< n 10) (str "0" n) (str n))]
    (slurp (str "resources/day" n' ".txt"))))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))
