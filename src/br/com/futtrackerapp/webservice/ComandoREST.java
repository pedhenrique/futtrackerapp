package br.com.futtrackerapp.webservice;

import br.com.futtrackerapp.entidades.Comando;

import com.google.gson.Gson;


public class ComandoREST {

	//private static final String URL = "http://10.0.3.2:8080/WebService/rest/comando";
	private static final String URL = "http://200.131.37.199:8080/WebService/rest/comando";
	//private static final String URL = "http://192.168.25.6:8080/WebService/rest/jogador";
	
	public Comando solicitaVideo() throws Exception{
		
//	     String[] resposta = new WebServiceCliente().get(URL + "/produzirVideo");
//	     if (resposta[0].equals("200")) {
//	         //return resposta[1];
//	     } else {
//	         throw new Exception(resposta[1]);
//	     }
		String[] resposta = new WebServiceCliente().get(URL + "/produzirVideo");

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			Comando comando = gson.fromJson(resposta[1], Comando.class);
			return comando;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
