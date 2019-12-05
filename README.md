# Advent of Code 2019. Solutions in Clojure

Solutions to the problems in [here](https://adventofcode.com/2019). My timezone
sucks, so I'll be doing the solutions much later than when the problems are
released, but whatever.

The command:

``` text
lein run a b c
```

Will show solutions for the specified days (a b c ...). If no arguments are
given will show the solutions of every day so far implemented. Example:

``` text
$ lein run

  ========================================================
 | Day | Star | Solution                   | Time (msecs) |
 |========================================================|
 |  1  |  *   | 3382136                    |      1.76084 |
 |     |  **  | 5070314                    |      4.58298 |
 |  2  |  *   | 5290681                    |      1.62666 |
 |     |  **  | 5741                       |    172.15849 |
 |  3  |  *   | 2129                       |      1.55650 |
 |     |  **  | 134662                     |    977.79855 |
 |  4  |  *   | 1605                       |     13.38443 |
 |     |  **  | 1102                       |      9.99586 |
 |  5  |  *   | 12896948                   |      3.56233 |
 |     |  **  | 7704130                    |      3.95985 |
  ========================================================
"Elapsed time: 1193.223693 msecs"
```

If you want to try your inputs, input files are stored at `resources/dayXX.txt`
