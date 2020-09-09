package infnetjavaq7.config;

import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

//Classe de configuração para conexão com o rabbitmq
@Configuration
@EnableScheduling
public class RabbitMQConfig {

	//Recupera os valores do arquivo rabbit.properties e os adiciona nas variaveis
	
	@Value("${rabbit.host}")
	private String rabbitHost;

	@Value("${rabbit.username}")
	private String rabbitUsername;

	@Value("${rabbit.password}")
	private String rabbitPassword;

	@Value("${rabbit.port}")
	private int rabbitPort;
	
	public static String QUEUE_NAME = "questao-7";

	//Cria a conexao com o rabbitmq
	@Bean
	public ConnectionFactory rabbitConnectionFactory() {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(rabbitHost);
		connectionFactory.setPort(rabbitPort);
		connectionFactory.setUsername(rabbitUsername);
		connectionFactory.setPassword(rabbitPassword);

		return connectionFactory;
	}

	//Declara a queue que sera utilizada com o rabbitmq
	@Bean
	public Declarables queueBinding() {
		Queue q7 = new Queue(QUEUE_NAME, true);
		
		return new Declarables(q7);
	}
	

}
