(ns day1
  (:require [clojure.string :as string]))

(defn- ->num [l]
  (let [nums (string/replace l #"[^\d]" "")]
    (Long/valueOf (str (first nums) (last nums)))))

;;53334
(defn solve-1 []
  (->> (string/split (slurp "input/1") #"\n")
       (map ->num)
       (apply +)))

(def substitutions
  {"one" "1"
   "two" "2"
   "three" "3"
   "four" "4"
   "five" "5"
   "six" "6"
   "seven" "7"
   "eight" "8"
   "nine" "9"})

(defn- ->num2 [l]
  (let [nums (-> l
                 (string/replace (->> substitutions keys (string/join "|") re-pattern)
                                 substitutions)
                 (string/replace #"[^\d]" ""))]
    (Long/valueOf (str (first nums) (last nums)))))

;;52834
(defn solve-2 []
  (->> (string/split (slurp "input/1") #"\n")
       (map ->num2)
       (apply +)))
