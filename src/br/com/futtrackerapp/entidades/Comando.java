package br.com.futtrackerapp.entidades;

public class Comando {

	private Integer id;
	
	private boolean comando;

	private Integer idVideo;
	
	
	public Integer getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(Integer idVideo) {
		this.idVideo = idVideo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isComando() {
		return comando;
	}

	public void setComando(boolean comando) {
		this.comando = comando;
	}
	
	
}
