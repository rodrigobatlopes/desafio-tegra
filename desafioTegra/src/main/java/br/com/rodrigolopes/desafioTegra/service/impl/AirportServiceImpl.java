package br.com.rodrigolopes.desafioTegra.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.rodrigolopes.desafioTegra.domain.Aeroporto;
import br.com.rodrigolopes.desafioTegra.service.AirportService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AirportServiceImpl implements AirportService {

	@Override
	public List<Aeroporto> loadAirports()   {
		try {
			//log.info("Load Airports started");
			BufferedReader br = new BufferedReader(new FileReader("./json/aeroportos.json"));
			Aeroporto[] aeroportos = new Gson().fromJson(br, Aeroporto[].class);
			return Arrays.asList(aeroportos);
		} catch (FileNotFoundException e ) {
			log.error("Unable to load airports: " + e.getMessage());
			return null;
		} catch (Exception e ) {
			log.error("Unable to load airports: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Aeroporto findById(String id) {
		return loadAirports().stream().filter(e -> e.getAeroporto().equals(id)).findFirst().get();
	}

}
