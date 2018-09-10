package br.com.modelo;

public class Soldado {

  private String nome;
  private int posicaoX;
  private int posicaoY;

  public Soldado(String nome, int x, int y) {
    this.nome = nome;
    this.posicaoX = x;
    this.posicaoY = y;
  }

  public String getNome() {
    return nome;
  }

  public void setPosicao(int x, int y) {
    this.posicaoX = x;
    this.posicaoY = y;
  }

  public int getPosicaoX() {
    return posicaoX;
  }

  public int getPosicaoY() {
    return posicaoY;
  }

}