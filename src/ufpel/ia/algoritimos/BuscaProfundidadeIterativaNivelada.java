/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.algoritimos;

import java.util.List;
import ufpel.ia.base.Nodo;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaProfundidadeIterativaNivelada implements Busca {

    @Override
    public List<Nodo> Busca(Nodo base, int profundidade) {
        Busca buscaProfIterativa = new BuscaProfundidadeIterativa();

        for (int i = 1; i <= profundidade; i++) {
            List<Nodo> resultado = buscaProfIterativa.Busca(base, i);

            if (resultado != null) {
                return resultado;
            }
        }
        return null;
    }

}
