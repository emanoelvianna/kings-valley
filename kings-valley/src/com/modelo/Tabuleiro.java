package com.modelo;

public class Tabuleiro {

	private static int TAMANHO = 5;
	public char[][] celulas;

	public Tabuleiro() {
		this.iniciar();
		// this.posicionarPecas();
	}

	private void iniciar() {
		this.celulas = new char[TAMANHO][TAMANHO];
		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				celulas[i][j] = '-';
			}
		}
	}

	private void posicionarPecas() {
		celulas[1][1] = (char) ('T');// Torre Branca
		celulas[1][2] = (char) ('C');// Cavalo Branca
		celulas[1][3] = (char) ('B');// Bispo Branca
		celulas[1][4] = (char) ('K');// Rei Branca
		celulas[1][5] = (char) ('R');// Rainha Branca
		celulas[1][6] = (char) ('B');// Bispo Branca
	}

	public void imprimirTabuleiro() {
		System.out.println("\n            a   b   c   d   e   f   g   h");
		System.out.println("");

		for (int linha = TAMANHO - 2; linha > 0; linha--) {
			System.out.print("       " + linha + " ");
			for (int coluna = 1; coluna < TAMANHO - 1; coluna++) {
				System.out.print("   " + celulas[linha][coluna]);
			}
			System.out.println();
		}
		System.out.println("\n            a   b   c   d   e   f   g   h");
	}

}
