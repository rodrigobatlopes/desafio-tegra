package br.com.rodrigolopes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigolopes.desafioTegra.service.AirportService;
import lombok.extern.slf4j.Slf4j;





@RestController
@RequestMapping("/airports")
@Slf4j
public class AirportRestController {

	@Autowired
	public AirportService airportService;
	
    @GetMapping(value={"/list"},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Object> getAirports() {
    	try {
    		log.info("Get Airports API started");
    		return ResponseEntity.ok(airportService.loadAirports());
    	} catch (Exception e) {
    		return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    	}	
    }        
}
