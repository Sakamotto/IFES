all: compile

compile:
	gcc -o chat_servidor chat_cliente -c chat_servidor chat_cliente

chat_servidor:
	gcc -o chat_servidor chat_servidor.c -Wall -lpthread

chat_cliente:
	gcc -o chat_cliente chat_cliente.c -Wall -lpthread
