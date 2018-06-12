/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.principal;

import java.util.ArrayList;
import java.util.List;
import ufpel.ia.algoritimos.Busca;
import ufpel.ia.algoritimos.BuscaAEstrela;
import ufpel.ia.algoritimos.HeuristicaContaPosDist;
import ufpel.ia.base.Nodo;
import ufpel.ia.util.Monitorador;
import ufpel.ia.util.TabuleitoUtil;

/**
 *
 * @author WeslenSchiavon
 */
public class Main {

    public static void main(String[] args) {
        List<Busca> buscas = new ArrayList<>();

        buscas.add(new BuscaAEstrela(new HeuristicaContaPosDist()));

        for (int x = 0; x < buscas.size(); x++) {
            System.out.println("Algoritimo " + x);
            for (int t = 3; t < 10; t++) {
                System.out.println("");
                System.out.println("");
                System.out.println("Tamanho do tabuleiro:" + t);
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println("");
                        System.out.println("Test " + i);

                        Busca algoritimoBusca = buscas.get(x);
                        Nodo base = new Nodo(t);
                        int embaralhamentoMaximo = 20;

                        TabuleitoUtil.GeraTabuleiroInicial(base, embaralhamentoMaximo);

                        Monitorador.startTimeMonitoring();

                        List<Nodo> jogadas = algoritimoBusca.Busca(base, -1);

                        if (jogadas == null) {
                            System.out.println("Pegou ramo infinito!");
                        } else {
                            System.out.println("Maior numero de nodos: " + Monitorador.getMaxSizeListMonitor());
                            System.out.println("Tempo usao: " + Monitorador.getTimeExecutation() + "s");
                            System.out.println("Num Jogadas: " + jogadas.size());
                        }

                        System.out.println("");
                        System.out.println("Num Jogadas: " + jogadas.size());
                        for (Nodo jogada : jogadas) {
                            System.out.println(jogada);
                        }
                    } catch (OutOfMemoryError e) {
                        System.out.println("Faltou memoria!");
                        System.out.println("Maior numero de nodos: " + Monitorador.getMaxSizeListMonitor());
                        System.out.println("Tempo usado: " + Monitorador.getTimeExecutation() + "s");
                    }
                }
            }
        }

    }
}
