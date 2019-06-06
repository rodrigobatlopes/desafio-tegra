package br.com.rodrigolopes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigolopes.desafioTegra.domain.Aeroporto;
import br.com.rodrigolopes.desafioTegra.service.AirportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/airports")
@Slf4j
@CrossOrigin(origins = "*")
@Api(value="API REST Aeroportos")
public class AirportRestController {

	@Autowired
	public AirportService airportService;
	
	@ApiOperation(value="Retorna uma lista de Aeroportos")
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
    
    @Autowired
    MessageChannel providerMessageChannel;
    
	@ApiOperation(value="Cadastra um novo Aeroporto")
    @PostMapping(value = "/new",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ResponseBody
    public HttpEntity newAirport(@RequestBody Aeroporto aeroporto) {
    	 providerMessageChannel.send(new GenericMessage<Object>(aeroporto)); 
    	return ResponseEntity.ok().build();

    }
    
    
}
