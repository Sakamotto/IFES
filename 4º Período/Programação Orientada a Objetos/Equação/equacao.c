#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "equacao.h"

struct equacao{
    double a, b, c, delta;
};

double getA(Equacao *eq){
    return eq->a;
}

double getB(Equacao *eq){
    return eq->b;
}

double getC(Equacao *eq){
    return eq->c;
}

double getDelta(Equacao *eq){
	return eq->delta;
}

void setA(Equacao *eq, double valor){
    eq->a = valor;
}

void setB(Equacao *eq, double valor){
    eq->b = valor;
}

void setC(Equacao *eq, double valor){
    eq->c = valor;
}

void setDelta(Equacao *eq, double delta){
	eq->delta = delta;
}

double calularDelta(double a, double b, double c){
	return ((b*b) - 4*a*c);
}

double* calcularRaizes(double a, double b, double delta){
	double* raizes = malloc(sizeof(double) * 2);
	
	raizes[0] = (-b + sqrt(delta)) / 2*a;
	raizes[1] = (-b - sqrt(delta)) / 2*a;
	
	return raizes;
}

Equacao* criaEquacao(){
	return malloc(sizeof(Equacao));
}

