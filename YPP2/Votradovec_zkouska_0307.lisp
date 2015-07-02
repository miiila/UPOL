;;;;;;;;;;;;;;;
;;Timer class;;
;;;;;;;;;;;;;;;


; Trida zapouzdrujici funkcnost timeru v prostredi LispWorks
; Pouziti:
;  + (setf new-timer (make-instance 'timer))
;  + (set-timer-function new-timer (lambda (x) (print x) 10))
;  + (start-timer new-timer)
;
; Vychozi perioda casovace je 10 vterin
; Je ji mozne zmenit zpravou (set-period)
;
; Sloty function a function-args jsou rozdeleny pro pripadne dalsi rozsireni
;
; Trida je potomkem mg-object kvuli moznosti napojeni na system udalosti
;
; Slot timer nema metody na nastaveni/ziskani, protoze jej nechci zpristupnovat,
; ale jeho pouziti omezit pouze na zverejnene metody

(defclass timer (mg-object)
  ((timer-function :initform nil)
   (period :initform 10)
   (timer :initform nil)
   (function-args :initform '())))

(defmethod initialize-instance ((ti timer) &rest args)
  (call-next-method)
  (set-events ti '(ev-timer-started ev-timer-stopped)))

(defmethod set-timer-function ((ti timer) function &rest args)
  (unless (functionp function)
    (error "Function must be provided."))
  (setf (slot-value ti 'timer-function) function)
  (set-function-args ti args))

(defmethod timer-function ((ti timer))
  (slot-value ti 'timer-function))

(defmethod set-function-args ((ti timer) args)
  (setf (slot-value ti 'function-args) args))

(defmethod function-args ((ti timer))
  (slot-value ti 'function-args))

(defmethod set-period ((ti timer) period)
  (unless (plusp period)
    (error "Period must be an positive number."))
  (setf (slot-value ti 'period) period))

(defmethod period ((ti timer))
  (slot-value ti 'period))

; Periodicke spousteni umoznuje primo funkce mp:make-timer a jeji pouziti je pohodlnejsi
; Casovac se spousti jen jednou
(defmethod start-timer ((ti timer))
  (unless (timer-function ti)
      (error "You have to provide timer-function before starting the timer."))
  (unless (slot-value ti 'timer)
    (setf (slot-value ti 'timer) (apply #'mp:make-timer (timer-function ti) (function-args ti)))
    (mp:schedule-timer-relative (slot-value ti 'timer) (period ti) (period ti))
    (send-event ti 'ev-timer-started)))

; Stejne tak zastaveni timeru umoznuje primo lispworks pomoci mp:unschedule-timer
(defmethod stop-timer ((ti timer))
  (when (mp:unschedule-timer (slot-value ti 'timer))
    (send-event ti 'ev-timer-stopped)
    (setf (slot-value ti 'timer) nil)))

;;;;;;;;;;;;;;;;;
;;Timer picture;;  
;;;;;;;;;;;;;;;;;

; Obecna trida pro obrazek s casovacem

(defclass timer-picture (picture)
  ((timer :initform nil)))

(defmethod initialize-instance ((tp timer-picture) &rest args)
  (call-next-method))

(defmethod set-timer ((tp timer-picture) timer)
  (set-delegate timer tp)
  (setf (slot-value tp 'timer) timer))

(defmethod ev-timer-started ((tp timer-picture) object)
  (send-event tp 'ev-change tp))

(defmethod ev-timer-stopped ((tp timer-picture) object)
  (send-event tp 'ev-change tp))

(defmethod timer ((tp timer-picture))
  (slot-value tp 'timer))

(defmethod start-timer ((tp timer-picture))
  (start-timer (timer tp)))

(defmethod stop-timer ((tp timer-picture))
  (stop-timer (timer tp)))

(defmethod window-destroyed ((tp timer-picture))
  (stop-timer tp)
  (call-next-method))

;;;;;;;;;;;;;;;;;;;;
;;Wheel of fortune;;
;;;;;;;;;;;;;;;;;;;;
;
; Ukazkova trida pro animaci s obrazkem
; Wheel of fortune = kolo stesti (cesky)
; Otacejici se kolo s barevnymi vysecemi pouzivane pro losovani
; V tomto pripade ma kolo pouze barevne "loukote" (nemoznost "kreslit" oblouky vs nehezky "kruh" z trojuhelniku)
; Roztaci se kliknutim leveho tlacitka mysi na ukazatel, zastavuje se kliknutim pravym tlacitkem

(defclass wheel-of-fortune (timer-picture) ())

(defmethod initialize-instance ((wf wheel-of-fortune) &rest args)
  (call-next-method)
  (let* ((circ (make-circle))
         (wedges (make-wedges 8 (center circ) (radius circ)))
         (ratchet (create-ratchet)))
    (set-items wf (append (list ratchet circ) wedges))
    (rotate wf (/ Pi 8) (center circ)) ;natoceni na vysec
    (set-timer wf (create-rotating-timer wf 1 (center circ)))))

; Prekryti metody rotate zajisti otaceni pouze kolem a nikoli "rucickou"
(defmethod rotate ((wf wheel-of-fortune) angle center)
  (mapcar
   (lambda (item) (rotate item angle center))
   (cdr (items wf))))

; Metoda contains-point-p vrati T i pri kliknuti na obvod kola, ale nepovazuji to za problem (takto je dle meho nazoru kod prehlednejsi)
(defmethod mouse-down ((wf wheel-of-fortune) button position)
  (when (contains-point-p wf position)
    (cond ((eql button :left) (start-spin wf))
          ((eql button :right) (stop-spin wf))))
  (call-next-method))

(defmethod start-spin ((wf wheel-of-fortune))
  (start-timer wf))

(defmethod stop-spin ((wf wheel-of-fortune))
  (stop-timer wf))

;;;;;;;;;;;;;;;;
;;Timer window;;
;;;;;;;;;;;;;;;;

(defclass timer-window (window) ())

(defmethod install-callbacks ((tw timer-window))
  (mg:set-callback (slot-value tw 'mg-window)
                   :destroy (lambda (mgw)
                              (declare (ignore mgw))
                              (window-destroyed (shape tw))))
  (call-next-method))

(defmethod ev-change ((tw timer-window) sender message &rest message-args)
  ; Verifikace predani zpravy
  (print sender *standard-output*)
  (print "was changed")
  (call-next-method))

;;;;
;;Nove metody pro tridy ze souboru 09.lisp
;;;;

; Prazdna metoda urcena pro implemetaci potomky
(defmethod window-destroyed ((shape shape)))

; Prekryti metody pro tridu picture a preposlani zpravy vsem potomkum
(defmethod window-destroyed ((picture picture)) 
  (send-to-items picture #'window-destroyed))

;;;;
;;Pomocne funkce pro vytvareni a
;;spravne napozicovanych grafickych objektu
;;Vyuzivaji funkci random-color ze souboru omg-examples.lisp
;;;;

(defun create-rotating-timer (wheel period center)
  (let ((timer (make-instance 'timer)))
    (set-period timer period)
    (set-timer-function timer #'rotate wheel (/ Pi 4) center)
    timer))

(defun make-circle ()
  (let ((circ (make-instance 'circle)))
    (set-radius circ 50)
    (move circ 100 100)
    circ))

(defun make-wedge-line (center length)
  (let ((start (make-instance 'point))
         (end (make-instance 'point))
         (line (make-instance 'polygon)))
    (set-x start (x center))
    (set-y start (y center))
    (set-x end (x center))
    (set-y end (+ (y center) length))
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

(defun create-ratchet ()
  (let ((triangle (make-instance 'polygon)))
    (set-items triangle
               (mapcar (lambda (coords)
                         (apply #'move (make-instance 'point) coords))
                       '((50 50) (65 35) (35 35))))
    (set-filledp triangle t)
    (move triangle 50 0)
    triangle))