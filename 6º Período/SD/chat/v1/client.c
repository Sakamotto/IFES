#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 
#include <string.h>
#include <pthread.h>

void error(char *msg)
{
    perror(msg);
    exit(0);
}


void* client_read(void* sock_server){
	int n;
	int server = (int) sock_server;
	char buffer[256];
	
	bzero(buffer,256);
	n = read(server, buffer, 255);

	if((strcmp(buffer, "bye") == 0)){
		exit(0);
	}
	
	if (n < 0) 
		 error("ERROR reading from socket");
	
	printf("Recebido: %s\n", buffer);
		
	return 0;
}


void* client_write(void* sock_server){
	int n;
	int server = (int) sock_server;
	char buffer_out[256];
	
	printf("[Client] Please enter the message: ");
		
	bzero(buffer_out, 256);

	fgets(buffer_out, 255, stdin);

	n = write(server, buffer_out, strlen(buffer_out) -1);
	
	if((strcmp(buffer_out, "bye") == 0)){
		exit(0);
	}
	
	if (n < 0) {
		error("ERROR writing to socket");
	}
		
	return 0;
}

int main(int argc, char *argv[])
{
    struct sockaddr_in serv_addr;
    struct hostent *server;
    int sockfd, portno;
    pthread_t thread_read, thread_write;

    if (argc < 3) {
       fprintf(stderr,"usage %s hostname port\n", argv[0]);
       exit(0);
    }

    portno = atoi(argv[2]);
    sockfd = socket(AF_INET, SOCK_STREAM, 0);

    if (sockfd < 0) 
        error("ERROR opening socket");
    server = gethostbyname(argv[1]);
    if (server == NULL) {
        fprintf(stderr,"ERROR, no such host\n");
        exit(0);
    }
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char *)server->h_addr, 
         (char *)&serv_addr.sin_addr.s_addr,
         server->h_length);
    serv_addr.sin_port = htons(portno);
    
    if (connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr)) < 0){
        error("ERROR connecting");
	}
	
	while(1){
		pthread_create(&thread_write, NULL, client_write, (void*) sockfd);
	}
	
	while(1){
		pthread_create(&thread_read, NULL, client_read, (void*) sockfd);
	}
    
    /*while(1){
		
		//client_write((void*) sockfd);
		//client_read((void*) sockfd);		
		
		pthread_create(&thread_write, NULL, client_write, (void*) sockfd);
		pthread_create(&thread_read, NULL, client_read, (void*) sockfd);
		
		pthread_join(thread_read, NULL);
		pthread_join(thread_write, NULL);
		
		
	}*/
    return 0;
}
