(ns day6
  (:require [clojure.string :as string]))

;;Time:        61     67     75     71
;;Distance:   430   1036   1307   1150

(def input [[61 430] [67 1036] [75 1307] [71 1150]])

(def test-input [[7 9] [15 40] [30 200]])

(defn- solutions [time distance]
  (loop [speed 1 time-left (dec time) passing []]
    (if (= time-left 0)
      passing
      (let [distance-traveled (* speed time-left)
            passing (if (> distance-traveled distance)
                      (conj passing speed)
                      passing)]
        (recur (inc speed) (dec time-left) passing)))))
;;316800
(defn solve-1 [input]
  (apply * (for [[time distance] input]
            (count (solutions time distance)))))

;;45647654
(defn solve-2 [time distance]
  (count (solutions time distance)))
