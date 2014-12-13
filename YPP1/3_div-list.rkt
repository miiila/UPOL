 (define div-list 
    (lambda (n)
      (build-list n (lambda (x)
                      (if (= (modulo n (+ x 1)) 0) #t #f)))))