package br.com.rodrigolopes.desafioTegra.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rodrigolopes.desafioTegra.service.NinetyNinePlanesService;

public class StaticClass {

	@Autowired
	static
	NinetyNinePlanesService ninetyNinePlanesService;
	
	
	public static void main(String[] args) {
		
		List<Voo> voos = new ArrayList();
		load(voos);
	}
	
	public static void load (List<Voo> voos) {
		voos = ninetyNinePlanesService.loadNinetyNinePlanesFlights();
		
	}

}
