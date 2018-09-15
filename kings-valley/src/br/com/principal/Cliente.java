package br.com.principal;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);

    try {
      Interface partida = (Interface) Naming.lookup("//localhost/jogo");
      System.out.println("[INFO] Informar o nome do jogador:");
      String jogador = entrada.nextLine();
      int registro = partida.registraJogador(jogador);
      System.out.println("[INFO] Id da partida: " + registro);
      int retorno = partida.temPartida(registro);

      while (retorno == 0) {
        System.out.println("[INFO] Esperando segundo jogador entrar.");
        retorno = partida.temPartida(registro);
        try { Thread.sleep (5000); } catch (InterruptedException ex) {}
      }

      switch (retorno) {
      case 1:
        System.out.println("[INFO] Partida começou!");
        break;
      case 2:
        System.out.println("[INFO] Partida começou!");
        break;
      default:
        System.out.println("[INFO] Tempo de espera esgotado.");
        break;
      }

      if (partida.ehMinhaVez(registro) == 1) {
        partida.obtemTabuleiro(registro);
        System.out.println("[INFO] Faça sua jogada!");
        System.out.println("[INFO] Linha:");
        int linha = entrada.nextInt();
        System.out.println("[INFO] Coluna:");
        int coluna = entrada.nextInt();
        System.out.println("[INFO] Deslocamento:");
        int deslocamento = entrada.nextInt();
        partida.movePeca(registro, linha, coluna, deslocamento);
        partida.obtemTabuleiro(registro);
      } else {
        System.out.println("[INFO] Esperando jogada do adversário.");
      }

    } catch (Exception e) {
      System.out.println("[INFO] Erro na conexão com o servidor:");
      e.printStackTrace();
    }
  }

}
