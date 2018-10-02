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

      int retorno = partida.registraJogador(jogador);
      if (retorno == -2) {
        System.out.println("[INFO] Sinto muito, servidor está cheio. Tente novamente mais tarde");
      } else {
        int idJogador = retorno;
        System.out.println("[INFO] Seu código id é: " + idJogador);
        System.out.println("[INFO] Partida esperando segundo jogador entrar...");

        retorno = partida.temPartida(idJogador);
        while (retorno == 0) {
          retorno = partida.temPartida(idJogador);
          switch (retorno) {
          case 1:
            System.out.println("[INFO] Partida começou. Você é o primeiro a jogar!");
            System.out.println("[INFO] Seu adversario é o: " + partida.obtemOponente(idJogador));
            System.out.println("---");
            break;
          case 2:
            System.out.println("[INFO] Partida começou. Você é o segundo a jogar!");
            System.out.println("[INFO] Seu adversario é o: " + partida.obtemOponente(idJogador));
            System.out.println("---");
            break;
          case -2:
            System.out.println("[INFO] Tempo de espera esgotado. Partida será finalizada!");
            partida.encerraPartida(idJogador);
            break;
          case -1:
            System.out.println("[INFO] Ocoreu algum problema. Partida será finalizada!");
            partida.encerraPartida(idJogador);
            break;
          }
        }

        while (true) {
          if (partida.ehMinhaVez(idJogador) == 2) {
            System.out.println("-----------------------------------");
            System.out.println("[INFO] Parabéns, você é o vencedor!");
            System.out.println("-----------------------------------");
            System.out.println("[INFO] Tabuleiro atualizado:");
            System.out.println(partida.obtemTabuleiro(idJogador));
            break;
          } else if (partida.ehMinhaVez(idJogador) == 3) {
            System.out.println("-----------------------------------");
            System.out.println("[INFO] Sinto muito, você perdeu!");
            System.out.println("-----------------------------------");
            System.out.println("[INFO] Tabuleiro atualizado:");
            System.out.println(partida.obtemTabuleiro(idJogador));
            break;
          } else if (partida.ehMinhaVez(idJogador) == 4) {
            System.out.println("---");
            System.out.println("[INFO] Fim da partida, houve empate!");
            System.out.println("---");
            break;
          } else if (partida.ehMinhaVez(idJogador) == 5) {
            System.out.println("---");
            System.out.println("[INFO] Fim da partida, vencedor por WO!");
            System.out.println("---");
            break;
          } else if (partida.ehMinhaVez(idJogador) == 6) {
            System.out.println("---");
            System.out.println("[INFO] Fim da partida, perdedor por WO!");
            System.out.println("---");
            break;
          } else if (partida.ehMinhaVez(idJogador) == 1) {
            System.out.println("[INFO] Tabuleiro atualizado:");
            System.out.println(partida.obtemTabuleiro(idJogador));
            System.out.println("[INFO] Faça sua jogada!");
            System.out.println("[INFO] Linha:");
            int linha = entrada.nextInt();
            System.out.println("[INFO] Coluna:");
            int coluna = entrada.nextInt();
            System.out.println("[INFO] Deslocamento:");
            int deslocamento = entrada.nextInt();
            if (partida.movePeca(idJogador, linha, coluna, deslocamento) == 0) {
              System.out.println("[INFO] Movimento invalido. Tente novamente.");
              System.out.println("[INFO] Faça sua jogada!");
              System.out.println("[INFO] Linha:");
              linha = entrada.nextInt();
              System.out.println("[INFO] Coluna:");
              coluna = entrada.nextInt();
              System.out.println("[INFO] Deslocamento:");
              deslocamento = entrada.nextInt();
            } else if(partida.movePeca(idJogador, linha, coluna, deslocamento) == -4) {
              System.out.println("[INFO] Esperando jogado do adversario");
            }
            
            System.out.println("[INFO] Tabuleiro atualizado:");
            System.out.println(partida.obtemTabuleiro(idJogador));
          } else if (partida.ehMinhaVez(idJogador) == -2) {
            try {
              Thread.sleep(5000);
              System.out.println("[INFO] Esperando jogada do adversário.");
            } catch (InterruptedException ex) {
            }
          } else if (partida.temPartida(idJogador) == 0) {
            try {
              Thread.sleep(5000);
              System.out.println("[INFO] Esperando jogada do adversário.");
            } catch (InterruptedException ex) {
            }
          } else if (partida.temPartida(idJogador) == -2) {
            System.out.println("[INFO] Tempo de espera da partida esgotado");
          }
        }
      }

    } catch (Exception e) {
      System.out.println("[INFO] Erro na conexão com o servidor:");
      e.printStackTrace();
    }
  }

}
