
(defun make-point (x y)
  (move (make-instance 'point) x y))

(defun make-polygon (coord-list filledp closedp color)
  (set-closedp (set-filledp
                (set-color
                 (set-items (make-instance 'polygon)
                            (mapcar (lambda (pair)
                                      (apply #'make-point pair))
                                    coord-list))
                 color)
                filledp)
               closedp))


(defclass click-circle (circle) ())

(defmethod mouse-down ((circ click-circle) button position) 
  (set-color circ (random-color)) 
  (call-next-method))
          


(defun make-arr (color)
  (make-polygon '((0 -30) (0 -15) (30 -15) (30 15) (0 15) (0 30) (-30 0))
                t
                t
                color))

(defun cwa1-items ()
  (let ((arr1 (make-arr :blue))
        (circ (make-instance 'click-circle)))
    (rotate arr1 (/ Pi 2) (make-instance 'point))
    (move arr1 60 150)
    (set-radius circ 40)
    (move circ 60 60)
    (set-filledp circ t)
    (list circ arr1)))

(defun cwa2-items (arr-color)
  (let ((arr1 (make-arr arr-color))
        (arr2 (make-arr arr-color))
        (circ (make-instance 'click-circle)))
    (move arr1 70 150)
    (rotate arr2 pi (make-instance 'point))
    (move arr2 250 150)
    (set-radius circ 50)
    (move circ 160 60)
    (set-filledp circ t)
    (list circ arr1 arr2)))

(defclass circle-with-arrows-2 (picture)
  ())

(defmethod cwa2-circle ((cwa circle-with-arrows-2))
  (first (items cwa)))

(defmethod cwa2-left-arr ((cwa circle-with-arrows-2))
  (second (items cwa)))

(defmethod cwa2-right-arr ((cwa circle-with-arrows-2))
  (third (items cwa)))

(defmethod initialize-instance ((cwa circle-with-arrows-2) &key)
  (let ((items (cwa2-items :dark-blue)))
    (call-next-method)
    (set-items cwa items)
    (add-event (cwa2-left-arr cwa) '(ev-mouse-down ev-mouse-down-1))
    (add-event (cwa2-right-arr cwa) '(ev-mouse-down ev-mouse-down-2)))
  cwa)
    
(defmethod ev-mouse-down-1 ((cwa circle-with-arrows-2) sender origin button position)
  (print (delegate (cwa2-left-arr cwa)))
  (when (eql button :left)
    (move (cwa2-circle cwa) -10 0))
  (send-event cwa 'ev-mouse-down origin button position))

(defmethod ev-mouse-down-2 ((cwa circle-with-arrows-2) sender origin button position)
  (when (eql button :left)
    (move (cwa2-circle cwa) 10 0))
  (send-event cwa 'ev-mouse-down origin button position))

(setf w (make-instance 'window))
(setf cwa2 (make-instance 'circle-with-arrows-2))
(set-shape w cwa2)