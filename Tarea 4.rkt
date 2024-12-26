#lang scheme
(current-namespace (make-base-namespace))

;; Crea una lista con valores hasta n en forma ascendente
;;
;; n: numero de elementos que tendra la lista
(define (make_lista n)
  (let val (( m(- n 1)) (list_val '()))
    (if (< m 0)  
        list_val
        (val (- m 1) (cons m list_val)))))
;(make_lista 10)

;;--------------------------------------------------------------------------------------------------------------
(define (inverso lista n)
  (let ((values (make_lista n)))
    (let aux ((l1 lista) (l2 values)); funcion auxiliar para loopear valores de lista y removerlos
      (if (null? l1)
          l2
          (aux (cdr l1) (remove (car l1) l2))))))
;(inverso '(1 3 7) 10)

;;--------------------------------------------------------------------------------------------------------------
;(define (umbral_simple lista umbral tipon)
;  (let aux ((l1 lista) (l2 (filter (lambda (x) (> x 5)) lista)) (index 0) (l4 '()))
;    (if (null? l1)
;        l2
;        (append (aux (cdr l1) l2 (+ index 1) l4) l4))))              
;(umbral_simple '(15 2 1 3 27 5 10) 5 #\m)

(define (umbral_cola lista umbral tipon)
  (let aux ((l1 lista) (l2 '()) (index 0))
    (if (null? l1)
        (reverse l2)
        (cond ((eq? tipon #\M); asumimos que solo se puede ingresar M o m,ya que dejamos el else para m
               (if (> (car l1) umbral) 
                   (aux (cdr l1) (cons index l2) (+ index 1))  
                   (aux (cdr l1) l2 (+ index 1))))
              ((eq? tipon #\m)
               (if (< (car l1) umbral) 
                   (aux (cdr l1) (cons index l2) (+ index 1))
                   (aux (cdr l1) l2 (+ index 1))))))))                                                    
;(umbral_cola '(15 2 1 3 27 5 10) 5 #\l) 
;(umbral_cola '(15 2 1 3 27 5 10) 5 #\m)

;;--------------------------------------------------------------------------------------------------------------
;(define (modsel_simple lista seleccion f)

(define (modsel_cola lista seleccion f) 
  (let aux ((l1 lista) (l2 '()) (index 0) (l3 (sort seleccion <))); sorteamos l3 en caso de que venga desordenada, para que matchee index
    (if (null? l1)   
        (reverse l2) 
        (cond ((null? l3) ;si se remplaza con un if, cuando l3 es null tira un error
               (aux (cdr l1) (cons (car l1) l2) (+ index 1) l3 ))
              ((eq? index (car l3)); matcheo del index, se hace la funcion lambda
               (aux (cdr l1) (cons (f (car l1)) l2) (+ index 1) (cdr l3)))
              (else
               (aux (cdr l1) (cons (car l1) l2) (+ index 1) l3 ))))))
;(modsel_cola '(15 2 1 3 27 5 10) '(0 4 6) (lambda (x) (modulo x 2)))
;(modsel_cola '(15 2 1 3 27 5 10) '(3 1 2) (lambda (x) (+ x 5)))

;;--------------------------------------------------------------------------------------------------------------
(define (estables lista umbral fM fm) ; usamos las funciones creadas, sacamos el length y solo nos queda unir el res
  (let ((l1 (length (umbral_cola (modsel_cola lista (make_lista (length lista)) fM) umbral #\M)))
        (l2 (length(umbral_cola (modsel_cola lista (make_lista (length lista)) fm) umbral #\m))))
    (list l1 l2))) 
;(estables '(15 2 1 3 27 5 10) 5 (lambda (x) (/ x 2)) (lambda (x) (* x 2)))

;;--------------------------------------------------------------------------------------------------------------
(define (query lista pos op params)
  (let aux ((index 0) (choice lista)) 
    (if (eq? index pos) ; car choice es la lista a manipular
        (cond
          ((eq? 1 op) (umbral_cola (car choice) (car params) (car(cdr params))))
          ((eq? 2 op) (modsel_cola (car choice) (car params) (eval(car(cdr params)))))
          ((eq? 3 op) (estables (car choice) (car params) (eval(car(cdr params))) (eval(car(cdr(cdr params)))))))
        (aux (+ index 1) (cdr choice)))))
;(query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 1 1 '(1 #\M))
;(query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 0 2 '((0 4) (lambda(x) (+ x 100))))
;(query '((0 1 2 3 4) (4 3 2 1 0) (15 2 1 3 27 5 10)) 2 3 '(5 (lambda (x)(/ x 2)) (lambda (x) (* x 2))))









    
