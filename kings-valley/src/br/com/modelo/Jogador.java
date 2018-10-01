package br.com.modelo;

public class Jogador {

  private String nome;
  private Integer id;
  private String minhaCor;
  private boolean ehMinhaVez;

  public Jogador(int id, String nome, String minhaCor) {
    this.id = id;
    this.nome = nome;
    this.ehMinhaVez = false;
    this.minhaCor = minhaCor;
  }

  public Integer obterId() {
    return this.id;
  }

  public String obterNome() {
    return this.nome;
  }

  public void ehMinhaVez(boolean minhaVez) {
    this.ehMinhaVez = minhaVez;
  }

  public boolean ehMinhaVez() {
    return this.ehMinhaVez;
  }

  public String obterMinhaCor() {
    return this.minhaCor;
  }
}
