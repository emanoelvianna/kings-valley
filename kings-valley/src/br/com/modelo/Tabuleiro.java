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
    // TODO Auto-generated method stub
  }

  private void diagonalEsquerdaSuperior(int linhaOrigem, int colunaOrigem) {
    // TODO Auto-generated method stub
  }

  private void paraEsquerda(int linhaOrigem, int colunaOrigem) {
    // TODO Auto-generated method stub
  }

  private void diagonalDireitaInferior(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    if (this.jogadaValida(linha + 1, coluna + 1)) {
      for (int i = coluna; i > 0; i++) {
        if(this.posicaoValida(linha + 1, i+1)){
          if (this.ehPosicaoVazia(linha + 1, i + 1)) {
            celulas[linha + 1][i + 1] = peca;
            celulas[linha][i] = "-";
            linha = linha + 1;
          } else {
            break;
          }
        } else {
          break;
        }
      }
    } else {
      for (int i = coluna; i > TAMANHO; i--) {
        if (this.ehPosicaoVazia(linha, i - 1)) {
          celulas[linha - 1][i - 1] = peca;
          celulas[linha][i] = "-";
          linha = linha - 1;
        } else {
          break;
        }
      }
    }
  }

  private void diagonalEsquerdaInferior(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    int linha = linhaOrigem;
    int coluna = colunaOrigem;
    if (this.jogadaValida(linha - 1, coluna - 1)) {
      for (int i = coluna; i > 0; i--) {
        if (this.ehPosicaoVazia(linha - 1, i - 1)) {
          celulas[linha - 1][i - 1] = peca;
          celulas[linha][i] = "-";
          linha = linha - 1;
        } else {
          break;
        }
      }
    } else {
      for (int i = coluna; i > TAMANHO; i++) {
        if (this.ehPosicaoVazia(linha, i + 1)) {
          celulas[linha + 1][i + 1] = peca;
          celulas[linha][i] = "-";
          linha = linha + 1;
        } else {
          break;
        }
      }
    }
  }

  private void paraBaixo(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    if (!this.posicaoValida(linhaOrigem, colunaOrigem + 1)) {
      for (int i = colunaOrigem; i < TAMANHO; i++) {
        if (this.ehPosicaoVazia(linhaOrigem, i + 1)) {
          celulas[linhaOrigem][i + 1] = peca;
          celulas[linhaOrigem][i] = "-";
        } else {
          break;
        }
      }
    } else {
      for (int i = colunaOrigem; i > 0; i--) {
        if (this.ehPosicaoVazia(linhaOrigem, i - 1)) {
          celulas[linhaOrigem][i - 1] = peca;
          celulas[linhaOrigem][i] = "-";
        } else {
          break;
        }
      }
    }
  }

  private void paraCima(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    if (!this.posicaoValida(linhaOrigem, colunaOrigem + 1)) {
      for (int i = colunaOrigem; i > 0; i--) {
        if (this.ehPosicaoVazia(linhaOrigem, i - 1)) {
          celulas[linhaOrigem][i - 1] = peca;
          celulas[linhaOrigem][i] = "-";
        } else {
          break;
        }
      }
    } else {
      for (int i = colunaOrigem; i < TAMANHO; i++) {
        if (this.ehPosicaoVazia(linhaOrigem, i + 1)) {
          celulas[linhaOrigem][i + 1] = peca;
          celulas[linhaOrigem][i] = "-";
        } else {
          break;
        }
      }
    }
  }

  private void paraDireita(int linhaOrigem, int colunaOrigem) {
    String peca = celulas[linhaOrigem][colunaOrigem];
    for (int i = 1; i < TAMANHO; i++) {
      if (this.ehPosicaoVazia(linhaOrigem + i, colunaOrigem)) {
        celulas[linhaOrigem + i][colunaOrigem] = peca;
        celulas[linhaOrigem][colunaOrigem] = "-";
      } else {
        break;
      }
    }
  }

  public boolean ganhador() {
    if (!this.ehPosicaoVazia(2, 2)) {
      return true;
    } else {
      return false;
    }
  }

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
    if (linha < TAMANHO && coluna < TAMANHO) {
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

  public void imprimirTabuleiro() {
    System.out.println("\n  a  b  c  d  e");
    for (int linha = 0; linha < TAMANHO; linha++) {
      System.out.print(linha + 1);
      for (int coluna = 0; coluna < TAMANHO; coluna++) {
        System.out.print(" " + celulas[linha][coluna] + " ");
      }
      System.out.print("\n");
    }
  }
}
