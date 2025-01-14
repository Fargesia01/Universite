myLength(0, []).

myLength(N, [_ | T]) :- 
  myLength(N1, T),
  N is N1 + 1.


myAppend([], L2, L2).

myAppend([H | T], L2, [H | T2]) :- myAppend(T, L2, T2).



myLast(X, [X]).
myLast(X, [_ | T]) :- myLast(X, T).


