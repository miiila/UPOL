;;;;;;;;;;;;;;;;;;;;;;
;;ROTATING RECTANGLE;;
;;;;;;;;;;;;;;;;;;;;;;

(defun rectangle-points ()
  (mapcar (lambda (coords)
            (apply #'move (make-instance 'point) coords))
          '((35 20) (80 20) (80 80) (35 80))))

(defun create-rectangle ()
  (let ((rect (make-instance 'polygon)))
    (set-items rect (rectangle-points))))

(defun create-rotating-timer (rectangle period center)
  (let ((timer (make-instance 'timer)))
    (set-period timer period)
    (set-timer-function timer #'rotate rectangle 45 center)
    timer))

(defclass rotating-rectangle (picture)
  ((timer :initform nil)))

(defmethod set-timer ((rt rotating-rectangle) timer)
  (setf (slot-value rt 'timer) timer))

(defmethod timer ((rt rotating-rectangle))
  (slot-value rt 'timer))
           
(defmethod initialize-instance ((rt rotating-rectangle) &rest args)
  (call-next-method)
  (let ((rectangle (create-rectangle)))
    (set-timer rt (create-rotating-timer rectangle 1 (caddr (items rectangle))))
    (set-items rt (list rectangle))))

(defmethod start-rotation ((rt rotating-rectangle))
  (start-timer (timer rt)))

(defmethod stop-rotation ((rt rotating-rectangle))
  (stop-timer (timer rt)))