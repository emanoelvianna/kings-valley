package br.com.modelo;

public class Tabuleiro {

  private static int TAMANHO = 5;
  public String[][] celulas;

  public Tabuleiro() {
    this.iniciar();
    this.posicionarPecas();
  }

  public boolean mover(int linhaOrigem, int colunaOrigem, int deslocamento) {
    if (this.posicaoContemPeca(linhaOrigem, colunaOrigem)) {
      switch (deslocamento) {
      case 0:
        return this.paraDireita(linhaOrigem, colunaOrigem);
      case 1:
        return this.diagonalDireitaInferior(linhaOrigem, colunaOrigem);
      case 2:
        return this.paraBaixo(linhaOrigem, colunaOrigem);
      case 3:
        return this.diagonalEsquerdaInferior(linhaOrigem, colunaOrigem);
      case 4:
        return this.paraEsquerda(linhaOrigem, colunaOrigem);
      case 5:
        return this.diagonalEsquerdaSuperior(linhaOrigem, colunaOrigem);
      case 6:
        return this.paraCima(linhaOrigem, colunaOrigem);
      case 7:
        return this.diagonalDireitaSuperior(linhaOrigem, colunaOrigem);
      }
    }
    return false;
  }

  private boolean diagonalDireitaSuperior(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha - 1, coluna + 1)) {
        celulas[linha - 1][coluna + 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha - 1;
        coluna = coluna + 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean diagonalDireitaInferior(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha + 1, coluna + 1)) {
        celulas[linha + 1][coluna + 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha + 1;
        coluna = coluna + 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean diagonalEsquerdaSuperior(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha - 1, coluna - 1)) {
        celulas[linha - 1][coluna - 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha - 1;
        coluna = coluna - 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean diagonalEsquerdaInferior(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha + 1, coluna - 1)) {
        celulas[linha + 1][coluna - 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha + 1;
        coluna = coluna - 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean paraBaixo(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = linha; i < TAMANHO; i++) {
      if (this.jogadaValida(i + 1, coluna)) {
        celulas[i + 1][coluna] = peca;
        celulas[linha][coluna] = "-";
        linha = i + 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean paraCima(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = linha; i > 0; i--) {
      if (this.jogadaValida(i - 1, coluna)) {
        celulas[i - 1][coluna] = peca;
        celulas[linha][coluna] = "-";
        linha = i - 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean paraEsquerda(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = coluna; i > 0; i--) {
      if (this.jogadaValida(linha, i - 1)) {
        celulas[linha][i - 1] = peca;
        celulas[linha][coluna] = "-";
        coluna = i - 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean paraDireita(int linhaOrigem, int colunaOrigem) {
    boolean movimentoRealizado = false;
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = coluna; i < TAMANHO; i++) {
      if (this.jogadaValida(linha, i + 1)) {
        celulas[linha][i + 1] = peca;
        celulas[linha][coluna] = "-";
        coluna = i + 1;
        movimentoRealizado = true;
      } else {
        break;
      }
    }
    return movimentoRealizado;
  }

  private boolean jogadaValida(int linha, int coluna) {
    if (posicaoValida(linha, coluna))
      if (ehPosicaoVazia(linha, coluna))
        return true;
    return false;
  }

  private boolean posicaoValida(int linha, int coluna) {
    if (linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO) {
      return true;
    } else {
      return false;
    }
  }

  private boolean ehPosicaoVazia(int linha, int coluna) {
    if (celulas[linha][coluna].equals("-")) {
      return true;
    } else {
      return false;
    }
  }

  private boolean posicaoContemPeca(int linha, int coluna) {
    if (!celulas[linha][coluna].equals("-")) {
      return true;
    } else {
      return false;
    }
  }

  public boolean ganhador() {
    if (!this.ehPosicaoVazia(2, 2)) {
      if (celulas[2][2].equals("Rc") || celulas[2][2].equals("Re")) {
        System.out.println("[INFO] Ganhador.");
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public String obterGanhador() {
    if (this.ganhador()) {
      if (celulas[2][2].equals("Rc"))
        return new String("Clara");
      else
        return new String("Escura");
    }
    return new String();
  }

  private void iniciar() {
    this.celulas = new String[TAMANHO][TAMANHO];
    for (int linha = 0; linha < TAMANHO; linha++) {
      for (int coluna = 0; coluna < TAMANHO; coluna++) {
        celulas[linha][coluna] = "-";
      }
    }
  }

  private void posicionarPecas() {
    celulas[0][0] = (String) ("Sc");
    celulas[1][0] = (String) ("Sc");
    celulas[2][0] = (String) ("Rc");
    celulas[3][0] = (String) ("Sc");
    celulas[4][0] = (String) ("Sc");

    celulas[0][4] = (String) ("Se");
    celulas[1][4] = (String) ("Se");
    celulas[2][4] = (String) ("Re");
    celulas[3][4] = (String) ("Se");
    celulas[4][4] = (String) ("Se");
  }

  public StringBuffer obterEstadoDoTabuleiro() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("\n  0  1  2  3  4\n");
    for (int linha = 0; linha < TAMANHO; linha++) {
      buffer.append(linha);
      for (int coluna = 0; coluna < TAMANHO; coluna++) {
        System.out.println(" " + celulas[linha][coluna] + " ");
        buffer.append(" " + celulas[linha][coluna] + " ");
      }
      buffer.append("\n");
    }
    return buffer;
  }

}
