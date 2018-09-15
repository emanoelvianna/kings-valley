package br.com.principal;

import java.util.ArrayList;

import br.com.modelo.Tabuleiro;

public class Servidor {

  private Tabuleiro tabuleiro;
  private ArrayList<Cliente> jogadores;

  public static void main(String[] args) {
    Tabuleiro tabuleiro = new Tabuleiro();
    tabuleiro.imprimirTabuleiro();
    tabuleiro.mover(4, 0, 7);
    tabuleiro.imprimirTabuleiro();
  }
}
