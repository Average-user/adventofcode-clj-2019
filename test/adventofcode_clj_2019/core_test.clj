(ns adventofcode-clj-2019.core-test
  (:require [clojure.test :refer :all]
            [adventofcode-clj-2019.day01 :as day01]
            [adventofcode-clj-2019.day02 :as day02]
            [adventofcode-clj-2019.day03 :as day03]
            [adventofcode-clj-2019.day04 :as day04]
            [adventofcode-clj-2019.day05 :as day05]
            [adventofcode-clj-2019.day06 :as day06]
            [adventofcode-clj-2019.day07 :as day07]
            [adventofcode-clj-2019.day08 :as day08]
            [adventofcode-clj-2019.day09 :as day09]
            [adventofcode-clj-2019.day10 :as day10]
            [adventofcode-clj-2019.day11 :as day11]
            [adventofcode-clj-2019.day12 :as day12]))

(deftest day01-part-1 (is (= (day01/part-1) 3382136)))
(deftest day01-part-2 (is (= (day01/part-2) 5070314)))

(deftest day02-part-1 (is (= (day02/part-1) 5290681)))
(deftest day02-part-2 (is (= (day02/part-2) 5741)))

(deftest day03-part-1 (is (= (day03/part-1) 2129)))
(deftest day03-part-2 (is (= (day03/part-2) 134662)))

(deftest day04-part-1 (is (= (day04/part-1) 1605)))
(deftest day04-part-2 (is (= (day04/part-2) 1102)))

(deftest day05-part-1 (is (= (day05/part-1) 12896948)))
(deftest day05-part-2 (is (= (day05/part-2) 7704130)))

(deftest day06-part-1 (is (= (day06/part-1) 251208)))
(deftest day06-part-2 (is (= (day06/part-2) 397)))

(deftest day07-part-1 (is (= (day07/part-1) 17440)))
(deftest day07-part-2 (is (= (day07/part-2) 27561242)))

(deftest day08-part-1 (is (= (day08/part-1) 1620)))
(deftest day08-part-2 (is (= (day08/part-2) ["###   ##  #   ##### #### "
                                             "#  # #  # #   ##    #    "
                                             "###  #     # # ###  ###  "
                                             "#  # #      #  #    #    "
                                             "#  # #  #   #  #    #    "
                                             "###   ##    #  #### #    "])))

(deftest day09-part-1 (is (= (day09/part-1) 2399197539)))
(deftest day09-part-2 (is (= (day09/part-2) 35106)))

(deftest day10-part-1 (is (= (day10/part-1) 276)))
(deftest day10-part-2 (is (= (day10/part-2) 1321)))

(deftest day11-part-1 (is (= (day11/part-1) 2469)))
(deftest day11-part-2 (is (= (day11/part-2) ["#  # #     ##  ####  ##  ####  ##  #  #"
                                             "# #  #    #  #    # #  # #    #  # #  #"
                                             "##   #    #      #  #  # ###  #    #  #"
                                             "# #  #    #     #   #### #    # ## #  #"
                                             "# #  #    #  # #    #  # #    #  # #  #"
                                             "#  # ####  ##  #### #  # ####  ###  ## "])))

(deftest day12-part-1 (is (= (day12/part-1) 7013)))
(deftest day12-part-2 (is (= (day12/part-2) 324618307124784)))
