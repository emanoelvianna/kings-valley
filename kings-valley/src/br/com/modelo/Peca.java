package br.com.modelo;

public class Peca {

  private String cor;
  private String nome;
  private int posicaoX;
  private int posicaoY;

  public Peca(String cor, String nome, int x, int y) {
    this.cor = cor;
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

  public String getCor() {
    return cor;
  }

}
