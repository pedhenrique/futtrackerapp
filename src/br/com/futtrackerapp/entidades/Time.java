package br.com.futtrackerapp.entidades;

import java.util.List;

public class Time {
	
	
	private Integer id;
	private String nome;
	
	private List<Jogador> jogadores;
	
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
	public List<Jogador> getJogadores() {
		return jogadores;
	}
	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	
}
