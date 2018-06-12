/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.base;

import java.util.Arrays;

/**
 *
 * @author WeslenSchiavon
 */
public class Nodo implements Cloneable, Comparable<Nodo> {

    private int[][] tabuleiro;
    private int tamanhoTabuleiro;
    private int posLinhaJogavel;
    private int posColunaJogavel;
    public Nodo pai;
    public boolean visitado;
    public int valorHeuristica;

    public Nodo(int TamanhoTabuleiro) {
        this.tamanhoTabuleiro = TamanhoTabuleiro;
        this.tabuleiro = new int[TamanhoTabuleiro][TamanhoTabuleiro];
    }

    public Nodo(int[][] tabuleiro, int tamanhoTabuleiro, int posLinhaJogavel, int posColunaJogavel, Nodo pai) {
        this.tabuleiro = tabuleiro;
        this.tamanhoTabuleiro = tamanhoTabuleiro;
        this.posLinhaJogavel = posLinhaJogavel;
        this.posColunaJogavel = posColunaJogavel;
        this.pai = pai;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public int getValorHeuristica() {
        return valorHeuristica;
    }

    public void setValorHeuristica(int valorHeuristica) {
        this.valorHeuristica = valorHeuristica;
    }

    public int getTamanhoTabuleiro() {
        return tamanhoTabuleiro;
    }

    public int getPosLinhaJogavel() {
        return posLinhaJogavel;
    }

    public int getPosColunaJogavel() {
        return posColunaJogavel;

    }

    public void setTabuleiro(int[][] tabuleiro, int posLinhaJogavel, int posColunaJogavel) {
        this.tabuleiro = tabuleiro;
        this.posLinhaJogavel = posLinhaJogavel;
        this.posColunaJogavel = posColunaJogavel;
    }

    public void Move(int linha, int coluna) {
        this.tabuleiro[this.posLinhaJogavel][this.posColunaJogavel] = this.tabuleiro[linha][coluna];
        this.tabuleiro[linha][coluna] = -1;
        this.posLinhaJogavel = linha;
        this.posColunaJogavel = coluna;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Arrays.deepHashCode(this.tabuleiro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        if (!Arrays.deepEquals(this.tabuleiro, other.tabuleiro)) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() {
        int[][] novo = new int[this.tamanhoTabuleiro][this.tamanhoTabuleiro];

        for (int i = 0; i < this.tamanhoTabuleiro; i++) {
            for (int j = 0; j < this.tamanhoTabuleiro; j++) {
                novo[i][j] = this.tabuleiro[i][j];
            }
        }

        return new Nodo(novo,
                this.tamanhoTabuleiro,
                this.posLinhaJogavel,
                this.posColunaJogavel,
                this.pai);
    }

    @Override
    public String toString() {
        String retorno = "";
        for (int i = 0; i < this.tamanhoTabuleiro; i++) {
            for (int j = 0; j < this.tamanhoTabuleiro; j++) {
                retorno += this.tabuleiro[i][j] + " ";
            }
            retorno += "\n";
        }
        return retorno;
    }

    @Override
    public int compareTo(Nodo other) {
        if (this.valorHeuristica < other.valorHeuristica) {
            return -1;
        } else if (this.valorHeuristica > other.valorHeuristica) {
            return 1;
        }
        return 0;
    }

}
