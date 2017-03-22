(ns luhn.core)

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
