(ns luhn.core-test
  (:require [midje.sweet :refer :all]
            [luhn.core :refer :all]))

(defn valid? [cc]
  (if (= "0" cc)
    true
    false))

(facts "about luhn check"
       (fact "0 passes the test"
             (valid? "0") => true)
       (fact "1 fails the test"                             ; introduces conditional
             (valid? "1") => false))
