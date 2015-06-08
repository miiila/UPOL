(defclass editor (window) ())

(defmethod window-mouse-down ((e editor) button position)
    (mouse-down (shape e) button position))

(defmethod initialize-instance ((e editor) &rest args)
  (call-next-method)
  (let ((editor-canvas (make-instance 'editor-canvas)))
    (set-shape e editor-canvas)))

(defclass editor-canvas (picture) ())

(defmethod ev-mouse-down ((ec editor-canvas) sender origin button position)
  (let ((item (apply #'make-instance (item-class origin) ())))
    (set-items ec (cons (move item (x position) (y position)) (cdr (items ec))))))

(defmethod mouse-down ((ec editor-canvas) button position)
  (dolist (item (items ec))
    (if (contains-point-p item position)
        (mouse-down item button position)))
  (when (eql button :right)
    (let ((context-menu (make-instance 'context-menu)))
      (set-original-position context-menu position)
      (make-menu-items context-menu)
      (set-delegate context-menu ec)
      (if (typep (car (items ec)) 'context-menu)
          (set-items ec (cons (move context-menu (x position) (y position)) (cdr (items ec))))
        (set-items ec (cons (move context-menu (x position) (y position)) (items ec)))))))

(defclass context-menu (picture) 
  ((original-position :initform nil)))

(defmethod ev-mouse-down ((cm context-menu) sender origin button position)
  (send-event cm 'ev-mouse-down origin button (original-position cm)))

(defmethod set-original-position ((cm context-menu) position)
  (setf (slot-value cm 'original-position) position))

(defmethod original-position ((cm context-menu))
  (slot-value cm 'original-position))

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

(defmethod ev-mouse-down ((mi menu-item) sender origin button position)
  (send-event mi 'ev-mouse-down mi button position))

(make-instance 'editor)
