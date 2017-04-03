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

#define END_CHAT -999

void error(char *msg)
{
    perror(msg);
    exit(1);
}


void* server_read(void* sock_client){
	int n;
	int client = *(int*) sock_client;
	char buffer[256];
	bzero(buffer, 256);
	while(strcmp(buffer, "bye") != 0){
		bzero(buffer, 256);
		n = read(client, buffer, 255);

		if (n < 0){
			error("ERROR reading from socket");
		}
		printf("Here is the message: %s\n", buffer);

	}
	printf("[server_read] NÃO ENTREI NO WHILE\n");
	n = write(client, "bye", strlen("bye"));
	//exit(0);	
	/*
	if(strcmp(buffer, "bye") == 0){
		
		//pthread_exit((void*) END_CHAT);
		exit(0);
	}*/
	
	return 0;
}

void* server_write(void* sock_client){
	int n;
	int client = *(int*) sock_client;
	char buffer_out[256];
	bzero(buffer_out, 256);
	while(strcmp(buffer_out, "bye\n") != 0){
		bzero(buffer_out, 256);
		printf("[Server] Please enter the message: ");
		fgets(buffer_out, 255, stdin);

		n = write(client, buffer_out, strlen(buffer_out) -1);

		if (n < 0){
			error("ERROR writing to socket");
		}
	}
	printf("[server_write] NÃO ENTREI NO WHILE\n");
	exit(0);
	

	/*if((strcmp(buffer_out, "bye\n") == 0)){
		//pthread_exit((void*) END_CHAT);
		exit(0);
	}*/

	return 0;		
}


int main(int argc, char *argv[])
{
	int sockfd, newsockfd, portno;
	int clilen;
	struct sockaddr_in serv_addr, cli_addr;

	pthread_t thread_read, thread_write;

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

	listen(sockfd,5);
	clilen = sizeof(cli_addr);
	newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);

	if (newsockfd < 0) 
		error("ERROR on accept");


	// Cria as threads para ouvir e escrever
	pthread_create(&thread_read, NULL, server_read, &newsockfd);
	pthread_create(&thread_write, NULL, server_write, &newsockfd);

	pthread_join(thread_read, NULL);
	pthread_join(thread_write, NULL);

	
	//while(1){
		//server_read((void*)newsockfd);
		//server_write((void*)newsockfd);
		
		// pthread_join(thread_write, NULL);
		// pthread_join(thread_read, NULL);
	//}


	return 0; 
}
