% Askhsh 8a
roots([1,0,0,-8*j])

roots([1,0,0,0,0,0,-1])

roots([1,0,0,-2-2*j])

roots([1,0,0,0,0,32])

% Askhsh 8b
    % Askhsh 1
    syms z
    solve(z/4 + (2-j)/(4+j)*z - 1 == 1/2)
    syms w
    eqn1 = z*j + w + 2*(w-z) == 2*j
    eqn2 = z - j*w + 1 == 2
    sol = solve([eqn1,eqn2],[z,w]);
    sol.z;
    sol.w;
    
    % Askhsh 2
    syms z
    solve(z^3 == 8j)
    
    % Askshs 6
    syms z
    solve(z^7 - 1 == 0)
    solve(z^3 - (2 + 2*j) == 0)
    solve(z^5 + 32 == 0)
% Askhsh 8c
    % a
    t = -5:0.01:0; % Time vector
    x = 2*exp(t+4); % Function
    plot(t, x, 'b-'), grid on; % Visualize
    title('A simple sinusoid'); % Make plot pretty
    xlabel('Time (s)'); % Make plot pretty
    % b
    t = -5:0.01:5; % Time vector
    x = 2*cos(2*pi*t)+cos(10*pi*t); % Function
    plot(t, x, 'b-'), grid on; % Visualize
    title('A simple sinusoid'); % Make plot pretty
    xlabel('Time (s)'); % Make plot pretty
    % c
    t = -3:0.01:0; % Time vector
    x = t.^2 + 3*t + 2; % Function
    plot(t, x, 'b-'), grid on; % Visualize
    title('A simple sinusoid'); % Make plot pretty
    xlabel('Time (s)'); % Make plot pretty
    % d
    t = 0:0.01:2; % Time vector
    x = (t + log(t))./(t.^2-1); % Function
    plot(t, x, 'b-'), grid on; % Visualize
    title('A simple sinusoid'); % Make plot pretty
    xlabel('Time (s)'); % Make plot pretty

% Askhsh 9
[x,y] = meshgrid(-5:0.1:5, -5:0.1:5); % Create a complex grid
Z = x+j*y; % Create complex number in the grid
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

% Absolute Values
k = abs(f)
mesh(x,y,k)

% Phase
m = angle(f)
mesh(x,y,m)

% F(z) = sinc(z)
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
