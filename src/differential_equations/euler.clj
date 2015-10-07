(ns differential-equations.euler
  (:require [differential-equations.solved :as s]))

(defn euler-method [x y]
  (let [[xn yn]
        [(+ s/step-size x) (+ y (* (s/differential-equation x y) s/step-size))]]
    (cons [xn yn] (lazy-seq (euler-method xn yn)))))

(defn euler-ordered-pairs []
  (take s/n (euler-method s/x-min s/y-not)))
