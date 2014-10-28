package br.com.futtrackerapp.webservice;


public class ComandoREST {

	//private static final String URL = "http://10.0.3.2:8080/WebService/rest/comando";
	private static final String URL = "http://200.131.37.199:8080/WebService/rest/comando";
	//private static final String URL = "http://192.168.25.6:8080/WebService/rest/jogador";
	
	public void solicitaVideo() throws Exception{
		
	     String[] resposta = new WebServiceCliente().get(URL + "/produzirVideo");
	     if (resposta[0].equals("200")) {
	         //return resposta[1];
	     } else {
	         throw new Exception(resposta[1]);
	     }
	}
}
