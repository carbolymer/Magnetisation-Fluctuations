%
%	Skrypt generujący wykresy fluktuacji i magnetyzacji w zaleznosci od parametru beta*H
%	autor:	Mateusz Galazyn
%

clear;

% przyjmuje:
% x - iloczyn beta H
% N - ilosc spinow, skalar!
% zwraca:
% fl - fluktuacja^2
% rel_fl - fluktuacja / M
% M - magnetyzacja
function [fl, rel_fl, M ] = simulate(x,N)
	len = length(x);
	a = transpose(exp(x)./cosh(x)./2);	% p-stwo spinu +1
	for i=1:N
		awide(:,i) = a;
	end
	spins = (rand(len,N) <  awide).*2-1; % generacja spinow
	M = sum(spins,2); % suma po wierszach
	ES = M ./ N;
	fl = N.*(1-ES.^2);
	rel_fl = sqrt(fl)./abs(M);
endfunction

x = -4.5:0.01:4.5; % stosunek H/T
N = 200;
t_fl = N.*(1-tanh(x).^2); % wartosc teoretyczna
t_M = N.*tanh(x);
t_rel_fl = sqrt(t_fl)./abs(t_M);
[fl,rel_fl,M] = simulate(x,N);

figure(1);
plot(x,fl,"+k;symulacja;",x,t_fl,"-r;teoria;");
title(["Fluktuacje magnetyzacji dla N = " num2str(N)]);
xlabel("βH");
ylabel("Fluktuacja magnetyzacji σ²");
print(["fl_" num2str(N) ".png"],"-S640,500");
close(1);

figure(2);
plot(x,rel_fl,"+k;symulacja;",x,t_rel_fl,"-r;teoria;");
title(["Względna fluktuacja magnetyzacji dla N = " num2str(N)]);
xlabel("βH");
ylabel("Względna fluktuacja σ/|M|");
print(["rel_fl_" num2str(N) ".png"],"-S640,500");
close(2);

figure(3);
plot(x,M,"+k;symulacja;",x,t_M,"-r;teoria;");
title(["Magnetyzacja dla N = " num2str(N)]);
xlabel("βH");
ylabel("Magnetyzacja M");
print(["M_" num2str(N) ".png"],"-S640,500");
close(3);

