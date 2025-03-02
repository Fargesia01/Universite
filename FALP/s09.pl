myLast(X, L) :- myLastDCG(X, L, [d]).

myLastDCG(X) --> [X].
myLastDCG(X) --> [_], myLastDCG(X).



convertToDec(X, L) :- convertDCG(X, _, L, []).

convertDCG(X, 1) --> [X], {X >= 0, X <= 1}.
convertDCG(V, Mult) --> [X], {X <= 1 , X >= 0}, convertDCG(VP, Multp), {Mult is Multp * 2, V is VP + X * Mult}.
