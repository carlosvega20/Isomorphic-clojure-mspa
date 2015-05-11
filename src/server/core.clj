(ns server.core
	(:require
            [compojure.api.sweet :refer :all]
            [compojure.route :as route]
            [ring.util.http-response :refer :all]
            [clojure.java.io :as io]
            [compojure.route :as route]
            [selmer.parser :refer [render-file]])
  (:import [java.io File]
           [javax.script ScriptEngineManager]
           [jdk.nashorn.api.scripting NashornException]))

(def production (or (System/getenv "env") false))

(def nashorn (.getEngineByName (ScriptEngineManager.) "nashorn"))

(defn source-url [file-name]
  (.toString (.getResource (.getContextClassLoader (Thread/currentThread)) file-name)))

(selmer.parser/set-resource-path! (clojure.java.io/resource "templates"))

(defn seo []
  
  ;(.eval nashorn (str (slurp (source-url "public/app.js"))))
     
  ;Prod Mode
  ;(.eval nashorn (str (slurp (source-url "public/accounts/prod/app.js"))))

  ; Dev Mode
  ;(.eval nashorn (str (slurp (source-url "public/accounts/dev/goog/base.js"))))
  ;(.eval nashorn (str (slurp (source-url "public/accounts/dev/app.js"))))
  ;(.eval nashorn "goog.require('accounts.core');")
  ;(.eval nashorn "accounts.core.run();")

  ;(.eval nashorn (str (slurp (source-url "public/app.js"))))
  ;(.eval nashorn "d")
  (str "test")
  )

(defapi app
  (route/resources "/")

  (GET* "/" [] (render-file "home.html" 
      {:production production :view "home" :title (str (seo) )}))

  (GET* "/home/*" [] 
    (render-file "home.html" 
      {:production production :view "home" :title (str (seo) )}))

  (GET* "/products/*" [] 
    (render-file "home.html" 
      {:production production :view "products" :title "Test from the server"}))
  )