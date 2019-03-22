package br.com.rodrigolopes.desafioTegra.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import br.com.rodrigolopes.desafioTegra.domain.Voo;

public interface UberAirService {
	
	List<Voo> loadUberAirFlights() throws FileNotFoundException, IOException;
	
	
}
