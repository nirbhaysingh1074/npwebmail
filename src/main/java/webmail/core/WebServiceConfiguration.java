package webmail.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import webmail.webservice.client.FolderClient;

@Configuration
public class WebServiceConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("webmail.wsdl");
		return marshaller;
	}

	@Bean
	public FolderClient folderClient(Jaxb2Marshaller marshaller) {
		FolderClient client = new FolderClient();
		client.setDefaultUri("http://localhost:8080/ws/documentFolder.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}