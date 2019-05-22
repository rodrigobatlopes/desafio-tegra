package br.com.rodrigolopes.desafioTegra.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UberAirVooRedis {
	
	private String voo; 
	private Aeroporto origem;
	private Aeroporto destino;
	private Date saida;
	private Date horaSaida;
	private Date horaChegada;
	private String operadora;
	private Double preco;

}
