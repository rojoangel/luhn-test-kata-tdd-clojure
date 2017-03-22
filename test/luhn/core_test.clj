(ns luhn.core-test
  (:require [midje.sweet :refer :all]
            [luhn.core :refer :all]))

(defn- cc->digits [cc]
  (map (comp read-string str) cc))

(defn valid? [cc]
  (let [digits (cc->digits cc)
        odds (take-nth 2 digits)]
    (if (= 0 (mod (apply + odds) 10))
      true
      false)))

(facts "about luhn check"
       (fact "0 passes the test"
             (valid? "0") => true)
       (fact "1 fails the test"                             ; introduces conditional
             (valid? "1") => false)
       (fact "000 passes the test"                          ; introduces sum
             (valid? "000") => true)
       (fact "505 passes the test"                          ; introduces modulus 10
             (valid? "505") => true)
       (fact "92932 passes the test"                        ; introduces sum of odds
             (valid? "92932") => true))
