package br.com.futtrackerapp.entidades;


public class Jogador {
	
	private Integer id;
	
	private String nome;
	private String apelido;
	private String posicao;
	private int numero;
	private Double velocidadeMaxima;
	private Double velocidadeMedia;
	private Double distanciaPercorrida;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getPosicao() {
		return posicao;
	}
	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Double getVelocidadeMaxima() {
		return velocidadeMaxima;
	}
	public void setVelocidadeMaxima(Double velocidadeMaxima) {
		this.velocidadeMaxima = velocidadeMaxima;
	}
	public Double getVelocidadeMedia() {
		return velocidadeMedia;
	}
	public void setVelocidadeMedia(Double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}
	public Double getDistanciaPercorrida() {
		return distanciaPercorrida;
	}
	public void setDistanciaPercorrida(Double distanciaPercorrida) {
		this.distanciaPercorrida = distanciaPercorrida;
	}
	
	
	
}
