
package com.rajeshkawali.exception;

import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@Component
public class MQErrorHandler implements ErrorHandler {

	public static final String CLASS_NAME = MQErrorHandler.class.getName();

	@Override
	public void handleError(Throwable throwable) {
		String _function = ".handleError";
        log.info(CLASS_NAME + _function + "::ENTER");
		log.error(CLASS_NAME + _function + "::XML unmarshalling exception: Please provide a valid input");
		log.error(throwable.getLocalizedMessage().toString());
		log.info(CLASS_NAME + _function + "::EXIT");
	}

}
