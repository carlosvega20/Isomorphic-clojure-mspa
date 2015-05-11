(ns server.core
	(:require
            [compojure.api.sweet :refer :all]
            [compojure.route :as route]
            [ring.util.http-response :refer :all]
            [clojure.java.io :as io]
            [compojure.route :as route]
            [selmer.parser :refer [render-file]]
            [server.status :as aus])
  (:import [java.io File]
           [javax.script ScriptEngineManager]
           [jdk.nashorn.api.scripting NashornException]))

(def production (or (System/getenv "env") false))

(def nashorn (.getEngineByName (ScriptEngineManager.) "nashorn"))

(defn source-url [file-name]
  (.toString (.getResource (.getContextClassLoader (Thread/currentThread)) file-name)))

(selmer.parser/set-resource-path! (clojure.java.io/resource "templates"))

(defn seo []
  
  ;(.eval nashorn (str (slurp "https://code.jquery.com/jquery-2.1.4.js")))
  (.eval nashorn (str (slurp "https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.0/moment.min.js")))
  (.eval nashorn (str (slurp "https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.2.1/moment-timezone-with-data-2010-2020.min.js")))
  
  (.eval nashorn (str (slurp (source-url "public/app.js"))))
     
  ;Prod Mode
  ;(.eval nashorn (str (slurp (source-url "public/accounts/prod/app.js"))))

  ; Dev Mode
  ;(.eval nashorn (str (slurp (source-url "public/accounts/dev/goog/base.js"))))
  ;(.eval nashorn (str (slurp (source-url "public/accounts/dev/app.js"))))
  ;(.eval nashorn "goog.require('accounts.core');")
  ;(.eval nashorn "accounts.core.run();")

  ;(.eval nashorn (str (slurp (source-url "public/app.js"))))
  (.eval nashorn "d")
  )

(defapi app
  (route/resources "/")

  (GET* "/" [] (render-file "home.html" {}))

  (GET* "/home/*" [] 
    (render-file "home.html" 
      {:production production :view "home" :title (str (seo) )}))

  (GET* "/products/*" [] 
    (render-file "home.html" 
      {:production production :view "products" :title "Test from the server"}))
  )