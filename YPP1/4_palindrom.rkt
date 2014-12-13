(define make-palindrom 
    (lambda (l) 
      (let ((len (length l)))
        (build-list (* 2 len)
                  (lambda (i)
                    (if (< i len) 
                        (list-ref l i)
                        (list-ref l (+ len (- len i 1)))))))))
