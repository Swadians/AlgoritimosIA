/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.algoritimos;

import java.util.List;
import java.util.Stack;
import ufpel.ia.base.Nodo;
import ufpel.ia.util.TabuleitoUtil;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaProfundidadeIterativa implements Busca {

    @Override
    public List<Nodo> Busca(Nodo base, int profundidade) {
        Stack<Nodo> pilhaDeNodos = new Stack<>();

        pilhaDeNodos.add(base);

        while (!pilhaDeNodos.empty()) {
            Nodo aProcurar = pilhaDeNodos.pop();

            if (TabuleitoUtil.EhJogadaFinal(aProcurar)) {
                return TabuleitoUtil.RetornaCaminho(aProcurar);
            }

            aProcurar.visitado = true;
            if ((profundidade == -1) || (TabuleitoUtil.Profundidade(aProcurar) < profundidade)) {
                List<Nodo> jogadas = TabuleitoUtil.GeraJogadas(aProcurar);
                jogadas.forEach(jogada -> {
                    if (jogada != null && !jogada.visitado) {
                        pilhaDeNodos.add(jogada);
                    }
                });
            }
        }
        return null;
    }

}
