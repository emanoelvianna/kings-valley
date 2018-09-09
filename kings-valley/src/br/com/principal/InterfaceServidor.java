package br.com.principal;

public interface InterfaceServidor {

  public int registraJogador(String nomeJogador);

  public int encerraPartida(int idJogador);

  public int temPartida(int idJogador);

  public String obtemOponente(int idJogador);

  public int ehMinhaVez(int idJogador);

  public String obtemTabuleiro(int idJogador);

  public int movePeca(int idJogador);

}
