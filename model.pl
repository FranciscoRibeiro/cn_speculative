recommended(F, yes) :-
  rating(F, R),
  R >= 9.
recommended(F, yes) :-
  popularity(F, P),
  rating(F, R),
  P < 5,
  R >= 8.
recommended(F, yes) :-
  genre(F, sci-fi),
  duration(F, D),
  rating(F,R),
  D > 60,
  R > 7.5.
recommended(F, yes) :-
  tooLong(F,no),
  masterpiece(F,yes).
recommended(F, yes) :-
  trendingMovie(F, yes).
recommended(F, no) :-
  tooLong(F, yes).
recommended(F, no) :-
  topGenre(F, no),
  masterpiece(F, no).

tooLong(F, yes) :-
  duration(F, D),
  D > 240.
tooLong(_, no).

masterpiece(F, yes) :-
  rating(F, R),
  popularity(F, P),
  R >= 8.3,
  P < 3.
masterpiece(_, no).

topGenre(F, no) :-
  genre(F, G),
  G \= sci-fi,
  G \= action,
  G \= thriller.
topGenre(_, yes).

% A movie is trending if its popularity is less than or equal to 2, meaning
% that it is in the top 2 movies of the ranking.
trendingMovie(F, yes) :-
  popularity(F, P),
  P =< 2.
trendingMovie(_, no).


exists(matrix).
exists(inception).
