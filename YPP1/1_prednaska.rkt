; 1)
  ( / (+ (log e) 1) 

      (+ (sqrt 4) (/ 10 (+ -1 6))))
; 2)


   (define w -)
   (define x +)
   (define y 1)
   (define z 2)


; 3)
(define sub-pyramid 
 (lambda (d) 
     (* (/ (sqrt 2) 2) d)))

 (define pyramid  
   (lambda (v d)  
     (* (sub-pyramid d)  
       (+ (sub-pyramid d)  
         (sqrt  
           (+ 
             (* (expt v 2) 4) 
             (expt (sub-pyramid d) 2)))))))



; 4)

   (define my-negative? 
     (lambda (x) 
      (< x 0)))

; 5)


     (define my-proc 
          (lambda (x) 
               (cond ((> x 0) (+ x 2)) 
                     ((< x 0) (- x 2))
                     (else 0))))

; 6)


     (define implies  
          (lambda (a b)  
          (not   
               (and a (not b)))))



; 7)


     (define my-even 
          (lambda (x) 
               (if (= (mod x 2) 0) x #f)))




; curryMax

(define curryMax 
    (lambda (a)
      (lambda (b)
        (lambda(c)
          (if (> a b) (if (> a c) a c) (if (> b c) b c))))))




(((curryMax 16) 5) 9)
