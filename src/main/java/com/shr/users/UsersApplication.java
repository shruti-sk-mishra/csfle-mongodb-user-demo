package com.shr.users;

import com.shr.fle.EncryptionMongoEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author shruti.mishra
 */
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "com.shr")
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}


	/**
	 * Register EncryptionMongoEventListener as customised
	 * AbstractMongoEventListener
	 * @return
	 */
	@Bean
	public EncryptionMongoEventListener encryptionMongoEventListener() {
		return new EncryptionMongoEventListener();
	}

}
