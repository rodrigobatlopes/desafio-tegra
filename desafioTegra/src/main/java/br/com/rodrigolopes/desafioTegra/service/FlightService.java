package br.com.rodrigolopes.desafioTegra.service;

import java.util.List;

import br.com.rodrigolopes.desafioTegra.domain.Voo;
import br.com.rodrigolopes.desafioTegra.domain.vo.VooVO;

public interface FlightService {
	
	List<Voo> convertVooVOToVoo(List<VooVO> voosVO, String operadora);
	
	
	
}
