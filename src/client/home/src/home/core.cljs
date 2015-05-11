(ns home.core
 (:require  [reagent.core :as reagent :refer [atom]]
 			      [home.views.about :as about_page]
            [home.views.main :as main_page]
  	        [home.state :as state]
  	        [home.action :as action]
 			      [secretary.core :as secretary]      
           	[goog.events :as events]
           	[goog.history.EventType :as EventType])
 (:import goog.History))

; Define Routes
(secretary/defroute "/about" {:as params}
  (action/set-page! about_page/view))

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

