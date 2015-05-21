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
  
  ;Polyfills for JavaScript Engine
  (.eval nashorn (str (slurp (source-url "public/app.js"))))
  (.eval nashorn (str "global.React = " (slurp (source-url "public/react.js"))))
  
  ;dev
  ;(.eval nashorn (str (slurp (source-url "public/home/dev/goog/base.js"))))
  ;(.eval nashorn (str (slurp (source-url "public/home/dev/app.js"))))
  ;(.eval nashorn "goog.require('home.core'); home.core.run();")

  ;Prod Mode
  (.eval nashorn (str (slurp (source-url "public/home/prod/app.js"))))
  ;(.eval nashorn "home.core.render_page();")

  ;test reactjs 
  ;(.eval nashorn "var HelloMessage = React.createClass({displayName: 'HelloMessage',render: function() {return React.createElement('div', null, 'Hello ', this.props.name);}});")
  ;(.eval nashorn "React.renderToStaticMarkup(React.createElement(HelloMessage, {name: 'John'}))")
  
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