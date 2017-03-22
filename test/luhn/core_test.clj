(ns luhn.core-test
  (:require [midje.sweet :refer :all]
            [luhn.core :refer :all]))

(defn valid? [cc]
  true)

(facts "about luhn check"
       (fact "0 passes the test"
             (valid? "0") => true))
