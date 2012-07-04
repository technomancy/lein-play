(ns leiningen.test-play-hook
  (:use [clojure.test]
        [leiningen.hooks.play]))

(deftest flip-flop
  (let [flop (java.io.File. "/tmp/test-lein-play")]
    (if (.exists flop)
      (is (not (.delete flop)))
      (with-open [w (java.io.FileOutputStream. flop)]))))
