package com.ftn.sbnz.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

//	@Bean
//	public KieContainer kieContainer() {
//		KieServices ks = KieServices.Factory.get();
//		return ks.getKieClasspathContainer();
//	}

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kieContainer = ks.getKieClasspathContainer();

		// DEBUG - ispisi sve dostupne sesije
		kieContainer.getKieBaseNames().forEach(kBaseName -> {
			System.out.println("KieBase: " + kBaseName);
			kieContainer.getKieSessionNamesInKieBase(kBaseName).forEach(sessionName -> {
				System.out.println("  KieSession: " + sessionName);
			});
		});

		return kieContainer;
	}
}
