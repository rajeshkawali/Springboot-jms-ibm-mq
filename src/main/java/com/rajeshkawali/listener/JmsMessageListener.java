
package com.rajeshkawali.listener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.rajeshkawali.model.UserDetails;
import com.rajeshkawali.util.IgnoreNamespace;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@Component
public class JmsMessageListener {

	public static final String CLASS_NAME = JmsMessageListener.class.getName();

	@JmsListener(destination = "${ibm.mq.one.queue-name}", containerFactory = "jmsListenerContainerFactoryOne")
	public void jmsMessageListenerOne(byte[] bytePayload) throws Exception {
		String _function = ".jmsMessageListenerOne";
		log.info(CLASS_NAME + _function + "::ENTER");
		try {
			String payload = new String(bytePayload);
			log.info(CLASS_NAME + _function + "::payload: {}", payload);
			unmarshalXmlString(payload);
			//unmarshalXmlStringWithIgnoringNameSpace(payload);
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred: {}", e);
		}
		log.info(CLASS_NAME + _function + "::EXIT");
	}

	@JmsListener(destination = "${ibm.mq.two.queue-name}", containerFactory = "jmsListenerContainerFactoryTwo")
	public void jmsMessageListenerTwo(byte[] bytePayload) throws Exception {
		String _function = ".jmsMessageListenerTwo";
		log.info(CLASS_NAME + _function + "::ENTER");
		try {
			String payload = new String(bytePayload);
			log.info(CLASS_NAME + _function + "::payload: {}", payload);
			unmarshalXmlString(payload);
			//unmarshalXmlStringWithIgnoringNameSpace(payload);
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred: {}", e);
		}
		log.info(CLASS_NAME + _function + "::EXIT");
	}

	public static void unmarshalXmlString(String xmlString) throws Exception {
		String _function = ".unmarshalXmlString";
		log.info(CLASS_NAME + _function + "::ENTER");
		UserDetails createEntitlements = null;
		if (StringUtils.isNotBlank(xmlString)) {
			log.info(CLASS_NAME + _function + "::Before unmarshalling xml string is : {}", xmlString);
			JAXBContext jc = JAXBContext.newInstance(UserDetails.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			InputStream iStream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
			XMLStreamReader xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(iStream);
			createEntitlements = (UserDetails) unmarshaller.unmarshal(xmlStreamReader);
			log.info(CLASS_NAME + _function + "::After unmarshalling xml message is : {}", createEntitlements);
		}
		log.info(CLASS_NAME + _function + "::EXIT");
	}

	public static void unmarshalXmlStringWithIgnoringNameSpace(String xmlString) throws Exception {
		String _function = ".unmarshalXmlStringWithIgnoringNameSpace";
		log.info(CLASS_NAME + _function + "::ENTER");
		UserDetails userDetails = null;
		if (StringUtils.isNotBlank(xmlString)) {
			log.info(CLASS_NAME + _function + "::Before unmarshalling xml string is : {}", xmlString);
			JAXBContext jcontext = JAXBContext.newInstance(UserDetails.class);
			Unmarshaller un = jcontext.createUnmarshaller();
			InputStream iStream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
			XMLStreamReader xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(iStream);
			IgnoreNamespace ignoreNamespace = new IgnoreNamespace(xmlStreamReader);
			userDetails = (UserDetails) un.unmarshal(ignoreNamespace);
			log.info(CLASS_NAME + _function + "::After unmarshalling xml message is : {}", userDetails);
		}
		log.info(CLASS_NAME + _function + "::EXIT");
	}

}
