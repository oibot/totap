(ns totap.dev
  (:require [totap.core :as totap]))

(defn main []
  (totap/init!)
  (println "Loaded!"))

(defn ^:dev/after-load reload []
  (totap/init!)
  (println "Reloaded!"))
