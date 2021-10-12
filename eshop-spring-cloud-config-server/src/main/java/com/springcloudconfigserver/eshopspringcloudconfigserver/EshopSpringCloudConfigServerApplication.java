package com.springcloudconfigserver.eshopspringcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class EshopSpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopSpringCloudConfigServerApplication.class, args);
	}

}
