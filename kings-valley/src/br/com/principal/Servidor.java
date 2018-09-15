package br.com.principal;

import java.util.ArrayList;

import br.com.modelo.Tabuleiro;

public class Servidor {

  private Tabuleiro tabuleiro;
  private ArrayList<Cliente> jogadores;

  public static void main(String[] args) {
    Tabuleiro tabuleiro = new Tabuleiro();
    tabuleiro.imprimirTabuleiro();
    tabuleiro.mover(0, 0, 0);
    tabuleiro.imprimirTabuleiro();
    tabuleiro.mover(3, 0, 0);
    tabuleiro.imprimirTabuleiro();
    tabuleiro.mover(0, 3, 2);
    tabuleiro.imprimirTabuleiro();
    tabuleiro.mover(2, 0, 0);
    tabuleiro.imprimirTabuleiro();
    tabuleiro.ganhador();
  }
}
