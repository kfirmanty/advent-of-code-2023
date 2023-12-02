(ns day2
  (:require [clojure.string :as string]))

(defn- possible-round? [state round]
  (->> state
       (filter (fn [[color num]]
                 (let [round-num (get round color 0)]
                   (not (<= round-num num)))))
       empty?))

(defn- possible-game? [state {:keys [rounds]}]
  (reduce (fn [v1 v2]
            (and v1 v2))
          true
          (map (partial possible-round? state) rounds)))

(defn- parse-game [l]
  (let [[id rounds] (string/split l #": ")
        id (Long/valueOf (string/replace id "Game " ""))
        rounds (map (fn [round]
                      (into {}
                            (map (fn [draw]
                                   (let [[num colour] (string/split draw #"\s+")]
                                     [colour (Long/valueOf num)]))
                                 (string/split round #", "))))
                    (string/split rounds #";\s?"))]
    {:id id :rounds rounds}))

(def input (slurp "input/2"))
(def state {"red" 12 "green" 13 "blue" 14})

;;3035
(defn solve-1 [input state]
  (->> (string/split input  #"\n")
       (map parse-game)
       (filter (partial possible-game? state))
       (map :id)
       (apply +)))

(defn conj-num [a b]
  (if (coll? a)
    (conj a b)
    [a b]))

(defn- smallest-possible-game [{:keys [rounds]}]
  (->> (conj rounds {"red" 0 "green" 0 "blue" 0})
       (apply merge-with conj-num)
       vals
       (map #(apply max %))
       (apply *)))
;;66027
(defn solve-2 [input]
  (->> (string/split input  #"\n")
       (map parse-game)
       (map smallest-possible-game)
       (apply +)))
