package br.com.rodrigolopes.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.rodrigolopes.desafioTegra.domain.Viagem;
import br.com.rodrigolopes.desafioTegra.service.TripService;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/flights")
@Slf4j
public class FlightsAvailableRestController {

	@Autowired
	public TripService tripService;
	
    @GetMapping(value={"/flightsavailable"},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Object> getFlightsAvailable(@RequestParam(name = "homeAirport", required = true) String homeAirport,
    		@RequestParam(name = "destinationAirport", required = true) String destinationAirport,
    		@RequestParam(name = "boardingDate", required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date boardingDate	
    		) {
    	try {
    		log.info("Get Flights API started");
    		List<Viagem> viagens = tripService.findTrip(homeAirport, destinationAirport, boardingDate);
    		if (!viagens.isEmpty()) {
    			return ResponseEntity.ok(viagens);
    		} else {
    			return ResponseEntity.ok( new Gson().toJson(":( Not found routes for the reported parameters!") );
    		}
    	} catch (Exception e) {
    		return ResponseEntity.badRequest().build();
    	}	
    }        
}
