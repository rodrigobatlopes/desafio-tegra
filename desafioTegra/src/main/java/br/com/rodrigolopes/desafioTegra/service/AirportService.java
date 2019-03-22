package br.com.rodrigolopes.desafioTegra.service;

import java.util.List;

import br.com.rodrigolopes.desafioTegra.domain.Aeroporto;

public interface AirportService {
	
	List<Aeroporto> loadAirports();
	
	Aeroporto findById(String id);

}
