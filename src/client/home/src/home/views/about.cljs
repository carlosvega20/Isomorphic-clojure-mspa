(ns home.views.about
  (:require [reagent.core :as reagent :refer [atom]]
            [home.state :as state]
            [home.action :as action]))

(defn view []
  (let [my-name "About Page"]
    [:div
      [:h2 my-name]]))