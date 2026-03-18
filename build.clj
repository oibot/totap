(ns build
  (:require
   [clojure.string :as str]
   [clojure.tools.build.api :as b]))

;; Replace NAME with your project name
(def project-name "NAME")

(def target-dir "target")
(def class-dir (str target-dir "/classes"))

(def version
  (or (System/getenv "VERSION")
      (some-> (b/git-process {:git-args "rev-parse --short HEAD"}) :out str/trim)
      "dev"))

(def jar-name (format "%s-%s.jar" project-name version))
(def uber-file (str target-dir "/" jar-name))

(def basis (b/create-basis {:project "deps.edn"}))

;; List namespaces that contain (:gen-class) here so the Java handler class is generated.
(def aot-nses
  '[])

(defn clean [_] (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis basis
                  :src-dirs ["src"]
                  :class-dir class-dir
                  :ns-compile aot-nses})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis}))
