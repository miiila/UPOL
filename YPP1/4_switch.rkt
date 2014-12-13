(define my-cons
  (lambda (a b)
    (let ((par (cons a b)))
     (lambda (arg)
       (if arg (car par) (cdr par))))))

(define my-car 
  (lambda (my-par) 
    (my-par #t)))

(define my-cdr 
  (lambda (my-par) 
    (my-par #f)))

(define switch 
  (lambda my-par 
    (my-cons (my-cdr p1) (my-car p1))))