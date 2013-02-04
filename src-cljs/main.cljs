(ns main)

(def i (js/$ "#i"))
(def history (js/$ "#history"))

(defn- now [] 
  (quot (.getTime (js/Date.)) 1000))

(def max-id (atom 0))

(defn add-msg [msg]
  (let [t (str "<span class=\"time\">" (- (now) (.-time msg))  "s ago</span>")
        author (str "<span class=\"author\">" (.-author msg) "</span>: ")]
    (.append history (str "<li>" author (.-msg msg) t "</li>"))))

(def conn 
  (js/WebSocket. "ws://127.0.0.1:9899/ws"))

(set! (.-onopen conn)
  (fn [e]
    (.send conn
      (.stringify js/JSON (js-obj "command" "getall")))))

(set! (.-onerror conn) 
  (fn []
    (js/alert "error")
    (.log js/console js/arguments)))

(set! (.-onmessage conn)
  (fn [e]
    (let [msgs (.parse js/JSON (.-data e))]
      (doseq [msg msgs]
         (if (> (.-id msg) (.-state max-id))
           (do
             (add-msg msg)
             (swap! max-id #(.-id msg))))))))

(defn send-to-server []
  (let [msg (.trim js/$ (.val i))
        author (.trim js/$ (.val (js/$ "#name")))]
    (if msg
      (do
        (.send conn (.stringify js/JSON (js-obj "msg" msg "author" author)))
        (.val i "")))))

(.click (js/$ "#send") send-to-server)

(.keyup (.focus i) 
  (fn [e]
    (if (= (.-which e) 13) (send-to-server))))
