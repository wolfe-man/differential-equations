(ns differential-equations.improvedeuler
  (:require [differential-equations.solved :as s]))

;ynplus1 = yn + h/2(f(x,y) + f(x+h,f(yn + hf(x,y))))

(defn euler-method [x y]
  (let [[xn yn]
        [(+ s/step-size x) (+ y (* (s/differential-equation x y) s/step-size))]]
    [xn yn]))

(defn improved-euler-method [x y]
  (let [slope (s/differential-equation x y)
        euler-pair (euler-method x y)
        xn (first euler-pair)
        yn (second euler-pair)
        euler-step (/ s/step-size 2)
        improved-euler (+ y (* euler-step (+ slope yn)))
        ]
    (cons [xn improved-euler] (lazy-seq (improved-euler-method xn yn)))))

(defn improved-euler-ordered-pairs []
  (take s/n (improved-euler-method s/x-min s/y-not)))

;improved-euler-ordered-pairs
