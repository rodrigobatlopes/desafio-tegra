package br.com.rodrigolopes.desafioTegra.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.rodrigolopes.desafioTegra.domain.Voo;
import br.com.rodrigolopes.desafioTegra.domain.vo.VooVO;
import br.com.rodrigolopes.desafioTegra.service.FlightService;
import br.com.rodrigolopes.desafioTegra.service.NinetyNinePlanesService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NinetyNinePlanesServiceImpl implements NinetyNinePlanesService {
	
	@Autowired
	FlightService flightService;

	@Override
	public List<Voo> loadNinetyNinePlanesFlights() {
		try {
			log.info("Load 99Planes Flights started");
			BufferedReader br = new BufferedReader(new FileReader("./json/99planes.json"));
			VooVO[] voosVO = new Gson().fromJson(br, VooVO[].class);
			List<Voo> voos = flightService.convertVooVOToVoo(Arrays.asList(voosVO), "99Planes");
			System.out.println("Flights Loaded succefully!");
			return voos;
		} catch (FileNotFoundException e ) {
			log.error("Error to load NinetyNinePlanesFlights - (FileNotFoundException)error: " + e.getMessage());
			return null;
		} catch (Exception e ) {
			log.error("Error to load NinetyNinePlanesFlights - error: " + e.getMessage());
			return null;
		}
	}

}
