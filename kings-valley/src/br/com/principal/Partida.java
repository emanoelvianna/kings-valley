package br.com.principal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.com.modelo.Tabuleiro;

public class Partida extends UnicastRemoteObject implements Interface {

  private static final long serialVersionUID = 1234L;
  private Random aleatorio;
  private Map<Integer, String> chave;
  private Tabuleiro tabuleiro;
  private int jogadores;
  private boolean primeiroJogador;
  private int iniciaPartida;
  private int quemJogou;
  private int quemJoga;

  public Partida() throws RemoteException {
    super();
    this.aleatorio = new Random();
    this.chave = new HashMap<>();
    this.tabuleiro = new Tabuleiro();
    this.jogadores = 0;
    this.primeiroJogador = true;
  }

  @Override
  public int registraJogador(String nomeJogador) throws RemoteException {
    int id = aleatorio.nextInt();
    while (chave.containsKey(id)) {
      id = aleatorio.nextInt();
    }
    chave.put(id, nomeJogador);
    this.jogadores++;
    if (this.primeiroJogador) {
      this.primeiroJogador = false;
      this.iniciaPartida = id;
      this.quemJoga = id;
    }
    return id;
  }

  @Override
  public int encerraPartida(int idJogador) throws RemoteException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int temPartida(int idJogador) throws RemoteException {
    // TODO: considerar o tempo para ralizar jogada
    if (this.jogadores < 2) {
      return 0;
    } else if (this.jogadores == 2) {
      return 1;
    }
    return -1;
  }

  @Override
  public String obtemOponente(int idJogador) throws RemoteException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int ehMinhaVez(int idJogador) throws RemoteException {
    if (quemJoga == idJogador) {
      return 1;
    } else if (quemJoga != idJogador) {
      return 0;
    }
    return -1;
  }

  @Override
  public void obtemTabuleiro(int idJogador) throws RemoteException {
    tabuleiro.imprimirTabuleiro();
  }

  @Override
  public int movePeca(int idJogador, int linha, int coluna, int deslocamento) throws RemoteException {
    tabuleiro.mover(linha, coluna, deslocamento);
    return 0;
  }

}
