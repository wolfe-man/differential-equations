(ns differential-equations.solved)

(def y-not 1)
(def x-min 0)
(def x-max 10.0)
(def n 10)
(def step-size (/ (- x-max x-min) n))
(defn differential-equation [x y]
  (+ x (- 1 y)))

y = x + e^-x
(defn solved-equation [x]
  (as-> Math/E %
        (Math/pow % (* -1 x))
        (+ % x)
        ))
