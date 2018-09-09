package br.com.modelo;

public class Soldado {

  private int posicao[];

  public Soldado() {
    this.posicao = new int[2];
  }

  public int[] getPosicao() {
    return posicao;
  }

  public void setPosicao(int posicao[]) {
    this.posicao = posicao;
  }
}
