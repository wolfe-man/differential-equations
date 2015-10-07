(ns differential-equations.core
  (:require [differential-equations.euler :as e]
            [differential-equations.improvedeuler :as ie]
            [differential-equations.taylor :as t]
            [differential-equations.graph :as g]))
;Clojure circular dependencies
(defn -main []
  (g/make-graph (e/euler-ordered-pairs) (ie/improved-euler-ordered-pairs) (t/taylor-method-4-ordered-pairs)))
;(-main )
