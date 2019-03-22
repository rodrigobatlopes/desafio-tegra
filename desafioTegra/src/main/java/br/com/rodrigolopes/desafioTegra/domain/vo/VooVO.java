package br.com.rodrigolopes.desafioTegra.domain.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VooVO {
	private String voo; 
	private String origem;
	private String destino;
	private String data_saida;
	private String saida;
	private String chegada;
	private Double valor;
}
