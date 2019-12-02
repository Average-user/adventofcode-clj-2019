(ns adventofcode-clj-2019.core
  (:require [adventofcode-clj-2019.util :as u]
            [adventofcode-clj-2019.day01]
            [adventofcode-clj-2019.day02]))

(def completed [1 2])

(defn my-format [x]
  (str x (apply str (take (- 26 (count (str x))) (repeat " "))) " |"))

(defn day-results [n]
  (let [fn (if (> n 9)
             (str "(adventofcode-clj-2019.day" n)
             (str "(adventofcode-clj-2019.day0" n))
        sn (if (> n 9) (str n) (str " " n))
        p1 (->> (str fn "/part-1)") read-string eval)
        p2 (->> (str fn "/part-2)") read-string eval)]
    (println (str " | " sn "  |  *   | " (my-format p1)))
    (println (str " |     |  **  | " (my-format p2)))))

(defn -main [& args]
  (let [args' (if (empty? args) completed (map u/parse-int args))]
    (time
      (do (println "\n  =========================================")
          (println " | Day | Star | Solution                   |")
          (println " |=========================================|")
          (doseq [day args'] (day-results day))
          (println "  =========================================")))))
