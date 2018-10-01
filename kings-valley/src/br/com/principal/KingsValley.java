package br.com.principal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.modelo.Jogador;
import br.com.modelo.Partida;
import br.com.modelo.Tabuleiro;

public class KingsValley extends UnicastRemoteObject implements Interface {

  private static final long serialVersionUID = 1234L;
  private Random aleatorio;
  private Map<Integer, Partida> partidas;

  public KingsValley() throws RemoteException {
    super();
    this.aleatorio = new Random();
    this.partidas = new HashMap<>();
  }

  @Override
  public int registraJogador(String nomeJogador) throws RemoteException {
    Integer idJogador;
    Partida partida;
    partida = this.partidaEsperandoJogador();
    if (partida != null) { // existe alguma partida esperando jogador?
      idJogador = aleatorio.nextInt();
      while (!partida.idJogadorUnico(idJogador)) {
        idJogador = aleatorio.nextInt();
      }
      Jogador jogador = new Jogador(idJogador, nomeJogador, "Escura");
      partida.adicionarJogador(jogador);
    } else {
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
    }
    return idJogador;
  }

  @Override
  public int encerraPartida(int idJogador) throws RemoteException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int temPartida(int idJogador) throws RemoteException {
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
    return -1;
  }

  @Override
  public String obtemOponente(int idJogador) throws RemoteException {
    Partida partida = this.obterPartidaPeloIdJogador(idJogador);
    List<Jogador> jogadores = partida.obterJogadores();
    for (Jogador jogador : jogadores) {
      if (!jogador.obterId().equals(idJogador))
        return jogador.obterNome();
    }
    return new String();
  }

  @Override
  public int ehMinhaVez(int idJogador) throws RemoteException {
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
    } else {
      // TODO: Considerar os outros casos!
    }
    return -1;
  }

  @Override
  public StringBuffer obtemTabuleiro(int idJogador) throws RemoteException {
    Partida partida = this.obterPartidaPeloIdJogador(idJogador);
    return partida.obterTabuleiro().obterEstadoDoTabuleiro();
  }

  @Override
  public int movePeca(int idJogador, int linha, int coluna, int deslocamento) throws RemoteException {
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
          partida.jogadaRealizada(idJogador);
          // TODO: Verificar se a jogada foi valida!
          tabuleiro.mover(linha, coluna, deslocamento);
          return 1;
        } else {
          return -4;
        }
      }
    } else {
      return -1;
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

}
