package br.com.principal;

import java.rmi.Naming;
import java.util.Scanner;

public class Cliente {

  private static Scanner entrada;
  
  public static void main(String[] args) {
    entrada = new Scanner(System.in);

    try {
      Interface partida = (Interface) Naming.lookup("//localhost/jogo");
      System.out.println("[INFO] Informar o nome do jogador:");
      String jogador = entrada.nextLine();
      int registro = partida.registraJogador(jogador);
      System.out.println("[INFO] Id do jogador: " + registro);

      System.out.println("[INFO] Esperando segundo jogador entrar...");
      int retorno = partida.temPartida(registro);
      while (retorno == 0) {
        retorno = partida.temPartida(registro);
      }
      System.out.println("[INFO] Segundo jogador acabou de entrar");
      System.out.println("[INFO] Seu adversario é: " + partida.obtemOponente(registro));

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

      while (true) {

        if (partida.ehMinhaVez(registro) == 2) {
          System.out.println("---");
          System.out.println("[INFO] Parabéns, você é o vencedor!");
          System.out.println("---");
          break;
        } else if (partida.ehMinhaVez(registro) == 3) {
          System.out.println("---");
          System.out.println("[INFO] Sinto muito, você perdeu!");
          System.out.println("---");
          break;
        } else if (partida.ehMinhaVez(registro) == 4) {
          System.out.println("---");
          System.out.println("[INFO] Fim da partida, houve empate!");
          System.out.println("---");
          break;
        } else if (partida.ehMinhaVez(registro) == 5) {
          System.out.println("---");
          System.out.println("[INFO] Fim da partida, vencedor por WO!");
          System.out.println("---");
          break;
        } else if (partida.ehMinhaVez(registro) == 6) {
          System.out.println("---");
          System.out.println("[INFO] Fim da partida, perdedor por WO!");
          System.out.println("---");
          break;
        } else if (partida.ehMinhaVez(registro) == 1) {
          System.out.println("[INFO] Tabuleiro atualizado:");
          System.out.println(partida.obtemTabuleiro(registro));
          System.out.println("[INFO] Faça sua jogada!");
          System.out.println("[INFO] Linha:");
          int linha = entrada.nextInt();
          System.out.println("[INFO] Coluna:");
          int coluna = entrada.nextInt();
          System.out.println("[INFO] Deslocamento:");
          int deslocamento = entrada.nextInt();
          partida.movePeca(registro, linha, coluna, deslocamento);
          System.out.println("[INFO] Tabuleiro atualizado:");
          System.out.println(partida.obtemTabuleiro(registro));
        } else if (partida.ehMinhaVez(registro) == -2) {
          try {
            Thread.sleep(5000);
            System.out.println("[INFO] Esperando jogada do adversário.");
          } catch (InterruptedException ex) {
          }
        } else if (partida.temPartida(registro) == 0) {
          try {
            Thread.sleep(5000);
            System.out.println("[INFO] Esperando jogada do adversário.");
          } catch (InterruptedException ex) {
          }
        } else if (partida.temPartida(registro) == -2) {
          System.out.println("[INFO] Tempo de espera da partida esgotado");
        }
      }

    } catch (Exception e) {
      System.out.println("[INFO] Erro na conexão com o servidor:");
      e.printStackTrace();
    }
  }

}
