(defproject org.httpkit/chat-websocket "1.0"
  :description "Realtime chat by utilizing http-kit's websocket support"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring/ring-core "1.1.6"]
                 [compojure "1.0.2"]
                 [org.clojure/data.json "0.1.2"]
                 [org.clojure/tools.logging "0.2.3"]
                 [ch.qos.logback/logback-classic "1.0.1"]
                 [http-kit "2.1.5"]]
  :warn-on-reflection true
  :min-lein-version "2.0.0"
  :main main
  :test-paths ["test"]
  :plugins [[lein-swank "1.4.4"]
            [lein-cljsbuild "0.3.0"]]
  :cljsbuild {
              :builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "static/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"})
