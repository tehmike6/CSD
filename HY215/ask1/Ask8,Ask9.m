%Exercise 8

% This solves the exercise 2
roots([1,0,0,-8*1i])
% Exercise 6a
roots([1,0,0,0,0,0,-1])
% Exercise 6b
roots([1,0,0,-2-2*1i])
% Exercise 6c
roots([1,0,0,0,0,32])

%Exercise 8b
%Exercise 1.a
syms z
solve(z/4 + ( 2-1i)/(4+1i)* z - 1 ==1/2)
%Exercise 1.b
syms w
solution = solve([z* 1i + w +2*(w-z) == 2*1i,z -1i*w + 1 == 2],[z,w]);
solution.w
solution.z
%Exercise 2
syms z
solve(z^3 == 8j)
%Exercise 6
syms z
%a
solve(z^7 - 1 == 0)
%b
solve(z^3 - (2 +2*1i) == 0)
%c
solve( z^5 +32 == 0)

%Askhsh 8c
%8c.a
t = -5:0.01:0; % Time vector
x = 2*exp(t+4); % Function
plot(t, x, 'b-'), grid on; % Visualize
title('A simple sinusoid'); % Make plot pretty
xlabel('Time (s)'); % Make plot pretty
%8c.b
t = -5:0.01:5; % Time vector
x = 2*cos(2*pi*t)+cos(10*pi*t); % Function
plot(t, x, 'b-'), grid on; % Visualize
title('A simple sinusoid'); % Make plot pretty
xlabel('Time (s)'); % Make plot pretty
%8c.c
t = -3:0.01:0; % Time vector
x = t.^2 + 3*t + 2; % Function
plot(t, x, 'b-'), grid on; % Visualize
title('A simple sinusoid'); % Make plot pretty
xlabel('Time (s)'); % Make plot pretty
%8c.d
t = 0:0.01:2; % Time vector
x = (t + log(t))./(t.^2-1); % Function
plot(t, x, 'b-'), grid on; % Visualize
title('A simple sinusoid'); % Make plot pretty
xlabel('Time (s)'); % Make plot pretty

% Askhsh 9
[x,y] = meshgrid(-5:0.1:5, -5:0.1:5); % Create a complex grid
Z = x+1i*y; % Create complex number in the grid
f = 3*cos(Z); % Create function
subplot(121); % Split a plot into two parts (1st part)
mesh(x,y, real(f)); % Plot the real part
title('Real part of 3cos(z)'); % Make plot pretty
xlabel('Real(z)'); % Make plot pretty
ylabel('Imag(z)'); % Make plot pretty
subplot(122); % Split a plot into two parts (2nd part)
mesh(x,y, imag(f)); % Plot the imaginary part
title('Imaginary part of 3cos(z)'); % Make plot pretty
xlabel('Real(z)'); % Make plot pretty
ylabel('Imag(z)'); % Make plot pretty

% Diagram of the absolute values
mesh(x,y,abs(f))

% Phase of the function
mesh(x,y,angle(f))

% Function of sinc
[x,y] = meshgrid(-2:0.1:2, -2:0.1:2); % Create a complex grid
Z = x+1i*y; % Create complex number in the grid
f = sinc(Z); % Create function
subplot(121); % Split a plot into two parts (1st part)
mesh(x,y, real(f)); % Plot the real part
title('Real part of sinc'); % Make plot pretty
xlabel('Real(z)'); % Make plot pretty
ylabel('Imag(z)'); % Make plot pretty
subplot(122); % Split a plot into two parts (2nd part)
mesh(x,y, imag(f)); % Plot the imaginary part
title('Imaginary part of sinc)'); % Make plot pretty
xlabel('Real(z)'); % Make plot pretty
ylabel('Imag(z)'); % Make plot pretty

