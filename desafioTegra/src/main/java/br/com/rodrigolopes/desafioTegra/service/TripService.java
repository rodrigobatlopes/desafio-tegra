package br.com.rodrigolopes.desafioTegra.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import br.com.rodrigolopes.desafioTegra.domain.Viagem;

public interface TripService {
	
	List<Viagem> findTrip(String homeAirport, String destinationAirport, Date boardingDate);
	
	
	
}
