% S11

% Given:
list_to_term_without_functor([X], X).
list_to_term_without_functor([X, Y | Rest], (X, F)) :-
    list_to_term_without_functor([Y | Rest], F).


%Mon implémentation

% grammar(-RL, +ListeInput, +Reste)
% Soit Rule la première règle puis la liste des règles suivantes appelées récursivement ainsi que le base case pour finir
grammar([Rule | RL]) --> rule(Rule), grammar(RL).
grammar([]) --> [].

%rule(-Rule) --> ruleName, ['->'], ruleBodies.
% Tout est plus ou moins self-explanatory, la règle est constituée du nom --> le corps de la règle (ici toutes les disjonctions sont mises dans la même règle ce qui est faux selon la donnée, CF rapport personnel)
% Le corps de la règle (liste des disjonctions (=Termlist)) est traité avec la fonction donnée en annexe pour la transformer en termes
rule(RuleName --> Term) -->
  ruleName(RuleName),
  ['->'],
  ruleBodies(Termlist),
  {list_to_term_without_functor(Termlist, Term)}.

%ruleBodies(-TermList) -->
% Ici il y soit une liste de termes soit plusieurs listes (ou les disjonctions qui sont assemblées dans la même règle)
% Dans l'exemple de la donnée ruleBodies correspond à (r_2, r_1), r_1      -> deux corps simple d'une même règle r_1
ruleBodies([Term]) --> ruleBody(Term).
ruleBodies([Term | Rest]) --> ruleBody(Term), ['|'], ruleBodies(Rest).

%ruleBody(-Term) --> partie simple de la règle
% On vient assembler chacune des "briques" en un corps finit. (ex r_1 --> r_2, r_1, ici r_2, r_1 correspond à un RuleBody)
ruleBody(Term) --> ruleBodyPart(Term).
ruleBody((Term, Rest)) --> ruleBodyPart(Term), ruleBody(Rest).

%ruleBodyPart(-Term) --> brique les plus simples d'une règle(nom de règle ou atome)
ruleBodyPart(RuleName) --> ruleName(RuleName).
ruleBodyPart([Atom]) --> [Atom], {atom(Atom), \+ atom_chars(Atom, ['r', '_']), Atom \= '->', Atom \= '|'}.

%ruleName(-RuleName) --> extrait le nom de la règle avec la bonne syntaxe
ruleName(Rule) --> [Rule], {atom(Rule), sub_atom(Rule, 0, 2, _, 'r_')}.


% Ajoute dynamiquement les règles données dans la base de donnée

% Les deux lignes commentées ci dessous sont les lignes de bases qui font appel à grammar()
% mais comme grammar n'est pas bien formatté (CF rapport), j'ai fais les lignes en dessous pour appeler le prédicat directement depuis le prompt
%
%generateRules(Grammar) :- 
%  grammar(Rules, Grammar, []),
generateRules(Rules) :-
  maplist(assert_rule, Rules).

assert_rule(DcgRule) :-
  expand_term(DcgRule, PrologRule),
  %writeln(PrologRule),
  %writeln(DcgRule),
  assertz(PrologRule).


% Finalement dernier commentaire, les deux writeln sont laissés en comm car je lorsque je print PrologRule, l'output est étonnant:
% [(:-non_terminal(user:r_1/2)),(r_1(_6182,_6204):-r_2(_6182,_6258),r_1(_6258,_6204))]
%[(:-non_terminal(user:r_1/2)),(r_1(_6612,_6634):-r_2(_6612,_6634))]
%[(:-non_terminal(user:r_2/2)),(r_2(_6978,_7000):-_6978=[a|_7000])]
%[(:-non_terminal(user:r_2/2)),(r_2(_7344,_7366):-_7344=[b|_7366])]
%
%Malgré que DcgRule soit bien formatté, de part cette sortie étonnante de expand_term, je n'arrive pas à call r_1 directement après. 
%Mais en théorie ce code fonctionne pour ajouter dynamiquement la règle et c'est sans doute un détail qui m'échappe à propos de la fonction expand_term(2).
