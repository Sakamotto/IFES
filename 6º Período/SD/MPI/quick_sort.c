#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>


int cmpfunc (const void * a, const void * b)
{
   return ( *(int*)a - *(int*)b );
}


int* merge(int *vet1, int size1, int *vet2, int size2){
	int tamanho_total = size1 + size2;
	int *result = malloc(tamanho_total * sizeof(int));
	int i = 0, j = 0, k = 0;
	
	while(i < size1 && j < size2){
		
		if(vet1[i] < vet2[j]){
			result[k] = vet1[i];
			i++;
		}else{
			result[k] = vet2[j];
			j++;
		}
		
		k++;				
	}
	
	while(i < size1){
		result[k] = vet1[i];
		i++;
		k++;
	}
	
	while(j < size2){
		result[k] = vet2[j];
		j++;
		k++;
	}
	
	return result;
	
}


int main(int argc, char **argv)
{
	MPI_Init(&argc, &argv);
	
	int rank, size;
	int para_ordenar[10] = {9, 5, 6, 8, 3, 10, 88, 96, 105, 60};
	int vetor2[5];
	int i, flag = 0;
	int *result;
	
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	
	if(rank == 0){
		MPI_Send(&para_ordenar[5], 5, MPI_INT, 1, flag, MPI_COMM_WORLD); // envio o vetor para o processo 1 ordenar ...
		qsort(para_ordenar, 5, sizeof(int), cmpfunc);
		MPI_Recv(vetor2, 5, MPI_INT, 1, flag, MPI_COMM_WORLD, MPI_STATUS_IGNORE); // recebo o vetor ordenado pelo processo 1
		
		result = merge(para_ordenar, 5, vetor2, 5); // ordenação dos dois vetores ...
		
		for(i = 0; i < 10; i++){
			printf("%d\n", result[i]);
		}
		
	}else{
		MPI_Recv(vetor2, 5, MPI_INT, 0, flag, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
		qsort(vetor2, 5, sizeof(int), cmpfunc);
		MPI_Send(vetor2, 5, MPI_INT, 0, flag, MPI_COMM_WORLD); // envio o vetor para o processo 0 já ordenado ...
	}
	
	
	MPI_Finalize();
	
	return 0;
}

