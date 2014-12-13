(define roots-of-unity 
  (lambda (n)
    (let ((root 
           (lambda (k)
             (let ((func (lambda (f) (f(/ (* 2 pi k) n)))) 
                   (modif (lambda (x) (rationalize (inexact->exact x) 1/10))))
             (make-rectangular (modif (func cos)) (modif (func sin)))))))
    (build-list n 
                (lambda (i) (root i))))))
                  