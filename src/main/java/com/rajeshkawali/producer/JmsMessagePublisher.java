package com.rajeshkawali.producer;

import java.io.StringWriter;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueueManager;
import com.rajeshkawali.model.UserDetails;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@Component
public class JmsMessagePublisher {

	public static final String CLASS_NAME = JmsMessagePublisher.class.getName();

	@Value("${ibm.mq.queueManager}")
	private String queueManager;

	@Value("${ibm.mq.host}")
	private String host;

	@Value("${ibm.mq.channel}")
	private String channel;

	@Value("${ibm.mq.sslCipherSuite}")
	private String sslCipherSuite;

	@Value("${ibm.mq.port}")
	private int port;

	@Value("${ibm.mq.queueName}")
	private String queueName;

	public void publishMessageToIbmMq(UserDetails userDetails) throws Exception {
		String _function = ".publishMessageToIbmMq";
		log.info(CLASS_NAME + _function + "::ENTER");
		String xmlString = null;
		try {
			xmlString = marshalUserDetailsToXmlString(userDetails);
			if (StringUtils.isNotBlank(xmlString)) {
				MQEnvironment.hostname = host;
				MQEnvironment.port = port;
				MQEnvironment.channel = channel;
				MQEnvironment.sslCipherSuite = sslCipherSuite;
				System.setProperty("com.ibm.mq.cfg.useIBMCipherMappings", "false");
				MQQueueManager qMgr = new MQQueueManager(queueManager);
				MQMessage mqMessage = new MQMessage();
				mqMessage.writeString(xmlString);
				qMgr.put(queueName, mqMessage);
				qMgr.close(); // Put this in finally block
				qMgr.disconnect();// Put this in finally block
				log.info(CLASS_NAME + _function + "::Message published to IBM MQ server:");
				// System.out.println(xmlData); // Added S.O.P because log.info() is not printing xml message.
			}
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred while publishing message to IBM MQ: "+ e.getMessage());
		}
		log.info(CLASS_NAME + _function + "::EXIT");
	}

	public static String marshalUserDetailsToXmlString(UserDetails userDetails) throws Exception {
		String _function = ".marshalUserDetailsToXmlString";
		log.info(CLASS_NAME + _function + "::ENTER");
		String xmlString = null;
		if (ObjectUtils.isNotEmpty(userDetails)) {
			log.info(CLASS_NAME + _function + "::Before unmarshalling xml string is : {}", userDetails);
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(UserDetails.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			jaxbMarshaller.marshal(userDetails, sw);
			xmlString = sw.toString();
			log.info(CLASS_NAME + _function + "::After unmarshalling xml message is : {}", xmlString);
		}
		log.info(CLASS_NAME + _function + "::EXIT");
		return xmlString;
	}
}
