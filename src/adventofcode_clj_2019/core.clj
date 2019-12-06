(ns adventofcode-clj-2019.core
  (:require [adventofcode-clj-2019.util :as u]
            [adventofcode-clj-2019.day01]
            [adventofcode-clj-2019.day02]
            [adventofcode-clj-2019.day03]
            [adventofcode-clj-2019.day04]
            [adventofcode-clj-2019.day05]
            [adventofcode-clj-2019.day06]))

(def completed [1 2 3 4 5 6])

(defmacro my-time
  [expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr]
     [(/ (double (- (. System (nanoTime)) start#)) 1000000.0) ret#]))

(defn format-sol [x]
  (str x (apply str (repeat (- 26 (count (str x)))  " ")) " |"))

(defn format-time [time]
  (let [t (format "%.5f" time)]
    (str (apply str (repeat (- 12 (count t)) \space)) t)))

(defn day-results [n]
  (let [get-fn  (if (> n 9)
                  (str "(adventofcode-clj-2019.day" n)
                  (str "(adventofcode-clj-2019.day0" n))
        sn      (if (> n 9) (str n) (str " " n))
        eval'   #(my-time (eval %))
        [t1 p1] (->> (str get-fn "/part-1)") read-string eval')
        [t2 p2] (->> (str get-fn "/part-2)") read-string eval')]
    (println (str " | " sn "  |  *   | " (format-sol p1) " " (format-time t1) " |"))
    (println (str " |     |  **  | "     (format-sol p2) " " (format-time t2) " |"))))

(defn -main [& args]
  (let [args' (if (empty? args) completed (map u/parse-int args))]
    (time
      (do (println "\n  ========================================================")
          (println " | Day | Star | Solution                   | Time (msecs) |")
          (println " |========================================================|")
          (doseq [day args'] (day-results day))
          (println "  ========================================================")))))
