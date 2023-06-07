(ns cljtime.core)
defn timeMap ([unix] 
    (timeMap unix "English"))
    ([unix lang]
    (let [month-day 
        (let [days (quot unix 86400)] 
            (let [dOlym (- days (* (quot days 1461) 1461)) r (let [s (mod unix 126230400)]
            (cond (<= 0 s 31449600) 0 (<= 31449601 s 63072000) 1 (<= 63072001 s 94608000) 2 (<= 94608001 s 126144000) 3))]  
                (let [d (cond (= r 0) (+ 1 dOlym) (= r 1) (- dOlym 364) (= r 2) (- dOlym 730) (= r 3) (- dOlym 1095))
                  feb  (cond (= r  2) 29 :else 28)] (cond (<= 1 d 31) [1 d] (<= 1 (- d 31) feb) [2 (- d 31)]
                    (<= 1 (- d 31 feb) 31) [3 (- d 31 feb)] (<= 1 (- d 62 feb) 30) [4 (- d 62 feb)]
                    (<= 1 (- d 92 feb) 31) [5 (- d 62 feb)] (<= 1 (- d 123 feb) 30) [6 (- d 123 feb)]
                    (<= 1 (- d 153 feb) 31) [7 (- d 153 feb)] (<= 1 (- d 184 feb) 31) [8 (- d 184 feb)] 
                    (<= 1 (- d 215 feb) 30) [9 (- d 215 feb)] (<= 1 (- d 246 feb) 31) [10 (- d 246 feb)] 
                    (<= 1 (- d 277 feb) 30) [11 (- d 277 feb)] (<= 1 (- d 307 feb) 31) [12 (- d 307 feb)]))))] 
    { :day (let [days {"English" {0 "Thursday" 1 "Friday" 2 "Saturday" 3 "Sunday" 4 "Monday" 5 "Tuesday" 6 "Wednesday"} 
               "Español" {0 "Jueves" 1 "Viernes" 2 "Sábado" 3 "Domingo" 4 "Lunes" 5 "Martes" 6 "Miércoles"}
               "Français" {0 "Jeudi" 1 "Vendredi" 2 "Samedi" 3 "Dimanche" 4 "Lundi" 5 "Mardi" 6 "Mecredi"}}]
        ((days lang) (mod (quot unix 86400) 7)))
     :year (+ 1970 (* 4 (quot unix 126230400)) (let [s (mod unix 126230400)] (cond (<= 0 s 31449600) 0
        (<= 31449601 s 63072000) 1 (<= 63072001 s 94608000) 2 (<= 94608001 s 126144000) 3)))   
     :month (format "%02d" (first month-day))
     :month-lang (let [months {"English" {1 "January" 2 "February" 3 "March" 4 "April" 5 
                "May" 6 "June" 7 "July" 8 "August" 9 "September" 10 "October" 11 "November" 12 "December"}    
                "Español" {1 "enero" 2 "febrero" 3 "marzo" 4 "abril" 5 "mayo" 6 "junio" 7 "julio" 
                8 "agosto" 9 "septiembre" 10 "octubre" 11 "noviembre" 12 "deciembre"}}]
        ((months lang) (first month-day)))
     :dayOfTheMonth-f (format "%02d" (second month-day))
     :dayOfTheMonth (second month-day)
     :24hr (format "%02d" (quot (mod unix 86400) 3600))
     :min (format "%02d" (quot (mod unix 3600) 60))
     :sec (format "%02d" (mod unix 60))
     :12hr (let [tfhr (quot (mod unix 86400) 3600)] (cond (<= 12 tfhr 24) (- tfhr 12) :else tfhr))
     :ampm (let [tfhr (quot (mod unix 86400) 3600)] (cond (<= 12 tfhr 24) "PM" :else  "AM"))
     :utc true
     :lang lang
    })))  
