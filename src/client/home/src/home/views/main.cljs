(ns home.views.main
  (:require [reagent.core :as reagent :refer [atom]]
            [home.state :as state]
            [home.action :as action]))

(defn view []
  (let [my-name "Main home Page"]
    [:div
      [:h2 my-name]]))