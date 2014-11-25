package br.com.futtrackerapp.entidades;

public class Video {

	private Integer id;
	
	private String path;
	private boolean pronto;
	private boolean pronto2;
	private String nome;
	
	public boolean isPronto2() {
		return pronto2;
	}
	public void setPronto2(boolean pronto2) {
		this.pronto2 = pronto2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
