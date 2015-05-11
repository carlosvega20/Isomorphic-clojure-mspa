(ns products.core
 (:require  [reagent.core :as reagent :refer [atom]]
 			      [products.views.detail :as detail_page]
            [products.views.main :as main_page]
  	        [products.state :as state]
  	        [products.action :as action]
 			      [secretary.core :as secretary]      
           	[goog.events :as events]
           	[goog.history.EventType :as EventType])
 (:import goog.History))

; Define Routes
(secretary/defroute "/detail" {:as params}
  (action/set-page! detail_page/view))

(secretary/defroute "/" []
  (action/set-page! main_page/view))

;If browser not support HTML5 history
(secretary/set-config! :prefix "#")

; Enable browser history
(doto (History.)
 (events/listen
   EventType/NAVIGATE
   (fn [event]
     (secretary/dispatch! (.-token event))))
 (.setEnabled true))

; Export and Render the Current Page
(defn view []
  [@state/current-page-cursor])

(defn ^:export run []
  (reagent/render-component [view] 
  (.getElementById js/document "app"))
)

