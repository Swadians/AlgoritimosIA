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
public interface Busca {

    /**
     * Profundidade com valor -1 é iguinorada
     *
     * @param base
     * @param profundidade
     * @return
     */
    List<Nodo> Busca(Nodo base, int profundidade);

}
