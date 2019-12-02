(ns adventofcode-clj-2019.core-test
  (:require [clojure.test :refer :all]
            [adventofcode-clj-2019.day01 :as day01]
            [adventofcode-clj-2019.day02 :as day02]))

(deftest day01-part-1 (is (= (day01/part-1) 3382136)))
(deftest day01-part-2 (is (= (day01/part-2) 5070314)))

(deftest day02-part-1 (is (= (day02/part-1) 5290681)))
(deftest day02-part-2 (is (= (day02/part-2) 5741)))
