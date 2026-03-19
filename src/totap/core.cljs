(ns totap.core
  (:require
   [replicant.dom :as r]
   [totap.router :as router]
   [totap.ui :as ui]))

(defonce store (atom {}))
(defonce started? (atom false))

(defn render! []
  (r/render
   js/document.body
   (ui/render @store)))

(defn process-effect [store [effect & args]]
  (case effect
    :effect/assoc-in
    (apply swap! store assoc-in args)))

(defn perform-actions [_ event-data]
  (mapcat
   (fn [[action & payload]]
     (case action
       :action/assoc-in
       [(into [:effect/assoc-in] payload)]

       (prn "Unknown action: " action)))
   event-data))

(defn init! []
  (when-not @started?
    (reset! started? true)

    (r/set-dispatch!
     (fn [_ event-data]
       (->> (perform-actions @store event-data)
            (run! #(process-effect store %)))))

    (router/start! store)

    (add-watch
     store ::render
     (fn [_ _ _ _]
       (render!)))

    (swap! store assoc :app/started-at (js/Date.)))

  (render!))

(defn ^:dev/after-load reload! []
  (render!))
