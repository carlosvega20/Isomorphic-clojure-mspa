(ns products.views.detail
  (:require [reagent.core :as reagent :refer [atom]]
            [products.state :as state]
            [products.action :as action]))

(defn view []
  (let [my-name "Product Detail Page"]
    [:div
      [:h2 my-name]]))