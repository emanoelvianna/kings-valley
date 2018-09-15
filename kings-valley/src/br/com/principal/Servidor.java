package br.com.principal;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      System.out.println("[INFO] Aplicação registrada na porta 1099.");
    } catch (RemoteException e) {
      System.out.println("[INFO] Aplicação já esta em execução.");
    }

    try {
      Naming.rebind("jogo", new Partida());
      System.out.println("[INFO] Servidor do jogo está pronto.");
    } catch (Exception e) {
      System.out.println("[INFO] Falha ao suber servidor do jogo:");
      e.printStackTrace();
    }
  }
}
