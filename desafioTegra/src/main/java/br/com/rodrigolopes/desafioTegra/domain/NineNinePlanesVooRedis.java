package br.com.rodrigolopes.desafioTegra.domain;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import redis.clients.jedis.Jedis;

@Repository
public class NineNinePlanesVooRedis {	
	
	private Jedis jedis;
	private Gson gson;
	
	public NineNinePlanesVooRedis() {
		jedis = new Jedis("127.0.0.1", 6379);
		gson = new GsonBuilder().create();
	}
	
	private String voo; 
	private Aeroporto origem;
	private Aeroporto destino;
	private Date saida;
	private Date horaSaida;
	private Date horaChegada;
	private String operadora;
	private Double preco;

}
