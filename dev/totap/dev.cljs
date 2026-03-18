(ns totap.dev
  (:require [totap.core :as totap]))

(defn main []
  (println "Loaded!"))

(defn ^:dev/after-load reload []
  (println "Reloaded!"))
