; 1. a)
(define euclid-def
  (lambda (x y) 
    (define euclid-iter 
      (lambda (acc count)
        (if (= count 0) 
            acc
            (euclid-iter count (modulo acc count)))))
    (euclid-iter x y)))

; 1. b)
(define euclid-let
  (lambda (x y) 
    (let euclid-iter ((acc x)
                      (count y))
      (if (= count 0) 
            acc
            (euclid-iter count (modulo acc count))))))

; 2.
(define in-list-pairs?
  (lambda (val list)
    (foldr (lambda (pair bool)
             (or 
              (equal? (car pair) val) bool))
           #f
           list)))

(define count 
  (lambda (l)
    (count-list l '())))

(define count-list
  (lambda (l term)
    (foldr (lambda (i res)
             (if (in-list-pairs? i res)
                 (map (lambda (pair)
                        (if (equal? (car pair) i)
                            (cons (car pair) (+ (cdr pair) 1))
                            pair))
                      res)
                 (cons (cons i 1) res)))
           term
           l)))

; 3.
(define histogram
  (lambda (l)
    (foldr count-list '() l)))

; 4.
(define map-index-pred 
  (lambda (pred? f l)
    (map 
     (lambda (i)
       (if (list? i)
       (map-index-pred pred? f i)
       (if (pred? i) (f i) i)))
       l)))

; 5.
(define transition
  (lambda (num state) 
    (cond ((= num 0) state) 
      ((equal? state 'q0) (if (= num 1) 'q1 'q2))
      ((equal? state 'q1) (if (= num 1) 'q2 'q0))
      ((equal? state 'q2) (if (= num 1) 'q0 'q1)))))

(define divided-by-three?
  (lambda (l)
    (let automat ((i 0)
                  (state 'q0))
      (if (= i (length l))
          (if (equal? state 'q0) #t #f)
          (automat (+ i 1) (transition (list-ref l i) state))))))
      

      