#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>
#include <math.h>

double integral = 0.0;

typedef struct
{
    double a;
    double b;
    int N;
} Targumentos;

double AreaTrapezio(double dx, double h1, double h2)
{
    return (dx*(h1+h2)/2);
}

double f(double x)
{
    return (4*sqrt(1-x*x));
}

void ThreadCalculaArea(Targumentos *argumentos)
{
    int i;
    double area, dx, x1, x2, f1, f2;
    Targumentos arg;
    arg = *argumentos;
    double a=arg.a; double b=arg.b; int N=arg.N;
    printf("a=%10.1lf\tb=%10.1lf\tN=%d\n", a, b, N);
    dx   = (b-a)/N;
    area = 0.0;
    for (i = 0; i < N; i++){
        x1 = a + dx * i;
        x2 = a + dx * (i+1);
        f1 = f(x1);
        f2 = f(x2);
        area += AreaTrapezio(dx, f1, f2);
    }
    integral += area;
}

int main(int argc, char **argv)
{
	MPI_Status stat;
	
	int size, rank, N, i;
	double a, b;
    Targumentos arg1, arg2, arg3;
    double teste1[3], teste2[3], teste3[3];
	
	MPI_Init(&argc, &argv);
	
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	
    if (argc < 4){
        printf("Numero de argumentos insuficiente...\n");
        exit(-1);
    }
    
    a = (double) atof(argv[1]); // limite inferior
    b = (double) atof(argv[2]); // limite superior
    N = atoi(argv[3]); // quantidade de iterações

    /* arg1 */
    arg1.a = a, // 1: 1*a = 0.0
    arg1.b = (a+b)/2, // 1: 1*(1/4) = 0.25
    arg1.N = N/2;
    teste1[0] = a;
    teste1[1] = (a+b)/2;
    teste1[2] = N/2;
    
    teste3[0] = 0;
    teste3[1] = 0;
    teste3[2] = 0;
    
    
    /* arg2 */
    arg2.a = (a+b)/2, // 2: 1*(1/4)  = 0.25 | 3: 2*(1/4) = 0.50 | 4: 3*(1/4) = 0.75
    arg2.b = b,  // 2*(1/4) = 0.50 | 3*(1/4) = 0.75 | 4*(1/4) = 1
    arg2.N = N/2;
    
    teste2[0] = (a+b)/2;
    teste2[1] = b;
    teste2[2] = N/2;
    
    if(rank == 0){
		MPI_Send(&teste1, 3, MPI_DOUBLE, 1, 0, MPI_COMM_WORLD);
		ThreadCalculaArea(&arg1);
		//MPI_Recv(&teste3, sizeof(Targumentos), MPI_DOUBLE, 1, 0, MPI_COMM_WORLD, &stat);
	}else{
		MPI_Recv(&teste2, 3, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD, &stat);
		//~ arg2.a = (a+b)/2,
		//~ arg2.b = b,
		//~ arg2.N = N/2;
		ThreadCalculaArea(&arg2);
		//MPI_Send(&teste2, sizeof(Targumentos), MPI_DOUBLE, 0, 0, MPI_COMM_WORLD);
	}
    

    printf("Rank: %d | Area= %.15lf\n", rank, integral);

	MPI_Finalize();
	
	return 0;
}

