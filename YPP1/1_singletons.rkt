(define singletons 
    (lambda (l) 
      (map (lambda (x) (list x)) l)))

;oprava
(define singletons 
    (lambda (l) 
      (map list l)))