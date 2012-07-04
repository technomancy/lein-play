(ns leiningen.hooks.play
  (:require [leiningen.test]
            [leiningen.core.user :as user]
            [robert.hooke :as hooke]
            [clojure.java.io :as io])
  (:import (java.io FileInputStream BufferedInputStream)
           (javazoom.jl.player Player)))

(defn play [name]
  (let [user-file (io/file (user/leiningen-home) (str name ".mp3"))
        is (io/input-stream (if (.exists user-file)
                              user-file
                              (io/resource (format "leiningen/%s.mp3" name))))]
    (-> (BufferedInputStream. is)
        (Player.)
        (.play))))

(defn activate []
  (hooke/add-hook #'leiningen.test/test
                  (fn play-hook [f & args]
                    (try (apply f args)
                         (play "pass")
                         (catch Exception e
                           (play "fail")
                           (throw e))))))
