package com.rajeshkawali.config;

import java.util.List;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.spring.boot.MQConfigurationProperties;
import com.ibm.mq.spring.boot.MQConnectionFactoryCustomizer;
import com.ibm.mq.spring.boot.MQConnectionFactoryFactory;
import com.rajeshkawali.exception.MQErrorHandler;

/**
 * @author Rajesh_Kawali
 *
 */
@Configuration
public class MQOneConfig {

	@Bean
	@ConfigurationProperties("ibm.mq.one")
	public MQConfigurationProperties mqConfigPropertiesOne() {
		return new MQConfigurationProperties();
	}

	@Bean
	public MQConnectionFactory qmOneConnectionFactory(
			@Qualifier("mqConfigPropertiesOne") MQConfigurationProperties properties,
			ObjectProvider<List<MQConnectionFactoryCustomizer>> factoryCustomizers) {
		return new MQConnectionFactoryFactory(properties, factoryCustomizers.getIfAvailable())
				.createConnectionFactory(MQConnectionFactory.class);
	}

	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerFactoryOne(
			@Qualifier("qmOneConnectionFactory") ConnectionFactory connectionFactory, MQErrorHandler errorHandler) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		//factory.setMessageConverter(convertXmlToJavaOne());
		factory.setErrorHandler(errorHandler);
		return factory;
	}
/*
	@Bean(name = "convertXmlToJavaTwo")
	public MarshallingMessageConverter convertXmlToJavaTwo() {
		Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
		MarshallingMessageConverter marshallingMessageConverter = null;
		//unmarshaller.setClassesToBeBound(Person.class);
		//unmarshaller.setSupportJaxbElementClass(true);
		marshallingMessageConverter = new MarshallingMessageConverter(unmarshaller);
		return marshallingMessageConverter;
	}
*/
}