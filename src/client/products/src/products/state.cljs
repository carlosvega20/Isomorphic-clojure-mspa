(ns products.state
  (:require [reagent.core :as reagent :refer [atom cursor]]
            [ajax.core :as ajax]))

(def app-state (atom {      :page { :current-page nil }}))

(def page-cursor (cursor app-state [:page]))
(def current-page-cursor (cursor page-cursor [:current-page]))

