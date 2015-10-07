(ns differential-equations.graph
  (:require [incanter.core :as in]
            [incanter.stats :as stat]
            [incanter.charts :as chart]
            [incanter.io :as io]
            [differential-equations.solved :as s]))

(defn cartesian-coordinates [coordinates]
  (as-> coordinates %
        (cons [s/x-min s/y-not] %)
        (let [[x-values y-values]
              [(mapv #(.floatValue (first %)) %) (mapv #(.floatValue (second %)) %)]]
        [x-values y-values])))

(defn make-graph [euler-ordered-pairs improved-euler-ordered-pairs taylor-method-4-ordered-pairs]
  (let [euler-coordinates (cartesian-coordinates euler-ordered-pairs)
        improved-euler-coordinates (cartesian-coordinates improved-euler-ordered-pairs)
        taylor-method-4-coordinates (cartesian-coordinates taylor-method-4-ordered-pairs)]
    (doto
      (chart/function-plot s/solved-equation s/x-min s/x-max
                           :legend true
                           :series-label "Solved-Equation"
                           :x-label ""
                           :y-label "")
      (chart/add-lines (first euler-coordinates) (second euler-coordinates)
                       :legend true
                       :series-label "Euler"
                       :x-label ""
                       :y-label "")
      (chart/add-lines (first improved-euler-coordinates) (second improved-euler-coordinates)
                       :legend true
                       :series-label "Improved-Euler"
                       :x-label ""
                       :y-label "")
      (chart/add-lines (first taylor-method-4-coordinates) (second taylor-method-4-coordinates)
                       :legend true
                       :series-label "Taylor-4"
                       :x-label ""
                       :y-label "")
      ;(chart/add-text s/x-max error (str "Most Accuracte: " error))
      in/view)))
