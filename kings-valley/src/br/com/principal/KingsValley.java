package br.com.principal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import br.com.modelo.Jogador;
import br.com.modelo.Partida;
import br.com.modelo.Tabuleiro;

public class KingsValley extends UnicastRemoteObject implements Interface {

  private static final int QUANTIDADE_MAXIMA = 500;
  private static final long serialVersionUID = 1234L;
  private Random aleatorio;
  private Map<Integer, Partida> partidas;
  private ReadWriteLock bloqueadorDeLeituraEscrita = new ReentrantReadWriteLock();
  private Lock bloqueadorDeLeitura = bloqueadorDeLeituraEscrita.readLock();
  private Lock bloequeadorDeEscrita = bloqueadorDeLeituraEscrita.writeLock();

  public KingsValley() throws RemoteException {
    super();
    this.aleatorio = new Random();
    this.partidas = new HashMap<>();
  }

  @Override
  public int registraJogador(String nomeJogador) throws RemoteException {
    Integer idJogador;
    Partida partida;
    bloequeadorDeEscrita.lock();
    partida = this.partidaEsperandoJogador();
    try {
      if (partida != null) { // existe alguma partida esperando jogador?
        idJogador = aleatorio.nextInt();
        while (!partida.idJogadorUnico(idJogador)) {
          idJogador = aleatorio.nextInt();
        }
        Jogador jogador = new Jogador(idJogador, nomeJogador, "Escura");
        partida.adicionarJogador(jogador);
      } else if (this.partidas.size() <= QUANTIDADE_MAXIMA) {
        partida = new Partida();
        Integer idPartida = aleatorio.nextInt();
        while (partidas.containsKey(idPartida)) {
          idPartida = aleatorio.nextInt();
        }
        this.partidas.put(idPartida, partida);
        idJogador = aleatorio.nextInt();
        while (!partida.idJogadorUnico(idJogador)) {
          idJogador = aleatorio.nextInt();
        }
        Jogador jogador = new Jogador(idJogador, nomeJogador, "Clara");
        partida.adicionarJogador(jogador);
      } else {
        return -2;
      }
    } finally {
      bloequeadorDeEscrita.unlock();
    }
    return idJogador;
  }

  @Override
  public int encerraPartida(int idJogador) throws RemoteException {
    bloequeadorDeEscrita.lock();
    try {
      Partida partida = this.obterPartidaPeloIdJogador(idJogador);
      if (!partida.esperandoJogador() && !partida.tempoEsgotado())
        if (!partida.partidaFinalizada()) {
          this.encerraPartida(partida);
          return 0;
        }
    } finally {
      bloequeadorDeEscrita.unlock();
    }
    return -1;
  }

  @Override
  public int temPartida(int idJogador) throws RemoteException {
    bloqueadorDeLeitura.lock();
    try {
      Partida partida = this.obterPartidaPeloIdJogador(idJogador);
      if (partida.tempoEsgotado()) {
        return -2;
      } else if (partida.esperandoJogador()) {
        return 0;
      } else if (!partida.tempoEsgotado() && !partida.esperandoJogador()) {
        Jogador jogador = partida.obterJogadorPeloId(idJogador);
        if (jogador.ehMinhaVez()) {
          return 1;
        } else {
          return 2;
        }
      }
    } finally {
      bloqueadorDeLeitura.unlock();
    }
    return -1;
  }

  @Override
  public String obtemOponente(int idJogador) throws RemoteException {
    bloqueadorDeLeitura.lock();
    try {
      Partida partida = this.obterPartidaPeloIdJogador(idJogador);
      List<Jogador> jogadores = partida.obterJogadores();
      for (Jogador jogador : jogadores) {
        if (!jogador.obterId().equals(idJogador))
          return jogador.obterNome();
      }
    } finally {
      bloqueadorDeLeitura.unlock();
    }
    return new String();
  }

  @Override
  public int ehMinhaVez(int idJogador) throws RemoteException {
    bloqueadorDeLeitura.lock();
    try {
      Partida partida = this.obterPartidaPeloIdJogador(idJogador);
      Jogador jogador = partida.obterJogadorPeloId(idJogador);
      if (partida.esperandoJogador()) {
        return -2;
      } else if (!partida.esperandoJogador() && !partida.tempoEsgotado() && !partida.partidaFinalizada()) {
        if (partida.obterTabuleiro().ganhador()) {
          String ganhador = partida.obterTabuleiro().obterGanhador();
          if (jogador.obterMinhaCor().equals(ganhador)) {
            return 2;
          } else {
            return 3;
          }
        } else if (jogador.ehMinhaVez()) {
          return 1;
        } else {
          return 0;
        }
      }
    } finally {
      bloqueadorDeLeitura.unlock();
    }
    return -1;
  }

  @Override
  public StringBuffer obtemTabuleiro(int idJogador) throws RemoteException {
    bloqueadorDeLeitura.lock();
    try {
      Partida partida = this.obterPartidaPeloIdJogador(idJogador);
      if (partida != null)
        return partida.obterTabuleiro().obterEstadoDoTabuleiro();
    } finally {
      bloqueadorDeLeitura.unlock();
    }
    return new StringBuffer();
  }

  @Override
  public int movePeca(int idJogador, int linha, int coluna, int deslocamento) throws RemoteException {
    bloequeadorDeEscrita.lock();
    try {
      Partida partida = this.obterPartidaPeloIdJogador(idJogador);
      if (partida != null) {
        if (partida.tempoEsgotado()) {
          return 2;
        } else if (partida.esperandoJogador()) {
          return -2;
        } else if (!partida.tempoEsgotado() && !partida.esperandoJogador()) {
          Jogador jogador = partida.obterJogadorPeloId(idJogador);
          if (jogador.ehMinhaVez()) {
            Tabuleiro tabuleiro = partida.obterTabuleiro();
            if (tabuleiro.mover(linha, coluna, deslocamento)) {
              partida.jogadaRealizada(idJogador);
              return 1;
            } else {
              return 0;
            }
          } else {
            return -4;
          }
        }
      } else {
        return -1;
      }
    } finally {
      bloequeadorDeEscrita.unlock();
    }
    return 0;
  }

  private Partida obterPartidaPeloIdJogador(Integer idJogador) {
    Partida partida = null;
    for (Map.Entry<Integer, Partida> p : this.partidas.entrySet()) {
      List<Jogador> jogadores = p.getValue().obterJogadores();
      for (Jogador jogador : jogadores) {
        if (jogador.obterId().equals(idJogador)) {
          partida = p.getValue();
        }
      }
    }
    return partida;
  }

  private Partida partidaEsperandoJogador() {
    Partida partida = null;
    for (Map.Entry<Integer, Partida> p : this.partidas.entrySet()) {
      if (p.getValue().esperandoJogador())
        partida = p.getValue();
    }
    return partida;
  }

  private void encerraPartida(Partida partida) {
    for (Map.Entry<Integer, Partida> p : this.partidas.entrySet()) {
      if (p.equals(partida))
        this.partidas.remove(p);
    }
  }

}
