package br.com.rodrigolopes.desafioTegra.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Aeroporto {
	
	private String nome;
	private String aeroporto;
	private String cidade;
	
    public String toJson(){
    	return new GsonBuilder().create().toJson(this);
    }

}
