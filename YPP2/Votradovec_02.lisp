(defun make-my-picture ()
       (let ((my-picture (make-instance 'picture))
             (my-circle (make-instance 'circle))
             (my-polygon (make-instance 'polygon))
             (my-points (mapcar (lambda (x) 
                                  (let ((my-point (make-instance 'point)))
                                    (set-x my-point (car x))
                                    (set-y my-point (cdr x))))
                     '((10 . 10) (40 . 10) (40 . 40) (10 . 40)))))
         #| Odsazeni jednotlivych zprav|maker bylo mene prehledne nez takto |#
         (rotate (set-items my-polygon my-points) 45 (fourth (items my-polygon)))
         (set-radius 
          (set-filledp 
           (set-color
            (move my-circle 50 50) :red) 't) 35) 
         (set-items my-picture (list my-polygon my-circle))))