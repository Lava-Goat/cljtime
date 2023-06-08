(ns cljtime.core-test
  (:require [clojure.test :refer :all]
            [cljtime.core :refer :all]))

(let [unixTime (quot (System/currentTimeMillis) 1000)]
(timeMap unixTime))

(let [now (timeMap (- (quot (System/currentTimeMillis) 1000) (* 7 3600)) "Español")]
(str (now :day)
      " el "
     (now :dayOfTheMonth) 
      " de " 
     (now :month-lang) 
      ", " 
     (now :year) 
      ". " 
     (now :12hr)
      ":" 
     (now :min)
      ":" 
     (now :sec)
      " " 
     (now :ampm)))
(unix->iso8601 (quot (System/currentTimeMillis) 1000))
(unix->rfc3339 (quot (System/currentTimeMillis) 1000))
