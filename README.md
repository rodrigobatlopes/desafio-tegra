# (Rodrigo Lopes - Teste de Backend: Criar a API)
O Projeto foi desenvolvido em Java 8 utilizando o Framework SpringBoot, optei por fazer apenas a parte de BackEnd do mesmo.

- Para subir a aplicação basta importar o diretorio desafioTegra como um Maven Project no Eclipse.
- A Classe de incilaização da aplicação é a Boot.java que fica em br.com.rodrigolopes.desafioTegra.
- A aplicação sobe na porta 8080.

## Existem 2 endpoints:
-1 /flights/flightsavailable?homeAirport=[aeroporto_origem]&destinationAirport=[aeroporto_destino]&boardingDate=[Data no formato "yyyy-MM-dd"]
-- Exemplo de chamada:
http://localhost:8080/flights/flightsavailable?homeAirport=BEL&destinationAirport=CGH&boardingDate=2019-02-10

-2 /airports/list
-- Exemplo de chamada:
http://localhost:8080/airports/list
