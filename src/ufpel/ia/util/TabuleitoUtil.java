/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ufpel.ia.algoritimos.Heuristica;
import ufpel.ia.base.Nodo;

/**
 *
 * @author WeslenSchiavon
 */
public class TabuleitoUtil {

    private static Nodo ESTADOFINAL;

    public static List<Nodo> GeraJogadas(Nodo base) {

        List<Nodo> listaDeNodosFilhos = new ArrayList<>();

        if (base.getPosColunaJogavel() > 0) {
            Nodo novoFilho = (Nodo) base.clone();

            novoFilho.pai = base;
            novoFilho.Move(base.getPosLinhaJogavel(), base.getPosColunaJogavel() - 1);
            listaDeNodosFilhos.add(novoFilho);
        }

        if (base.getPosColunaJogavel() < base.getTamanhoTabuleiro() - 1) {
            Nodo novoFilho = (Nodo) base.clone();

            novoFilho.pai = base;
            novoFilho.Move(base.getPosLinhaJogavel(), base.getPosColunaJogavel() + 1);
            listaDeNodosFilhos.add(novoFilho);
        }

        if (base.getPosLinhaJogavel() > 0) {
            Nodo novoFilho = (Nodo) base.clone();

            novoFilho.pai = base;
            novoFilho.Move(base.getPosLinhaJogavel() - 1, base.getPosColunaJogavel());
            listaDeNodosFilhos.add(novoFilho);
        }

        if (base.getPosLinhaJogavel() < base.getTamanhoTabuleiro() - 1) {
            Nodo novoFilho = (Nodo) base.clone();

            novoFilho.pai = base;
            novoFilho.Move(base.getPosLinhaJogavel() + 1, base.getPosColunaJogavel());
            listaDeNodosFilhos.add(novoFilho);
        }

        return listaDeNodosFilhos;
    }

    public static boolean EhJogadaFinal(Nodo base) {

        InicializaEstadoFinal(base);

        return base.equals(TabuleitoUtil.ESTADOFINAL);
    }

    public static List<Nodo> RetornaCaminho(Nodo base) {
        List<Nodo> retorno = new ArrayList<>();
        while (base != null) {
            retorno.add(base);

            base = base.pai;
        }
        return retorno;
    }

    public static int Profundidade(Nodo base) {
        int count = 0;
        while (base != null) {
            base = base.pai;
            count++;
        }
        return count;
    }

    public static int CalculaValorHeuristica(Heuristica qualquer, Nodo base) {
        return qualquer.Avalia(base);
    }

    private static void InicializaEstadoFinal(Nodo base) {
        if ((TabuleitoUtil.ESTADOFINAL == null) || (TabuleitoUtil.ESTADOFINAL.getTamanhoTabuleiro() != base.getTamanhoTabuleiro())) {
            TabuleitoUtil.ESTADOFINAL = new Nodo(base.getTamanhoTabuleiro());
            int[][] estadoFinal = new int[base.getTamanhoTabuleiro()][base.getTamanhoTabuleiro()];
            int contador = 1;

            for (int i = 0; i < base.getTamanhoTabuleiro(); i++) {
                for (int j = 0; j < base.getTamanhoTabuleiro(); j++) {
                    estadoFinal[i][j] = contador;
                    contador++;
                }
            }

            int ultimaPos = base.getTamanhoTabuleiro() - 1;
            estadoFinal[ultimaPos][ultimaPos] = -1;
            TabuleitoUtil.ESTADOFINAL.setTabuleiro(estadoFinal, ultimaPos, ultimaPos);
        }
    }

    public static void GeraTabuleiroInicial(Nodo base, int numeroDeEmbaralhamento) {
        int tamanho = base.getTamanhoTabuleiro();

        int[][] tabuleiro = new int[tamanho][tamanho];
        int contador = 1;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = contador;
                contador++;
            }
        }
        tabuleiro[tamanho - 1][tamanho - 1] = -1;

        Random rand = new Random();
        int posLinhaJogavel = tamanho - 1;
        int posColunaJogavel = tamanho - 1;
        for (int i = 0; i < numeroDeEmbaralhamento; i++) {
            if (rand.nextBoolean()) {
                if (posLinhaJogavel == tamanho - 1) {
                    int valorTemp = tabuleiro[posLinhaJogavel - 1][posColunaJogavel];
                    tabuleiro[posLinhaJogavel - 1][posColunaJogavel] = -1;
                    tabuleiro[posLinhaJogavel][posColunaJogavel] = valorTemp;
                    posLinhaJogavel--;
                } else if (posLinhaJogavel == 0) {
                    int valorTemp = tabuleiro[posLinhaJogavel + 1][posColunaJogavel];
                    tabuleiro[posLinhaJogavel + 1][posColunaJogavel] = -1;
                    tabuleiro[posLinhaJogavel][posColunaJogavel] = valorTemp;
                    posLinhaJogavel++;
                } else {
                    int valor = rand.nextBoolean() ? -1 : 1;

                    int valorTemp = tabuleiro[posLinhaJogavel + valor][posColunaJogavel];
                    tabuleiro[posLinhaJogavel + valor][posColunaJogavel] = -1;
                    tabuleiro[posLinhaJogavel][posColunaJogavel] = valorTemp;
                    posLinhaJogavel += valor;
                }

            } else {
                if (posColunaJogavel == tamanho - 1) {
                    int valorTemp = tabuleiro[posLinhaJogavel][posColunaJogavel - 1];
                    tabuleiro[posLinhaJogavel][posColunaJogavel - 1] = -1;
                    tabuleiro[posLinhaJogavel][posColunaJogavel] = valorTemp;
                    posColunaJogavel--;
                } else if (posColunaJogavel == 0) {
                    int valorTemp = tabuleiro[posLinhaJogavel][posColunaJogavel + 1];
                    tabuleiro[posLinhaJogavel][posColunaJogavel + 1] = -1;
                    tabuleiro[posLinhaJogavel][posColunaJogavel] = valorTemp;
                    posColunaJogavel++;
                } else {
                    int valor = rand.nextBoolean() ? -1 : 1;

                    int valorTemp = tabuleiro[posLinhaJogavel][posColunaJogavel + valor];
                    tabuleiro[posLinhaJogavel][posColunaJogavel + valor] = -1;
                    tabuleiro[posLinhaJogavel][posColunaJogavel] = valorTemp;
                    posColunaJogavel += valor;
                }
            }
        }
        base.setTabuleiro(tabuleiro, posLinhaJogavel, posColunaJogavel);
    }
}
