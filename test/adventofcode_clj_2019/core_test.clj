(ns adventofcode-clj-2019.core-test
  (:require [clojure.test :refer :all]
            [adventofcode-clj-2019.day01 :as day01]))

(deftest day01-part-1 (is (= (day01/part-1) 3382136)))
(deftest day01-part-2 (is (= (day01/part-2) 5070314)))
