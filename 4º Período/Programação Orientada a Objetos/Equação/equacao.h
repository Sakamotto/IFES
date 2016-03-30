#ifndef EQUACAO_H
#define EQUACAO_H

struct equacao;
typedef struct equacao Equacao;

double getA(Equacao *eq);
double getB(Equacao *eq);
double getC(Equacao *eq);
double getDelta(Equacao *eq);


void setDelta(Equacao *eq, double delta);
void setA(Equacao *eq, double valor);
void setB(Equacao *eq, double valor);
void setC(Equacao *eq, double valor);

double calularDelta(double a, double b, double c);
double* calcularRaizes(double a, double b, double delta);

Equacao* criaEquacao();

#endif // EQUACAO_H
