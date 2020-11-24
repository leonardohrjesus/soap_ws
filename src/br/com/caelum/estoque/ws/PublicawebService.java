package br.com.caelum.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublicawebService {

	public static void main(String[] args) {
		EstoqueWS service = new EstoqueWS();
		String url = "http://localhost:8080/estoquews";
		
		System.out.println("Servico rodando " + url +"?wsdl");
		
		Endpoint.publish(url, service);

	}

}
