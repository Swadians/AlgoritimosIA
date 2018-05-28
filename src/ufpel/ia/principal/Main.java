/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.principal;

import java.util.List;
import ufpel.ia.algoritimos.Busca;
import ufpel.ia.algoritimos.BuscaBestFirst;
import ufpel.ia.algoritimos.HeuristicaContaPosDist;
import ufpel.ia.base.Nodo;

/**
 *
 * @author WeslenSchiavon
 */
public class Main {

    public static void main(String[] args) {

        Busca algoritimoBusca = new BuscaBestFirst(new HeuristicaContaPosDist());

        Nodo base = new Nodo(4);
        int[][] tabuleiro = {{5, 1, 2, 3}, {9, 6, 7, 4}, {13, 10, 11, 8}, {-1, 14, 15, 12}};

        base.setTabuleiro(tabuleiro, 3, 0);

        List<Nodo> jogadas = algoritimoBusca.Busca(base, 200);

        System.out.println("Num Jogadas: " + jogadas.size());
        for (Nodo jogada : jogadas) {
            System.out.println(jogada);
        }

    }
}
