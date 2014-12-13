(define make-c 
  (lambda (a b)
    (cons a b)))

(define real car)
(define imag cdr)

(define conj 
  (lambda (c) 
    (cons (real c) (- (imag c)))))

(define c+
  (lambda (c1 c2)
    (cons (+ (real c1) (real c2)) 
          (+ (imag c1) (imag c2)))))

(define c*
  (lambda (c1 c2)
    (cons (- (* (real c1) (real c2)) (* (imag c1) (imag c2))) 
          (+ (* (real c1) (imag c2)) (* (real c2) (imag c1))))))

(define c-
  (lambda (c1 c2)
    (cons (- (real c1) (real c2)) 
          (- (imag c1) (imag c2)))))

(define c/
  (lambda (c1 c2)
    (let ((rev (lambda (c)
                 (cons (/ 1 (real c)) (/ 1 (imag c))))))
          (c* c1 (rev c2)))))