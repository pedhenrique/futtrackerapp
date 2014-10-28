package br.com.futtrackerapp.entidades;

public class Video {

	private Integer id;
	
	private String path;
	private boolean pronto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isPronto() {
		return pronto;
	}
	public void setPronto(boolean pronto) {
		this.pronto = pronto;
	}
	
	
}
