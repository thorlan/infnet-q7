package infnetjavaq7.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import infnetjavaq7.config.RabbitMQConfig;

@Component
public class RabbitMQService {
	
	//'Pede' ao spring um RabbitTemplate para poder enviar msg para a fila
	@Autowired
	private RabbitTemplate template;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	//Diz de qual fila do rabbitmq iremos consumir
	@RabbitListener(queues = "questao-7")
	public void consume(@Payload String body, Channel channel, @Header("amqp_deliveryTag") long tag) throws IOException {
		
		log.info("Mensagem recebida: " + body);
		
		//da um "ok" na transacted para poder retirar a mensagem da fila
		channel.basicAck(tag, false);
	}
	
	//cria uma rotina de envio de mensagens. Uma a cada 5 segundos
	@Scheduled(fixedDelay = 5000, initialDelay = 500)
	public void produce() {
		String msg = "A hora da mensagem é: " + LocalDateTime.now().toString();
		
		//usa o template recebido pelo spring e envia a mensagem para a fila 
		template.convertAndSend(RabbitMQConfig.QUEUE_NAME, msg);
		log.info("Mensagem enviada: " + msg);
	}
	
}

