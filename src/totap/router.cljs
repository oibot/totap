(ns totap.router
  (:require
   [reitit.frontend :as rf]
   [reitit.frontend.easy :as rfe]))

(def routes
  [["/" :route/apps]
   ["/support" :route/support]])

(def router
  (rf/router routes))

(defn start! [store]
  (rfe/start!
   router
   (fn [match _]
     (swap! store assoc :current-view (get-in match [:data :name])))
   {:use-fragment false}))
