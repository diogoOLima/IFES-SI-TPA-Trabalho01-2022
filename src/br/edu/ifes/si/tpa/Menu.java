/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.si.tpa;

import java.util.Scanner;

/**
 *
 * @author diogo
 */
public class Menu {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Digrafo G = new Digrafo( new In("./Digrafo1.txt"));

        System.out.println("Digite o ID do artigo origem: ");
        int origem = ler.nextInt();
        System.out.println("Digite o ID do artigo destino: ");
        int destino = ler.nextInt();
        AlgoritmoBFSDigrafo algoritmoBFS = new AlgoritmoBFSDigrafo(G, origem);

        if (algoritmoBFS.temCaminhoPara(destino)) {
            System.out.printf("%d para %d (%d):  ", origem, destino, algoritmoBFS.distanciaPara(destino));
            for (int x : algoritmoBFS.caminhoPara(destino)) {
                if (x == origem) {
                    System.out.print(x);
                } else {
                    System.out.print("->" + x);
                }
            }
            System.out.println();
        } else {
            System.out.printf("%d para %d (-):  não conectado\n", origem, destino);
        }
        
        System.out.println("Digite o ID do artigo origem: ");
        origem = ler.nextInt();
        System.out.println("Digite o ID do artigo destino: ");
        destino = ler.nextInt();
        AlgoritmoTodosCaminhos todosCaminhos1 = new AlgoritmoTodosCaminhos(G, origem, destino);
        System.out.println("# caminhos = " + todosCaminhos1.numeroDeCaminhos());
    }
}
