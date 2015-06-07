(defclass editor (window) ())

(defmethod window-mouse-down ((e editor) button position)
  (when (contains-point-p (shape e) position)
    (mouse-down (shape e) button position))
  (when (eql button :right)
    (let ((context-menu (make-instance 'context-menu)))
      (set-original-position context-menu position)
      (make-menu-items context-menu)
      (set-delegate context-menu e)
      (set-shape e (move context-menu (x position) (y position))))))

(defclass context-menu (picture) 
  ((original-position :initform nil)))

(defmethod set-original-position ((cm context-menu) position)
  (setf (slot-value cm 'original-position) position))

(defmethod original-position ((cm context-menu))
  (slot-value cm 'original-position))

(defmethod ev-mouse-down ((cm context-menu) sender origin button position)
   (let ((item (apply #'make-instance (item-class sender) ()))
         (position (original-position cm)))
     (set-shape (delegate cm) (move item (x position) (y position)))))

(defun make-menu-points ()
  (mapcar (lambda (coords)
            (apply #'move (make-instance 'point) coords))
          '((0 0) (60 0) (60 150) (0 150))))

(defun make-menu ()
  (let ((rect (make-instance 'polygon)))
    (set-items rect (make-menu-points))))

(defun make-menu-items (cm)
  (let ((item (make-instance 'menu-item)))
    (set-delegate item cm)
    (set-button-text item "circle")
    (set-item-class item 'circle)
    (set-items cm (list (make-menu) item))))

(defmethod mouse-down ((cm context-menu) button position)
  (dolist (item (items cm))
    (if (contains-point-p item position)
        (mouse-down item button position))))

(defclass menu-item (button) 
  ((item-class :initform nil)))

(defmethod initialize-instance ((mi menu-item) &rest args)
  (call-next-method)
  (add-event mi '(ev-mouse-down ev-menu-item-click)))

(defmethod set-item-class ((mi menu-item) class-name)
  (setf (slot-value mi 'item-class) class-name))

(defmethod item-class ((mi menu-item))
  (slot-value mi 'item-class))

(make-instance 'editor)

