/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.algoritimos;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import ufpel.ia.base.Nodo;
import ufpel.ia.util.Monitorador;
import ufpel.ia.util.TabuleitoUtil;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaBestFirst implements Busca {

    private final Heuristica qualquer;

    public BuscaBestFirst(Heuristica qualquer) {
        this.qualquer = qualquer;
    }

    @Override
    public List<Nodo> Busca(Nodo base, int profundidade) {
        Queue<Nodo> FilaDePrioridade = new PriorityQueue<>();
        Monitorador.startMaxSizeListMonitor(FilaDePrioridade);

        TabuleitoUtil.CalculaValorHeuristica(qualquer, base);
        FilaDePrioridade.add(base);

        while (!FilaDePrioridade.isEmpty()) {
            Monitorador.updateSizeList();
            Nodo aProcurar = FilaDePrioridade.poll();

            if (TabuleitoUtil.EhJogadaFinal(aProcurar)) {
                return TabuleitoUtil.RetornaCaminho(aProcurar);
            }

            if (Monitorador.getTimeExecutation() > 120) {
                return null;
            }

            aProcurar.visitado = true;

            int profundidadeAtual = TabuleitoUtil.Profundidade(aProcurar);

            if ((profundidade == -1) || (profundidadeAtual < profundidade)) {
                List<Nodo> jogadas = TabuleitoUtil.GeraJogadas(aProcurar);
                jogadas.forEach(jogada -> {
                    if (jogada != null && !jogada.visitado) {
                        int retorno = TabuleitoUtil.CalculaValorHeuristica(qualquer, jogada);

                        jogada.setValorHeuristica(retorno);
                        FilaDePrioridade.add(jogada);
                    }
                });
            }
        }
        return null;
    }

}
