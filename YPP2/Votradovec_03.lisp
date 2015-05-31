(defclass semaphore (picture) 
  ((light-count :initform 2)
   (lights)))

(defun semaphore-rect-points ()
  (mapcar (lambda (coords)
            (apply #'move (make-instance 'point) coords))
          '((20 20) (140 20) (140 200) (20 200))))

(defun make-light (x y radius color)
  (set-radius
   (move 
    (set-on-color (make-instance 'light) color) 
    x y)
   radius))

(defun make-semaphore-lights ()
  (list (make-light 90 55 30 :red)
        (make-light 90 125 30 :green)))

(defmethod initialize-instance ((s semaphore) &rest intiargs)
  (call-next-method)
  (setf (slot-value s 'lights) (make-semaphore-lights))
  (set-items s (slot-value s 'lights)))
        
(defmethod light-count ((s semaphore))
  (slot-value s 'light-count))

(defmethod set-light-count ((s semaphore) count)
  (check-light-count s count)
  (setf (slot-value s 'light-count) count)
  (set-initial-phase s))

(defmethod check-light-count ((s semaphore) count)
  (if (or (< count 2) (> count 3))
      (error "Light count must be 2 or 3.")))

(defmethod next-phase ((s semaphore))
  (if (= 2 (light-count s))
      (send-to-items s #'toggle)))

(defmethod set-initial-phase ((s semaphore))
  (set-on (nth 0 (items s)))
  (set-off (nth 1 (items s)))))

;;
(setf s (make-instance 'semaphore))
(setf w (make-instance 'window))
(set-shape w s)
(set-light-count s 2)
(redraw w)
(next-phase s)
