(defproject mspa "v.1.0"
  :description "Multi-Single-Page Applications (MSPA) using Clojure - ClojureScript - Nashorn JVM"
  :url "https://github.com/carlosvega20/Isomorphic-clojure-mspa"
  :ring {:handler server.core/app :port 2001}
  :resource-paths  ["resources" "build-resources"]
  :uberjar-name "server.jar"

  :aliases {"server"  ["ring" "server"]
            "server-headless"  ["ring" "server-headless"]
            "build" ["do" ["clean"] ["compile"]]}

  :dependencies [[org.clojure/clojure "1.6.0"]
                [compojure "1.3.3"]
                [selmer "0.8.2"]
                [metosin/compojure-api "0.19.3"]
                [ring/ring-defaults "0.1.4"]]

  :plugins [[lein-ring "0.8.13"]
            [lein-environ "0.5.0"]
            [lein-modules "0.3.11"]]

  :modules {:subprocess "../../../lein"
              :dirs ["src/client/home" "src/client/products"]}

  :min-lein-version "2.5.0")
