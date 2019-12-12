(defproject adventofcode-clj-2019 "0.1.0-SNAPSHOT"
  :description "solutions to aoc2019 www.adventofcode.com"
  :url "http://example.com/FIXME"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.match "0.3.0"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 [org.clojure/core.async "0.6.532"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :main ^:skip-aot adventofcode-clj-2019.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
