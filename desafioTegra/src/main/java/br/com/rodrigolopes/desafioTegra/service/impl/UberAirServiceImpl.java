package br.com.rodrigolopes.desafioTegra.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigolopes.desafioTegra.domain.Voo;
import br.com.rodrigolopes.desafioTegra.domain.vo.VooVO;
import br.com.rodrigolopes.desafioTegra.service.FlightService;
import br.com.rodrigolopes.desafioTegra.service.UberAirService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UberAirServiceImpl implements UberAirService {

	private static final String COMMA_DELIMITER = ",";
	
	@Autowired
	FlightService flightService;

	@Override
	public List<Voo> loadUberAirFlights() throws FileNotFoundException, IOException {
		log.info("Load UberAir Flights started");
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("./csv/uberair.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        records.add(Arrays.asList(values));
		    }
		    List<VooVO> voosVO = new ArrayList();
		    int i = 1;
		    records.remove(0);
		    records.forEach(e -> {
		    	voosVO.add(new VooVO().builder()
						 .voo(e.get(0))
						 .origem(e.get(1))
						 .destino(e.get(2))
						 .data_saida(e.get(3))
						 .saida(e.get(4))
						 .chegada(e.get(5))
						 .valor(Double.valueOf(e.get(6)))
						 .build());
		    });
			return flightService.convertVooVOToVoo(voosVO, "UberAir");	
		} catch (Exception ex) {
			log.error("Error to load UberAir Flights - erro: "+ ex.getMessage());
			return null;
		}
	}

}
