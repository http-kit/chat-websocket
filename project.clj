(defproject chat "1.0"
  :description "Realtime chat by utilizing http-kit's websocket support"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [ring/ring-core "1.8.0"]
                 [ring/ring-defaults "0.3.2"]
                 [compojure "1.6.1"]
                 [org.clojure-android/data.json "0.2.6-SNAPSHOT"]
                 [org.clojure/tools.logging "0.2.3"]
                 [ch.qos.logback/logback-classic "1.0.1"]
                 [http-kit "2.4.0-alpha4"]]
  :warn-on-reflection true
  :min-lein-version "2.0.0"
  :main main
  :test-paths ["test"]
  :plugins [[lein-swank "1.4.5"]
            [lein-cljsbuild "1.1.7"]]
  :cljsbuild {
              :builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "static/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"})
