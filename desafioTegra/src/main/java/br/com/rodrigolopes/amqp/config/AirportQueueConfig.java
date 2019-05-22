package br.com.rodrigolopes.amqp.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.support.Transformers;

import br.com.rodrigolopes.amqp.handler.AirportMessageHandler;
import br.com.rodrigolopes.desafioTegra.domain.Aeroporto;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Rodrigo Lopes
 */
@Configuration
@Slf4j
public class AirportQueueConfig {

	 @Autowired
    private ConnectionFactory rabbitConnectionFactory;
	 
	 @Autowired
	 AirportMessageHandler airportMessageHandler;

    /**
     * Inbound for queue moviments to database
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow inboundResponse(){
        return IntegrationFlows
                .from(Amqp.inboundAdapter(rabbitConnectionFactory, "q.airplanes")
                		.defaultRequeueRejected(false)
                		.errorHandler(throwable -> log.info("Erro inboundAdapter ", throwable.getCause().getMessage()))
                )
                .wireTap(this::logRequestPayload)
                .transform(Transformers.fromJson(Aeroporto.class))
                .handle(airportMessageHandler)
                .get();
    }
   
    private void logRequestPayload(IntegrationFlowDefinition<?> flow) {
        flow.handle(message -> {
            log.info("Mensagem Recebida: ", message.getPayload());
        });
    }
}
