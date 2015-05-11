(ns products.views.main
  (:require [reagent.core :as reagent :refer [atom]]
            [products.state :as state]
            [products.action :as action]))

(defn view []
  (let [my-name "Main products Page"]
    [:div
      [:h2 my-name]]))