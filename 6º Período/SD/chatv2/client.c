#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 
#include <string.h>
#include <pthread.h>

pthread_t thread_read, thread_write;
char my_name[270];

void error(char *msg)
{
    perror(msg);
    exit(0);
}

void* client_read(void* sock_server){
	int n;
	int server = *(int*) sock_server;
	char buffer[256];
	bzero(buffer,256);
	
	while(strcmp(buffer, "bye\n") != 0){
		bzero(buffer,256);
		n = read(server, buffer, 255);
		if (n < 0)
			error("ERROR reading from socket");

		printf("> %s\n", buffer);
	}
	n = write(server, "bye", strlen("bye"));
	pthread_cancel(thread_write);
		
	return 0;
}


void* client_write(void* sock_server){
	int n;
	int server = *(int*) sock_server;
	char buffer_out[256], name_user[50];
	bzero(buffer_out, 256);
	
	strcpy(name_user, my_name);
	
	while(strcmp(buffer_out, "bye\n") != 0){
		bzero(buffer_out, 256);
		bzero(my_name, 256);
		
		// Lendo o que o usuário digitar para enviar a mensagem ...
		fgets(buffer_out, 255, stdin);
		
		strcat(my_name, name_user);
		strcat(my_name, ": ");
		strcat(my_name, buffer_out);

		n = write(server, my_name, strlen(my_name) -1);
		
		if (n < 0) {
			error("ERROR writing to socket");
		}
	}
	pthread_cancel(thread_read);
	exit(0);	
		
	return 0;
}

int main(int argc, char *argv[])
{
    struct sockaddr_in serv_addr;
    struct hostent *server;
    int sockfd, portno;

    if (argc < 3) {
       fprintf(stderr,"usage %s hostname port nick_name\n", argv[0]);
       exit(0);
    }else if (argc == 3){
		strcpy(my_name, "Anônimo");
	}else{
		strcpy(my_name, argv[3]);
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
	
	// Cria as threads para ouvir e escrever
    pthread_create(&thread_write, NULL, client_write, &sockfd);
	pthread_create(&thread_read, NULL, client_read, &sockfd);
	
	pthread_join(thread_read, NULL);
	pthread_join(thread_write, NULL);
	
	close(sockfd);

    return 0;
}
