% Askisi 7

% a
syms t;
x = sin(4*t)*cos(5*t);
Result = int(x, t, 0, pi)

% b
syms t;
x = 1/(5/4 - cos(t));
Result = int(x, t, 0, 2*pi)

% c
syms t;
x = log(t)/(1+t);
Result = int(x, t, 0, 1)

% d
syms t;
x = (t+2)*(t-2)/t.^2;
Result = int(x, t, 1, 2)

% Askisi 8