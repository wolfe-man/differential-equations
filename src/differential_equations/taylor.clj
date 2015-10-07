(ns differential-equations.taylor
  (:require [differential-equations.solved :as s]))
;ynplus1 = yn + hf(x,y) + h^2/2!f2(x,y)+...+h^p/p!fp(x,y)
;f2=df/dx(x,y)+df/dy(x,y)f(x,y)

(defn h-coef [h n]
  (/ (Math/pow s/step-size s/n) (reduce * (range 1 (inc s/n)))))

(defn second-dir [x y]
  (- y x))

(defn third-dir [x y]
  (- x y))

(defn fourth-dir [x y]
  (- y x))

(defn taylor-method-4 [x y h n]
  (let [[xn yn]
        [(+ x h) (-> y
                     (+ (* h (s/differential-equation x y)))
                     (+ (* (h-coef s/step-size s/n) (second-dir x y)))
                     (+ (* (h-coef s/step-size (+ s/n 1)) (third-dir x y)))
                     (+ (* (h-coef s/step-size (+ s/n 2)) (fourth-dir x y)))
                     )]]
    (cons [xn yn] (lazy-seq (taylor-method-4 xn yn s/step-size s/n)))))

(defn taylor-method-4-ordered-pairs []
  (take s/n (taylor-method-4 s/x-min s/y-not s/step-size s/n)))
