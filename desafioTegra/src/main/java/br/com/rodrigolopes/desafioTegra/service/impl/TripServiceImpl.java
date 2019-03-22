package br.com.rodrigolopes.desafioTegra.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigolopes.desafioTegra.domain.Trecho;
import br.com.rodrigolopes.desafioTegra.domain.Viagem;
import br.com.rodrigolopes.desafioTegra.domain.Voo;
import br.com.rodrigolopes.desafioTegra.service.AirportService;
import br.com.rodrigolopes.desafioTegra.service.NinetyNinePlanesService;
import br.com.rodrigolopes.desafioTegra.service.TripService;
import br.com.rodrigolopes.desafioTegra.service.UberAirService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TripServiceImpl implements TripService {
	
	@Autowired
	NinetyNinePlanesService ninetyNinePlanesService;

	@Autowired
	AirportService airportService;
	
	@Autowired
	UberAirService uberAirService;
	
	@Override
	public List<Viagem> findTrip(String homeAirport, String destinationAirport, Date boardingDate) {
		try	{
			log.info("Find Trip started");
			List<Voo> voos = new ArrayList();
			uberAirService.loadUberAirFlights().stream().filter(e -> e.getSaida().equals(boardingDate)).forEach(e -> voos.add(e) );
			ninetyNinePlanesService.loadNinetyNinePlanesFlights().stream().filter(e -> e.getSaida().equals(boardingDate)).forEach(e -> voos.add(e) );
			return findRoutes(voos, homeAirport, destinationAirport);
			
		} catch (FileNotFoundException ex) {
			log.error("Error to find Trip - error: "+ ex.getMessage());
			return null;
		} catch (IOException ex) {
			log.error("Error to find Trip - error: "+ ex.getMessage());
			return null;
		}
	}
	
	public List<Viagem> findRoutes(List<Voo> voos, String homeAirport, String destinationAirport) {
		List<Viagem> viagens = new ArrayList();
		voos.forEach(e -> {
			if (e.getOrigem().getAeroporto().equals(homeAirport) && e.getDestino().getAeroporto().equals(destinationAirport)) {
				List<Trecho> trechosLevel1 = new ArrayList();
				trechosLevel1.add(new Trecho().builder().chegada(e.getHoraChegada())
							 .saida(e.getHoraSaida())
							 .origem(e.getOrigem().getAeroporto())
							 .destino(e.getDestino().getAeroporto())
							 .operadora(e.getOperadora())
							 .preco(e.getPreco())
							 .build());
				viagens.add(new Viagem().builder().origem(e.getOrigem().getAeroporto())
												  .destino(e.getDestino().getAeroporto())
												  .saida(e.getHoraSaida())
												  .chegada(e.getHoraChegada())
												  .trechos(trechosLevel1).build());
				log.info("Route Level1 founded "+trechosLevel1.toString());
			} else { if (e.getOrigem().getAeroporto().equals(homeAirport)) {
				List<Trecho> trechosLevel2 = new ArrayList();
					voos.stream().filter(f -> f.getOrigem().getAeroporto().equals(e.getDestino().getAeroporto())).forEach(f -> {
						if (f.getDestino().getAeroporto().equals(destinationAirport) && 
							f.getOrigem().getAeroporto().equals(e.getDestino().getAeroporto()) &&
							f.getHoraSaida().after(e.getHoraChegada())
						) {
							trechosLevel2.add(new Trecho().builder().chegada(e.getHoraChegada())
  									 .saida(e.getHoraSaida())
  									 .origem(e.getOrigem().getAeroporto())
  									 .destino(e.getDestino().getAeroporto())
  									 .operadora(e.getOperadora())
  									 .preco(e.getPreco())
  									 .build());
							trechosLevel2.add(new Trecho().builder().chegada(f.getHoraChegada())
  									 .saida(f.getHoraSaida())
  									 .origem(f.getOrigem().getAeroporto())
  									 .destino(f.getDestino().getAeroporto())
  									 .operadora(f.getOperadora())
  									 .preco(f.getPreco())
  									 .build());
							viagens.add(new Viagem().builder().origem(e.getOrigem().getAeroporto())
															  .destino(f.getDestino().getAeroporto())
															  .saida(e.getHoraSaida())
															  .chegada(f.getHoraChegada())
															  .trechos(trechosLevel2).build());
							log.info("Route Level2 founded "+trechosLevel2.toString());
						} else { if (f.getOrigem().getAeroporto().equals(homeAirport)) {
							List<Trecho> trechosLevel3 = new ArrayList();
							voos.stream().filter(g -> g.getOrigem().getAeroporto().equals(f.getDestino().getAeroporto())).forEach(g -> {
								if (g.getDestino().getAeroporto().equals(destinationAirport) && 
									g.getOrigem().getAeroporto().equals(f.getDestino().getAeroporto()) &&
									g.getHoraSaida().after(f.getHoraChegada()) )
								{
									trechosLevel3.add(new Trecho().builder().chegada(e.getHoraChegada())
		  									 .saida(e.getHoraSaida())
		  									 .origem(e.getOrigem().getAeroporto())
		  									 .destino(e.getDestino().getAeroporto())
		  									 .operadora(e.getOperadora())
		  									 .preco(e.getPreco())
		  									 .build());
									trechosLevel3.add(new Trecho().builder().chegada(f.getHoraChegada())
		  									 .saida(f.getHoraSaida())
		  									 .origem(f.getOrigem().getAeroporto())
		  									 .destino(f.getDestino().getAeroporto())
		  									 .operadora(f.getOperadora())
		  									 .preco(f.getPreco())
		  									 .build());
									trechosLevel3.add(new Trecho().builder().chegada(g.getHoraChegada())
		  									 .saida(g.getHoraSaida())
		  									 .origem(g.getOrigem().getAeroporto())
		  									 .destino(g.getDestino().getAeroporto())
		  									 .operadora(g.getOperadora())
		  									 .preco(g.getPreco())
		  									 .build());
									viagens.add(new Viagem().builder().origem(e.getOrigem().getAeroporto())
																	  .destino(g.getDestino().getAeroporto())
																	  .saida(e.getHoraSaida())
																	  .chegada(g.getHoraChegada())
																	  .trechos(trechosLevel3).build());
									log.info("Route Level3 founded "+trechosLevel2.toString());
									}
								});
							}
						}
					});
				}
			}
		});
		log.info("Find Routes finished successfully");
		return viagens;
	}
	
}
