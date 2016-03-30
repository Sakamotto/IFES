/*
 * equacaov2.c
 * 
 * Copyright 2016 Cristian <cristian@cristian>
 * 
 * 
 */


#include <stdio.h>
#include <stdlib.h>
#include "equacaov2.h"

#define EQ3G 3
#define EQ2G 2
#define EQ1G 1

struct equacao{
	double c, d;
};

struct equacao2{
	Equacao *e;
	double b;
};

struct equacao3{
	Equacao2 *e;
	double a;
};

double getA(void *eq, int kind){
	if(kind == 3){
		Equacao3 *e = (Equacao3*) eq;
		return e->a;
	}else{
		return -999;
	}
}

double getB(void *eq, int kind){
	if(kind == 3){
		Equacao3 *e = (Equacao3*) eq;
		return e->e->b;
	}else if(kind == 2){
		Equacao2 *e = (Equacao2*) eq;
		return e->b;
	}else{
		return -999;
	}
}

double getC(void *eq, int kind){
	if(kind == 3){
		Equacao3 *e = (Equacao3*) eq;
		return e->e->e->c;
	}else if(kind == 2){
		Equacao2 *e = (Equacao2*) eq;
		return e->e->c;
	}else if(kind == 1){
		Equacao *e = (Equacao*) eq;
		return e->c;
	}else{
		return -999;
	}	
}

double getD(void *eq, int kind){
	if(kind == 3){
		Equacao3 *e = (Equacao3*) eq;
		return e->e->e->d;
	}else if(kind == 2){
		Equacao2 *e = (Equacao2*) eq;
		return e->e->d;
	}else if(kind == 1){
		Equacao *e = (Equacao*) eq;
		return e->d;
	}else{
		return -999;
	}
}

void setA(void *eq, double valor, int kind){
	Equacao3 *e3 = (Equacao3*) eq;
	e3->a = valor;
}

void setB(void *eq, double valor, int kind){
	if(kind == 3){
		Equacao3 *e = (Equacao3*) eq;
		e->e->b = valor;
	}else{
		Equacao2 *e = (Equacao2*) eq;
		e->b = valor;
	}
}

void setC(void *eq, double valor, int kind){
	if(kind == 3){
		Equacao3 *e = (Equacao3*) eq;
		e->e->e->c = valor;
	}else if(kind == 2){
		Equacao2 *e = (Equacao2*) eq;
		e->e->c = valor;
	}else if(kind == 1){
		Equacao *e = (Equacao*) eq;
		e->c = valor;
	}
}

void setD(void *eq, double valor, int kind){
	if(kind == 3){
		Equacao3 *e = (Equacao3*) eq;
		e->e->e->d = valor;
	}else if(kind == 2){
		Equacao2 *e = (Equacao2*) eq;
		e->e->d = valor;
	}else if(kind == 1){
		Equacao *e = (Equacao*) eq;
		e->d = valor;
	}
}



Equacao3 *criaEquacao3g(){
	Equacao3 *eq = malloc(sizeof(Equacao3));
	eq->e = malloc(sizeof(Equacao2));
	eq->e->e = malloc(sizeof(Equacao));
	
	return eq;
}

Equacao2 *criaEquacao2g(){
	Equacao2 *eq = malloc(sizeof(Equacao2));
	eq->e = malloc(sizeof(Equacao));
	
	return eq;
}

Equacao *criaEquacao1g(){
	Equacao *eq = malloc(sizeof(Equacao));	
	return eq;
}

// Calcula as raízes de uma equação do 3º grau
//~ double calcularRaizes(){
	
//~ }


int main(int argc, char **argv)
{
	//~ Equacao *e1 = malloc(sizeof(Equacao));
	//~ Equacao2 *e2 = malloc(sizeof(Equacao2));
	//~ Equacao3 *e3 = criaEquacao3g();
	
	//~ setD(e3, 6, EQ3G);
	//~ setC(e3, 5, EQ3G);
	//~ setB(e3, 4, EQ3G);
	//~ setA(e3, 3, EQ3G);

	//~ printf("D: %.2lf\n", getD(e3, EQ3G));
	//~ printf("C: %.2lf\n", getC(e3, EQ3G));
	//~ printf("B: %.2lf\n", getB(e3, EQ3G));
	//~ printf("A: %.2lf\n", getA(e3, EQ3G));
	
	//~ Equacao2 *e2 = criaEquacao2g();
	
	//~ setD(e2, 6, EQ2G);
	//~ setC(e2, 5, EQ2G);
	//~ setB(e2, 11, EQ2G);
	
	//~ printf("D: %.2lf\n", getD(e2, EQ2G));
	//~ printf("C: %.2lf\n", getC(e2, EQ2G));
	//~ printf("B: %.2lf\n", getB(e2, EQ2G));
	

	
	
	
	
	return 0;
}

