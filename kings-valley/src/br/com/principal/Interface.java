package br.com.principal;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {

  public int registraJogador(String nomeJogador) throws RemoteException;;

  public int encerraPartida(int idJogador) throws RemoteException;;

  public int temPartida(int idJogador) throws RemoteException;;

  public String obtemOponente(int idJogador) throws RemoteException;;

  public int ehMinhaVez(int idJogador) throws RemoteException;;

  public void obtemTabuleiro(int idJogador) throws RemoteException;;

  public int movePeca(int idJogador, int linha, int coluna, int deslocamento) throws RemoteException;;

}
