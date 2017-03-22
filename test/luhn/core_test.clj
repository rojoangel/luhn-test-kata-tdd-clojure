(ns luhn.core-test
  (:require [midje.sweet :refer :all]
            [luhn.core :refer :all]))

(defn- cc->digits [cc]
  (map (comp read-string str) cc))

(defn- sum-digits [n]
  (apply + (map #(read-string (str %)) (str n))))

(defn valid? [cc]
  (let [digits (cc->digits cc)
        even-digits (take-nth 2 (rest digits))
        reverse-digits (reverse digits)
        reverse-odds (take-nth 2 reverse-digits)
        reverse-evens (take-nth 2 (rest reverse-digits))
        dupped-reverse-evens (map (partial * 2) reverse-evens)
        digit-added-dupped-reverse-evens (map sum-digits dupped-reverse-evens)]
    (if (= 0 (mod (+ (apply + reverse-odds) (apply + digit-added-dupped-reverse-evens)) 10))
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
             (valid? "92932") => true)
       (fact "929320 fails the test"                        ; introduces reverse
             (valid? "929320") => false)
       (fact "018 passes the test"                          ; introduces sum of dupped evens
             (valid? "018") => true)
       (fact "18 passes the test"                           ; introduces reverse for evens
             (valid? "18") => true)
       (fact "59 passes the test"                           ; introduces sum of digits for dupped evens
             (valid? "59") => true)
       (fact "sample number passes the test"
             (valid? "49927398716") => true))
