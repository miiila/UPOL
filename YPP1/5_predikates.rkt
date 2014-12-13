(define map-index-pred 
  (lambda (pred? f l)
    (map 
     (lambda (i) 
       (if (pred? i) (f i) i))
       l)))