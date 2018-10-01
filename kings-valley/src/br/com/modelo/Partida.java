package br.com.modelo;

import java.util.ArrayList;
import java.util.List;

public class Partida {

  private static final int QUANTIDADE_JOGADORES = 2;
  private Tabuleiro tabuleiro;
  private List<Jogador> jogadores;
  private long tempo;
  private boolean finalizada;

  public Partida() {
    this.tabuleiro = new Tabuleiro();
    this.jogadores = new ArrayList<>(QUANTIDADE_JOGADORES);
    this.tempo = System.currentTimeMillis();
    this.finalizada = false;
  }

  public Tabuleiro obterTabuleiro() {
    return this.tabuleiro;
  }

  public void adicionarJogador(Jogador jogador) {
    if (this.jogadores.size() < 2) {
      if (this.jogadores.isEmpty()) { // sou o primeiro a entrar?
        jogador.ehMinhaVez(true);
      }
      this.jogadores.add(jogador);
    }
  }

  public boolean esperandoJogador() {
    return this.jogadores.size() < 2;
  }

  public List<Jogador> obterJogadores() {
    return this.jogadores;
  }

  public boolean idJogadorUnico(int idJogador) {
    for (Jogador jogador : this.jogadores) {
      if (jogador.obterId().equals(idJogador)) {
        return false;
      }
    }
    return true;
  }

  public Jogador obterJogadorPeloId(Integer idJogador) {
    for (Jogador jogador : this.jogadores) {
      if (jogador.obterId().equals(idJogador))
        return jogador;
    }
    return null;
  }

  public void jogadaRealizada(int idJogador) {
    for (Jogador jogador : this.jogadores) {
      if (jogador.obterId().equals(idJogador))
        jogador.ehMinhaVez(false); // passou a vez
      else
        jogador.ehMinhaVez(true);
    }
  }

  public void finalizarPartida() {
    this.finalizada = true;
  }

  public boolean partidaFinalizada() {
    return this.finalizada;
  }

  public void atualizarTempoDeEspera() {
    this.tempo = System.currentTimeMillis();
  }

  public boolean tempoEsgotado() {
    // return (System.currentTimeMillis() - this.timestamp) >= 60 * 1000;
    return false;
  }

}
