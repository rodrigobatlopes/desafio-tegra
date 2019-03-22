package br.com.rodrigolopes.desafioTegra.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigolopes.desafioTegra.domain.Voo;
import br.com.rodrigolopes.desafioTegra.domain.vo.VooVO;
import br.com.rodrigolopes.desafioTegra.service.AirportService;
import br.com.rodrigolopes.desafioTegra.service.FlightService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class FlightServiceImpl implements FlightService {
	
	@Autowired
	AirportService airportService;

	@Override
	public List<Voo> convertVooVOToVoo(List<VooVO> voosVO, String operadora) {
		log.info("Convert VooVO to Voo started");
		List<Voo> voos = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		voosVO.forEach(e -> {
			voos.add(new Voo().builder().horaChegada(Date.from(LocalDateTime.parse(e.getData_saida()+" "+e.getChegada(), formatter).atZone(ZoneId.systemDefault()).toInstant()))
										 .horaSaida(Date.from(LocalDateTime.parse(e.getData_saida()+" "+e.getSaida(), formatter).atZone(ZoneId.systemDefault()).toInstant()))
										 .voo(e.getVoo())
										 .saida(Date.from(LocalDateTime.parse(e.getData_saida()+" 00:00", formatter).atZone(ZoneId.systemDefault()).toInstant()))
										 .origem(airportService.findById(e.getOrigem()))
										 .destino(airportService.findById(e.getDestino()))
										 .preco(e.getValor())
										 .operadora(operadora)
										 .build());
		});
		log.info("Convert VooVO to Voo finished successfully");
		return voos;
	}
}
