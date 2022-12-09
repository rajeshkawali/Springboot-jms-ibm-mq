
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
public class MQTwoConfig {

	@Bean
	@ConfigurationProperties("ibm.mq.two")
	public MQConfigurationProperties mqConfigPropertiesTwo() {
		return new MQConfigurationProperties();
	}

	@Bean
	public MQConnectionFactory qmTwoConnectionFactory(
			@Qualifier("mqConfigPropertiesTwo") MQConfigurationProperties properties,
			ObjectProvider<List<MQConnectionFactoryCustomizer>> factoryCustomizers) {
		return new MQConnectionFactoryFactory(properties, factoryCustomizers.getIfAvailable()).createConnectionFactory(MQConnectionFactory.class);
	}

	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerFactoryTwo(@Qualifier("qmTwoConnectionFactory") ConnectionFactory connectionFactory,
			MQErrorHandler errorHandler) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		//factory.setMessageConverter(convertXmlToJavaTwo());
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