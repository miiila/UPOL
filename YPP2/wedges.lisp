(defun random-color () 
  (let ((colors (color:get-all-color-names))) 
    (nth (random (length colors)) colors)))

(defun make-circle ()
  (let ((circ (make-instance 'circle)))
    (set-radius circ 50)
    (move circ 100 100)
    circ))

(defun make-wedge-line (center length)
  (let* ((start center)
         (start (make-instance 'point))
         (end (make-instance 'point))
         (line (make-instance 'polygon)))
    (set-x start (x center))
    (set-y start (y center))
    (set-x end (x center))
    (set-y end (+ (y center) 50))
    (set-color line (random-color))
    (set-items line (list start end))))

(defun make-wedges (wedges-count center length)
  (let ((result '()))
    (dotimes (i wedges-count)
      (let ((wedge-line (make-wedge-line center length)))
        (setf result 
              (cons
               (rotate wedge-line (* i (/ Pi (/ wedges-count 2))) (car (items wedge-line)))
               result))))
    result))  

(defclass divided-circle (picture) ())

(defmethod initialize-instance ((dc divided-circle) &rest args)
  (call-next-method)
  (let* ((circ (make-circle))
         (wedges (make-wedges 15 (center circ) (radius circ))))
    (set-items dc (cons circ wedges))))

(setf w (make-instance 'window))
(setf div-circ (make-instance 'divided-circle))
(set-shape w div-circ)

(defmethod set-wedges-count ((wf wheel-of-fortune) wedges-count)
  (setf (slot-value wf 'wedges-count) wedges-count))

(defmethod wedges-count ((wf wheel-of-fortune))
  (slot-value wf 'wedges-count))