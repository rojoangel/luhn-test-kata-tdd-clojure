(ns luhn.core)

(defn- cc->digits [cc]
  (map (comp read-string str) cc))

(defn- sum-digits [n]
  (apply + (map #(read-string (str %)) (str n))))

(defn- dup-when-in-even-position [idx n]
  (let [pos (inc idx)]
    (if (even? pos)
      (* 2 n)
      n)))

(defn- checksum [digits]
  (apply + (map sum-digits (map-indexed dup-when-in-even-position (reverse digits)))))

(defn valid? [cc]
  (let [digits (cc->digits cc)]
    (= 0 (mod (checksum digits) 10))))
