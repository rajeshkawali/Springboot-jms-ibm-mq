package com.rajeshkawali;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Rajesh_Kawali
 *
 */
@EnableJms
@SpringBootApplication
public class SpringbootJmsIbmMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJmsIbmMqApplication.class, args);
	}

}
