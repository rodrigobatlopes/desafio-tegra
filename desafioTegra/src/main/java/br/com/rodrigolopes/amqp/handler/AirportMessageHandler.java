package br.com.rodrigolopes.amqp.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import br.com.rodrigolopes.desafioTegra.domain.Aeroporto;
import lombok.extern.slf4j.Slf4j;

/**
 * Class Order call back Dequeue Message Handle
 *
 * @author Rodrigo Lopes
 * @since 23/10/2018
 */
@Component
@Slf4j
public class AirportMessageHandler implements MessageHandler {


	public void handleMessage(Message<?> message) throws MessagingException {
		log.info("inicio handler" );
		log.info("message.toString=" + message.toString());  
		log.info("message.getPayload=" + message.getPayload().toString()); 
        Aeroporto aeroportoQueue = (Aeroporto) message.getPayload();
        log.info("fim handler" );
        log.info(" Log End ", aeroportoQueue.toString());        
  
    }
    
    
}