/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.algoritimos;

import java.util.LinkedList;
import java.util.List;
import ufpel.ia.base.Nodo;
import ufpel.ia.util.TabuleitoUtil;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaLargura implements Busca {

    public List<Nodo> Busca(Nodo base, int profundidade) {
        List<Nodo> arvore = new LinkedList<>();

        arvore.add(base);
        List<Nodo> nivel = TabuleitoUtil.GeraJogadas(base);

        if (profundidade == -1) {
            profundidade = Integer.MAX_VALUE;
        }

        for (int i = 0; i < profundidade; i++) {
            arvore.addAll(nivel);
            for (Nodo jogada : nivel) {
                if (TabuleitoUtil.EhJogadaFinal(jogada)) {
                    return TabuleitoUtil.RetornaCaminho(jogada);
                }
            }

            List<Nodo> tmp = new LinkedList<>();
            for (Nodo jogada : nivel) {
                tmp.addAll(TabuleitoUtil.GeraJogadas(jogada));
            }
            nivel = tmp;
        }
        return null;
    }
}
