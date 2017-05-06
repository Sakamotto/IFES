#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>
#include <math.h>

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

double ThreadCalculaArea(Targumentos *argumentos)
{
    int i;
    double area, dx, x1, x2, f1, f2;
    Targumentos arg;
    arg = *argumentos;
    double a=arg.a; double b=arg.b; int N=arg.N;
    printf("a=%10.2lf\tb=%10.2lf\tN=%d\n", a, b, N);
    dx   = (b-a)/N;
    area = 0.0;
    for (i = 0; i < N; i++){
        x1 = a + dx * i;
        x2 = a + dx * (i+1);
        f1 = f(x1);
        f2 = f(x2);
        area += AreaTrapezio(dx, f1, f2);
    }
    return area;
}

int main(int argc, char **argv)
{
	MPI_Status stat;
	
	int size, rank, N, i;
	double a, b, soma_local = 0.0, response = 0.0, segmento = 0.0;
	double integral = 0.0;
    	Targumentos arg;
	double *vet;
	
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

	segmento = (b-a)/size;
	arg.a = a + (rank*segmento),
	arg.b = a + (rank+1)*segmento,
	arg.N = N/size;
	vet = malloc(size * sizeof(double));

	soma_local = ThreadCalculaArea(&arg);
    
    if(rank == 0){
	integral = soma_local;
	for(i = 1; i < size; i++){
		MPI_Recv(&response, 1, MPI_DOUBLE, i, 0, MPI_COMM_WORLD, &stat);
		integral += response;
	}
		printf("Area= %.15lf\n", integral);
	}else{
		MPI_Send(&soma_local, 1, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD);
	}

	MPI_Finalize();
	
	return 0;
}

