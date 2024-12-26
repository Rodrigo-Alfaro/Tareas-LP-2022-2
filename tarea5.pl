%Implementamos el predicado con las condiciones que nos dan, y teniendo en cuenta que se va a ejecutar "alreves" por el backtracking, por lo cual en primero ponemos las 
%condiciones base, todas las listas vacias, solo un elemento, el cual seria de posicion impar, y cuando tenemos 2 elementos, uno par y otro impar, 
%luego implementamos el predicado como tal,con las tails de nuestros parametros (listas).
sepparimpar([],[],[]).
sepparimpar([I],[I],[]).
sepparimpar([I,P],[I],[P]).
sepparimpar([I,P|L],[I|IT],[P|PT]):- sepparimpar(L,IT,PT),!.

%------------------------------------------------------------------------------------------------
%Falta que los members se ejecuten de manera dinamica, para poner los condiciones necesarias para su correcto funcionamiento, ya que estan hardcodeados,
%y solo funciona cuando tenemos 2 numeros para generar, como el ejemplo 1 y 3.
todosrango(L, Min, Max):- 
X is Max-1,
    member(Min,L),
    member(X,L).
%todosrango(L,Max,Max):- true.

%todosrango(L, Min, Max):- 
%    X is Max-1,
%    length(L,X),
%    between(Min, X, J),
%    member(L,J),!.
%
%rango(X):-
    %member(X,L),
    %X is X -1,
    %rango(X).
%-----------------------------------------------------------------------------
%Para este predicado obtenemos el mayor y menor valores de L, el minimo lo operamos con Min, al mayor le sumamos uno por el intervalo abierto, y
%operamos con Max, con esto obtenemos el resultado que esperamos, ya que en caso contrario, los operadores serian false, dandonos el resultado
%que esperamos.
rangomax(L,Min,Max):-
    min_list(L,X),
    max_list(L,Y),
    Min is X,
    N is Y+1,
    Max is N.

%-----------------------------------------------------------------------------
%rotatelist rota a la lista, al igual que un ROR, simplemente movemos la head a la tail de la lista
rotatelist([LH|LT], LR) :- append(LT, [LH], LR).
%between pero adaptado a listas
betweenlist(I1,I1,[I1]):-!.
betweenlist(I1,I2,[I1|IT]):-
    I1 =< I2,
    R is I1+1,
    betweenlist(R,I2,IT).
%Creamos una lista del tamano pedido, luego se calculan los intervalos y hacemos una lista con ellos con betweenlist, luego los "juntamos" con sepparimpar, hay 4 versiones
%de este predicado, lo cual representa las diferentes combinaciones para las posiciones dentro de L, las cuales varian gracias al ROR con el cual se aplica el sepparimpar,
%pero como vemos, esto solo funciona con cuando el largo de L es de 4, como en el ejemplo 1, para el 2 ejemplo, solo nos entrega un resultado y luego false.
chicograndechico(L,Min,Max):- % normales
    X is Max-Min,
    length(L,X),
    %member(_,L),
    MixMax is /(+(Max,Min),2),
    MinMax is ceiling(MixMax),
    AAAA is -(MinMax,1),
    EEEE is -(Max,1),
    betweenlist(Min,AAAA,R1),
    betweenlist(MinMax,EEEE,R2),
    sepparimpar(L,R1,R2).

chicograndechico(L,Min,Max):-% ROR primero
    X is Max-Min,
    length(L,X),
    %member(_,L),
    MixMax is /(+(Max,Min),2),
    MinMax is ceiling(MixMax),
    MinMax is /(+(Max,Min),2),
    AAAA is -(MinMax,1),
    EEEE is -(Max,1),
    betweenlist(Min,AAAA,R1),
    rotatelist(R1,L1),
    betweenlist(MinMax,EEEE,R2),
    sepparimpar(L,L1,R2).

chicograndechico(L,Min,Max):- % ROR segundo
    X is Max-Min,
    length(L,X),
    %member(_,L),
    MixMax is /(+(Max,Min),2),
    MinMax is ceiling(MixMax),
    MinMax is /(+(Max,Min),2),
    AAAA is -(MinMax,1),
    EEEE is -(Max,1),
    betweenlist(Min,AAAA,R1),
    betweenlist(MinMax,EEEE,R2),
    rotatelist(R2,L2),
    sepparimpar(L,R1,L2).
    
chicograndechico(L,Min,Max):- % ROR ambos
    X is Max-Min,
    length(L,X),
    %member(_,L),
    MixMax is /(+(Max,Min),2),
    MinMax is ceiling(MixMax),
    MinMax is /(+(Max,Min),2),
    AAAA is -(MinMax,1),
    EEEE is -(Max,1),
    betweenlist(Min,AAAA,R1),
    rotatelist(R1,L1),
    betweenlist(MinMax,EEEE,R2),
    rotatelist(R2,L2),
    sepparimpar(L,L1,L2).
    


    
    
