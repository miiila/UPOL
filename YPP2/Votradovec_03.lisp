(defclass semaphore (picture) 
  ((light-count :initform 2)
   (lights)))
        
(defmethod light-count ((s semaphore))
  (slot-value s 'light-count))

(defmethod set-light-count ((s semaphore) count)
  (check-light-count s count)
  (if (= 2 count)
      (setf (slot-value s 'lights) (make-instance 'pedestrian-semaphore))
    (setf (slot-value s 'lights) (make-instance 'car-semaphore)))
  (set-items s (list (slot-value s 'lights) (make-semaphore-outline))))

(defmethod check-light-count ((s semaphore) count)
  (if (or (< count 2) (> count 3))
      (error "Light count must be 2 or 3.")))

(defmethod next-phase ((s semaphore))
 (apply #'next-phase (slot-value s 'lights) '()))

(defmethod set-initial-phase ((s semaphore))
  (set-on (nth 0 (items s)))
  (set-off (nth 1 (items s))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;ABSTRACT SEMAPHORE LIGHTS;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defclass semaphore-lights (picture)
  ())

(defun semaphore-rect-points ()
  (mapcar (lambda (coords)
            (apply #'move (make-instance 'point) coords))
          '((35 20) (140 20) (140 210) (35 210))))

(defun make-semaphore-outline ()
  (let ((rect (make-instance 'polygon)))
    (set-items rect (semaphore-rect-points))))

(defun make-light (x y radius color)
  (set-radius
   (move 
    (set-on-color (make-instance 'light) color) 
    x y)
   radius))

(defun make-semaphore-lights (count)
  (if (= count 2)
      (list (make-light 90 75 30 :red)
            (make-light 90 145 30 :green))
    (list (make-light 90 55 27 :red)
          (make-light 90 115 27 :orange)
          (make-light 90 175 27 :green))))

(defmethod setup-lights ((sl semaphore-lights) setup-list)
  (mapcar (lambda (light setup)
            (apply setup light '()))
          (items sl)
          setup-list))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;PEDESTRIAN SEMAPHORE CLASS;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defclass pedestrian-semaphore (semaphore-lights)
  ())

(defmethod initialize-instance ((ps pedestrian-semaphore) &key)
  (call-next-method)
  (set-items ps (make-semaphore-lights 2))
  (setup-lights ps (list #'set-on #'set-off)))

(defmethod next-phase ((ps pedestrian-semaphore))
   (send-to-items ps #'toggle))

;;;;;;;;;;;;;;;;;;;;;;;;
;;CAR SEMAPHORE LIGHTS;;
;;;;;;;;;;;;;;;;;;;;;;;;
(defclass car-semaphore (semaphore-lights)
  ((current-phase :initform 0)))

(defmethod initialize-instance ((cs car-semaphore) &key)
  (call-next-method)
  (set-items cs (make-semaphore-lights 3))
  (handle-phase-change cs))

(defmethod set-current-phase ((cs car-semaphore) phase)
  (setf (slot-value cs 'current-phase) phase))

(defmethod current-phase ((cs car-semaphore))
  (slot-value cs 'current-phase))

(defmethod next-phase ((cs car-semaphore))
   (if (= 3 (current-phase cs))
        (set-current-phase cs 0)
        (set-current-phase cs (+ (current-phase cs) 1)))
   (handle-phase-change cs))

(defmethod handle-phase-change ((cs car-semaphore))
   (let ((current-phase (current-phase cs)))
    (cond ((= 0 current-phase) 
           (setup-lights cs (list #'set-on #'set-off #'set-off)))
          ((= 1 current-phase) 
           (setup-lights cs (list #'set-on #'set-on #'set-off)))
          ((= 2 current-phase) 
           (setup-lights cs (list #'set-off #'set-off #'set-on)))
          ((= 3 current-phase) 
           (setup-lights cs (list #'set-off #'set-on #'set-off))))))