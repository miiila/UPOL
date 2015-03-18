;;; tvorba paru pomoci funkce
(defun my-cons (a b)
              (setf par (cons a b))
              (lambda (arg)
                (if arg (car par) (cdr par)))) 

;;; nasledna implementace car...
(defun my-car (my-par)
                  (funcall my-par t))

;;; a cdr
(defun my-car (my-par)
                  (funcall my-par nil))

;;; prehozeni jejich slozek
(defun switch (my-par)
  (my-cons (my-cdr my-par) (my-car my-par)))

;;; faktorial rekurzivne
(defun fact (i)
                   (if (= i 1) 1 (* i (fact (- i 1)))))

;;; mapovani vysledku predikatu na senzam
(defun map-pred (predp list)
              (mapcar predp list))

;;; posun znaku o 3 dopredu
(defun caesar-char-encode (char)
                  (unless (characterp char)
                    (error "Caesar cipher works with character only."))
                  (code-char (+ 3 (char-code char))))

;;; posun znaku o 3 dozadu
(defun caesar-char-decode (char)
                  (unless (characterp char)
                    (error "Caesar cipher works with character only."))
                  (code-char (- (char-code char) 3)))

;;; zakodovani caesarovou sifrou
(defun caesar-encode (str)
                 (dotimes (x (length str))
                   (print (caesar-char-encode (aref str x)))))

;;; vylusteni caesarovy sifry
(defun caesar-decode (str)
                 (dotimes (x (length str))
                   (print (caesar-char-decode (aref str x)))))