/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.algoritimos;

import java.util.List;
import ufpel.ia.base.Nodo;
import ufpel.ia.util.TabuleitoUtil;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaProfundidadeRecursiva implements Busca {

    public List<Nodo> Busca(Nodo base, int profundidade) {
        if (TabuleitoUtil.EhJogadaFinal(base)) {
            return TabuleitoUtil.RetornaCaminho(base);
        }
        base.visitado = true;

        if ((profundidade == -1) || (TabuleitoUtil.Profundidade(base) < profundidade)) {
            List<Nodo> filhos = TabuleitoUtil.GeraJogadas(base);
            for (Nodo filho : filhos) {
                if (filho != null && !filho.visitado) {
                    List<Nodo> resultado = this.Busca(filho, profundidade);
                    if (resultado != null) {
                        return resultado;
                    }
                }
            }
        }

        return null;
    }
}
