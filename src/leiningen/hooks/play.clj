(ns leiningen.hooks.play
  (:use [leiningen.core :only [add-hook]])
  (:import [java.io FileInputStream BufferedInputStream]
           [javazoom.jl.player Player]))

(defn play [name]
  (let [r (.getResourceAsStream (.getContextClassLoader
                                 (Thread/currentThread))
                                (format "leiningen/%s.mp3" name))]
    (-> (BufferedInputStream. r)
        (Player.)
        (.play))))

(add-hook 'test (fn [f]
                  (let [code (f)]
                    (play (if (zero? code)
                            "pass" "fail"))
                    code)))
