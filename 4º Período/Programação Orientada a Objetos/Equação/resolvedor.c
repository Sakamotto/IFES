/*
 * resolvedor.c
 * 
 * Copyright 2016 Cristian <cristian@cristian>
 */


#include <stdio.h>
#include <stdlib.h>

#include "entrada.h"
#include "equacao.h"
#include "tratadorDeExecao.h"
#include "saida.h"


int main(int argc, char **argv)
{
	Equacao *eq = criaEquacao();
	double a, b, c, *raizes = malloc(sizeof(double) * 2);
	double delta;
		
	printf("A: ");
	a = lerCoeficientes();
	setA(eq, a);
	
	printf("B: ");
	b = lerCoeficientes();
	setB(eq, b);
	
	printf("C: ");
	c = lerCoeficientes();
	setC(eq, c);
	
	setDelta(eq, calularDelta(a, b, c));
	delta = getDelta(eq);
	
	printf("\nDelta: %.2f\n", delta);
	
	if(execaoDeltaMenorZero(delta) < 0){
		imprimeMensagemDeErro("\nErro! Delta menor que zero!\n");
	}else{
		raizes = calcularRaizes(getA(eq), getB(eq), delta);
		imprimeResultado(raizes[0], raizes[1]);
	}
	
	
	return 0;
}

