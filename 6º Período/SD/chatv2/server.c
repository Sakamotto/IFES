/* A simple server in the internet domain using TCP
   The port number is passed as an argument */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <pthread.h>

#define N_THREADS 5
#define N_CLIENTS 5

pthread_t thread_read[N_THREADS], thread_write[N_THREADS];
int newsockfd[N_CLIENTS];

void error(char *msg)
{
    perror(msg);
    exit(1);
}


void* server_read(void* sock_client){
	int n, i;
	int client = *(int*) sock_client;
	char buffer[256];
	bzero(buffer, 256);
	
	while(strcmp(buffer, "bye") != 0){
		bzero(buffer, 256);
		n = read(client, buffer, 255);

		if (n < 0){
			error("ERROR reading from socket");
		}
		
		for(i = 0; i < N_CLIENTS; i++){
			n = write(newsockfd[i], buffer, strlen(buffer));
		}
		//printf("> Here is the message: %s\n", buffer);

	}
	n = write(client, "bye", strlen("bye"));
	//pthread_cancel(thread_write);
	
	return 0;
}

/*void* server_write(void* sock_client){
	int n;
	int client = *(int*) sock_client;
	char buffer_out[256];
	bzero(buffer_out, 256);

	while(strcmp(buffer, "bye\n") != 0){
		bzero(buffer, 256);
		printf("> [Server] Please enter the message: ");
		fgets(buffer_out, 255, stdin);
		
		n = write(client, buffer, strlen(buffer) -1);

		if (n < 0){
			error("ERROR writing to socket");
		}
	}
	//pthread_cancel(thread_read);

	return 0;		
}*/


int main(int argc, char *argv[])
{
	int sockfd, portno;
	int clilen, i;
	struct sockaddr_in serv_addr, cli_addr;

	if (argc < 2) {
		fprintf(stderr,"ERROR, no port provided\n");
		exit(1);
	}
	
	// Criando um socket via tcp
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
		error("ERROR opening socket");
	bzero((char *) &serv_addr, sizeof(serv_addr));

	portno = atoi(argv[1]);
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(portno);

	if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0)
		error("ERROR on binding");

	listen(sockfd, 5);
	clilen = sizeof(cli_addr);

	// Cria as threads para ouvir e escrever
	for(i = 0; i < N_THREADS; i++){
		newsockfd[i] = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
		pthread_create(&thread_read[i], NULL, server_read, &newsockfd[i]);
		if (newsockfd[i] < 0) 
			error("ERROR on accept");
	}
	
	for(i = 0; i < N_THREADS; i++){
		pthread_join(thread_read[i], NULL);
	}
	
	for(i = 0; i < N_CLIENTS; i++){
		close(newsockfd[i]);
	}

	return 0; 
}
