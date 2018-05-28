/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.algoritimos;

import ufpel.ia.base.Nodo;

/**
 *
 * @author WeslenSchiavon
 */
public class HeuristicaArrancarPeca implements Heuristica {

    @Override
    public int Avalia(Nodo base) {
        int contador = 0;
        int val = 1;
        for (int i = 0; i < base.getTamanhoTabuleiro(); i++) {
            for (int j = 0; j < base.getTamanhoTabuleiro(); j++) {
                if (base.getTabuleiro()[i][j] != val) {
                    contador++;
                }
                val++;
            }
        }

        return contador;
    }

}
