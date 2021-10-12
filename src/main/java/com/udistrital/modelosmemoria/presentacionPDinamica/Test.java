/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udistrital.modelosmemoria.presentacionPDinamica;

/**
 *
 * @author Admin
 */
public class Test {

    private static int memory[] = new int[6];

    public static void main(String[] args) {
        mejorAjuste(5);

        for (int i = 0; i < memory.length; i++) {
            System.out.println(memory[i]);
        }
        primerAjuste(5);
        for (int i = 0; i < memory.length; i++) {
            System.out.println(memory[i]);
        }
        peorAjuste(2);
        for (int i = 0; i < memory.length; i++) {
            System.out.println(memory[i]);
        }
    }

    public static void mejorAjuste(int proceso) {
        memory[0] = 4;
        memory[1] = 3;
        memory[2] = 6;
        memory[3] = 5;
        memory[4] = 7;
        memory[5] = 8;
        int memoryOrdenada[] = memory.clone();
        memoryOrdenada = ordenamientoBurbuja(memoryOrdenada);
        for (int i = 0; i < memoryOrdenada.length; i++) {
            if (memoryOrdenada[i] >= proceso && memoryOrdenada[i] != -1) {
                System.out.println("Memoria asignada " + memoryOrdenada[i]);
                for (int j = 0; j < memory.length; j++) {
                    if (memoryOrdenada[i] == memory[j]) {
                        memory[j] = -1;
                        break;
                    }
                }
                break;
            }
        }

    }

    public static int[] ordenamientoBurbuja(int[] temp) {
        int aux;
        for (int i = 1; i < temp.length; i++) {
            for (int j = 0; j < temp.length - 1; j++) {
                if (temp[j] > temp[j + 1]) {
                    aux = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = aux;
                }
            }
        }
        return temp;
    }

    public static void primerAjuste(int proceso) {
        memory[0] = 4;
        memory[1] = 3;
        memory[2] = 6;
        memory[3] = 5;
        memory[4] = 7;
        memory[5] = 8;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] >= proceso && memory[i] != -1) {
                System.out.println("Memoria asignada " + memory[i]);
                memory[i] = -1;
                break;
            }
        }
    }

    public static void peorAjuste(int proceso) {
        memory[0] = 4;
        memory[1] = 3;
        memory[2] = 9;
        memory[3] = 5;
        memory[4] = 7;
        memory[5] = 4;
        int memoryOrdenada[] = memory.clone();
        memoryOrdenada = ordenamientoBurbuja(memoryOrdenada);
        for (int i = memoryOrdenada.length - 1; i >= 0; i--) {
            if (memoryOrdenada[i] >= proceso && memoryOrdenada[i] != -1) {
                System.out.println("Memoria asignada " + memoryOrdenada[i]);
                for (int j = 0; j < memory.length; j++) {
                    if (memoryOrdenada[i] == memory[j]) {
                        memory[j] = -1;
                        break;
                    }
                }
                break;
            }
        }
  
    }
}
