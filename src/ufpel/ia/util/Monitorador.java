/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufpel.ia.util;

import java.util.Collection;
import ufpel.ia.base.Nodo;

/**
 *
 * @author WeslenSchiavon
 */
public class Monitorador {

    private static long startTime;
    private static MemoryMonitor memoryMonitor;
    private static long maxSizeList;
    private static Collection<Nodo> listNodos;

    public static void startTimeMonitoring() {
        Monitorador.startTime = System.nanoTime();
    }

    public static double getTimeExecutation() {
        long endTime = System.nanoTime();

        return (endTime - Monitorador.startTime) / 1000000000.0;
    }

    public static long getAtualMemoryUsage() {
        return Runtime.getRuntime().totalMemory();
    }

    public static void startMemoryMonitor() {

        Monitorador.memoryMonitor = new MemoryMonitor(1000);

        new Thread(Monitorador.memoryMonitor).start();
    }

    public static double getMaxMemoryUsage() {
        Monitorador.memoryMonitor.setStop(true);
        return Monitorador.memoryMonitor.getMaiorUsoDeMemoria() / 1000000.0;

    }

    public static void startMaxSizeListMonitor(Collection<Nodo> nodos) {
        Monitorador.listNodos = nodos;
        Monitorador.maxSizeList = nodos.size();
    }

    public static void updateSizeList() {
        if (Monitorador.maxSizeList < Monitorador.listNodos.size()) {
            Monitorador.maxSizeList = Monitorador.listNodos.size();
        }
    }

    public static long getMaxSizeListMonitor() {

        return Monitorador.maxSizeList;
    }

}
