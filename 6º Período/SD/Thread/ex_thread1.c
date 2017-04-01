#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>



void* f1(void* seconds){
	
	printf("Waiting %d seconds!\n", ((int)seconds));
	sleep(5);
	
	pthread_exit((void*)13);
	//return NULL;	
}


int main(int argc, char **argv)
{
	
	pthread_t thread1;
	void* ret;
	
	pthread_create(&thread1, NULL, f1, (void*)5000);
	pthread_join(thread1, &ret);
	
	printf("Retorno: %d\n", (int)ret);
	
	
	return 0;
}

