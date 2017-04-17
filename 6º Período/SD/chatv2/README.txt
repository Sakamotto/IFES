Modo de usar:

1º: Digite o comando make para compilar os arquivos.
2º: Execute o arquivo chat_servidor da seguinte maneira:
	./chat_servidor <porta>
	Exemplo:
		./chat_servidor 8000

3º: Execute o arquivo chat_cliente da seguinte maneira:
	./chat_cliente <endereço> <porta> <nome_do_usuario>(opcional)
	Exemplo:
		./chat_cliente localhost 8000 Cristian
		Caso o parâmetro <nome_do_usuario> não seja fornecido, a aplicação usará o nome 'Anônimo' como default.
