(defproject adventofcode-clj-2019 "0.1.0-SNAPSHOT"
  :description "solutions to aoc2019 www.adventofcode.com"
  :url "http://example.com/FIXME"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.match "0.3.0"]]
  :main ^:skip-aot adventofcode-clj-2019.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
