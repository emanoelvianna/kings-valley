package br.com.modelo;

public class Tabuleiro {

  private static int TAMANHO = 5;
  public String[][] celulas;

  public Tabuleiro() {
    this.iniciar();
    this.posicionarPecas();
  }

  public void mover(int linhaOrigem, int colunaOrigem, int deslocamento) {
    switch (deslocamento) {
    case 0:
      this.paraDireita(linhaOrigem, colunaOrigem);
      break;
    case 1:
      this.diagonalDireitaInferior(linhaOrigem, colunaOrigem);
      break;
    case 2:
      this.paraBaixo(linhaOrigem, colunaOrigem);
      break;
    case 3:
      this.diagonalEsquerdaInferior(linhaOrigem, colunaOrigem);
      break;
    case 4:
      this.paraEsquerda(linhaOrigem, colunaOrigem);
      break;
    case 5:
      this.diagonalEsquerdaSuperior(linhaOrigem, colunaOrigem);
      break;
    case 6:
      this.paraCima(linhaOrigem, colunaOrigem);
      break;
    case 7:
      this.diagonalDireitaSuperior(linhaOrigem, colunaOrigem);
      break;
    }
  }

  private void diagonalDireitaSuperior(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha - 1, coluna + 1)) {
        celulas[linha - 1][coluna + 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha - 1;
        coluna = coluna + 1;
      } else {
        break;
      }
    }
  }

  private void diagonalDireitaInferior(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha + 1, coluna + 1)) {
        celulas[linha + 1][coluna + 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha + 1;
        coluna = coluna + 1;
      } else {
        break;
      }
    }
  }

  private void diagonalEsquerdaSuperior(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha - 1, coluna - 1)) {
        celulas[linha - 1][coluna - 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha - 1;
        coluna = coluna - 1;
      } else {
        break;
      }
    }
  }

  private void diagonalEsquerdaInferior(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = 0; i < TAMANHO; i++) {
      if (this.jogadaValida(linha + 1, coluna - 1)) {
        celulas[linha + 1][coluna - 1] = peca;
        celulas[linha][coluna] = "-";
        linha = linha + 1;
        coluna = coluna - 1;
      } else {
        break;
      }
    }
  }

  private void paraBaixo(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = linha; i < TAMANHO; i++) {
      if (this.jogadaValida(i + 1, coluna)) {
        celulas[i + 1][coluna] = peca;
        celulas[linha][coluna] = "-";
        linha = i + 1;
      } else {
        break;
      }
    }
  }

  private void paraCima(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = linha; i > 0; i--) {
      if (this.jogadaValida(i - 1, coluna)) {
        celulas[i - 1][coluna] = peca;
        celulas[linha][coluna] = "-";
        linha = i - 1;
      } else {
        break;
      }
    }
  }

  private void paraEsquerda(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = coluna; i > 0; i--) {
      if (this.jogadaValida(linha, i - 1)) {
        celulas[linha][i - 1] = peca;
        celulas[linha][coluna] = "-";
        coluna = i - 1;
      } else {
        break;
      }
    }
  }

  private void paraDireita(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    for (int i = coluna; i < TAMANHO; i++) {
      if (this.jogadaValida(linha, i + 1)) {
        celulas[linha][i + 1] = peca;
        celulas[linha][coluna] = "-";
        coluna = i + 1;
      } else {
        break;
      }
    }
  }

  // TODO: Consigo utilizar para informar ao usuário se a jogada é valida?
  private boolean jogadaValida(int linha, int coluna) {
    if (posicaoValida(linha, coluna)) {
      if (ehPosicaoVazia(linha, coluna))
        return true;
      else
        return false;
    } else {
      return false;
    }
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
