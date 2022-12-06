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
        boolean continuar = false;
        int opcaoMenu;
        do{
            System.out.println("Bem-vindo ao algoritimo de Digrafo. Informe o número da opção que deseja realizar:");
            System.out.println("1) Encontrar o menor caminho entre dois vértices");
            System.out.println("2) Listar todos os caminhos entre dois vértices");
            System.out.println("3) Número de citações de cada artigo");
            opcaoMenu = ler.nextInt();
            switch (opcaoMenu){
                case 1:
                    menorCaminho(G, ler);
                    break;
                case 2:
                    todosCaminhos(G, ler);
                    break;
                case 3:
                    citacoesArtigos(G);
                    break;
                case 4:
                    autorArtigo(G);
                    break;
                    
            }
            System.out.println("Deseja continuar?(1-Sim | 0-Não)");
            if(ler.nextInt() == 1){
                continuar = true;
            }else{
                continuar = false;
            }
        }while(continuar == true);
    }

    private static void menorCaminho(Digrafo G, Scanner ler) {
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
    }

    private static void todosCaminhos(Digrafo G, Scanner ler) {
        System.out.println("Digite o ID do artigo origem: ");
        int origem = ler.nextInt();
        System.out.println("Digite o ID do artigo destino: ");
        int destino = ler.nextInt();
        AlgoritmoTodosCaminhos todosCaminhos1 = new AlgoritmoTodosCaminhos(G, origem, destino);
        System.out.println("# caminhos = " + todosCaminhos1.numeroDeCaminhos());
    }

    private static void citacoesArtigos(Digrafo G) {
        AlgoritmoTopArtigos topArtigos = new AlgoritmoTopArtigos(G);
        int[] qtdCitacoesArtigos =  topArtigos.citacoesArtigos(G);
        for(int x = 0; x < G.V(); x++){
            System.out.printf("%d: %d \n", x, qtdCitacoesArtigos[x]);
        }
    }

    private static void autorArtigo(Digrafo G) {
        AlgoritmoTopAutores topAutores = new AlgoritmoTopAutores(G);
        
        int[]qtdArtigoAutor =  topAutores.qtdArtigosAutores(G);
        for(int x = 0; x < qtdArtigoAutor.length; x++){
            if(qtdArtigoAutor[x] > 0){
                System.out.printf("%d: %d \n", x, qtdArtigoAutor[x]);
            }
        }
    }
    
    
}
