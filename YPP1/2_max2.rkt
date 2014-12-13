(define 2max (lambda (a b c) 
               (cond ((and (> a b) (> a c)) (cons a (if (> b c) b c)))
                      ((and (> b a) (> b c)) (cons b (if (> a c) a c)))
                      (else (cons c (if (> a b) a b))))))
                                             
                                             
                  
                              