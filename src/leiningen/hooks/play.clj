(ns leiningen.hooks.play
  (:use [robert.hooke :only [add-hook]])
  (:require [leiningen.test])
  (:import [java.io FileInputStream BufferedInputStream]
           [javazoom.jl.player Player]))

(defn play [name]
  (let [r (.getResourceAsStream (.getContextClassLoader
                                 (Thread/currentThread))
                                (format "leiningen/%s.mp3" name))]
    (-> (BufferedInputStream. r)
        (Player.)
        (.play))))

(add-hook #'leiningen.test/test
          (fn play-hook [f & args]
            (let [code (apply f args)]
              (play (if (zero? code)
                      "pass" "fail"))
              code)))
