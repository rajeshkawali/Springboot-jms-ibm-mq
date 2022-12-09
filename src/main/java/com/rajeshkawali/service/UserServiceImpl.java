package com.rajeshkawali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshkawali.model.UserDetails;
import com.rajeshkawali.producer.JmsMessagePublisher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	public static final String CLASS_NAME = UserServiceImpl.class.getName();

	@Autowired
	private JmsMessagePublisher jmsMessagePublisher;

	@Override
	public void publish(UserDetails userDetails) {
		String _function = ".publish()";
		log.info(CLASS_NAME + _function + "::ENTER");
		try {
			log.info(CLASS_NAME + _function + "::send message to publishMessageToIbmMq method");
			jmsMessagePublisher.publishMessageToIbmMq(userDetails);
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred:" + e.getMessage());
		}
	}
}
