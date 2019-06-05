package br.com.rodrigolopes.amqp.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.MessageChannel;

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
     * @return IntegrationFlow   -- CONSUMMER --
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
    
    
    // PUBLISHER
    @Bean
    public MessageChannel providerMessageChannel() {
        return new DirectChannel();
    }
    
    @Bean
    public RabbitTemplate providerRabbitTemplate() {
        RabbitTemplate r = new RabbitTemplate(rabbitConnectionFactory);
        r.setQueue("q.airplanes");
        r.setRoutingKey("q.airplanes");
        return r;
    }
  
    // This method publishes in the queue
    @Bean
    public IntegrationFlow publishMessage() {
        return IntegrationFlows
                .from(providerMessageChannel())
                .transform(new ObjectToJsonTransformer())
                .handle(Amqp.outboundAdapter(providerRabbitTemplate()))
                .get();
    }  
    
    
    
}
