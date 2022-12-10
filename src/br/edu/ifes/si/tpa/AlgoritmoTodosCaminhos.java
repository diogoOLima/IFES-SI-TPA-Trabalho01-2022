package br.edu.ifes.si.tpa;

import java.util.Scanner;

public class AlgoritmoTodosCaminhos {
    private boolean[] noCaminho;        // vértices no caminho atual
    private Pilha<Integer> caminho;     // o caminho atual
    private int numeroDeCaminhos;       // número de caminhos simples

    // mostra todos os caminhos simples de vo (vértice origem) para vd (vértice destino) - usando DFS
    public AlgoritmoTodosCaminhos(Digrafo G, int vo, int vd) {
        noCaminho = new boolean[G.V()];
        caminho   = new Pilha<Integer>();
        dfs(G, vo, vd);
    }

    // usando a ideia de exploração do método DFS
    private void dfs(Digrafo G, int v, int vd) {

        // adiciona v ao caminho atual
        caminho.empilha(v);
        noCaminho[v] = true;

        // encontrado caminho de v para vd (vértice destino)
        if (v == vd) {
            imprimeCaminhoAtual();
            numeroDeCaminhos++;
        }

        // considerar todos os vizinhos que continuariam o caminho
        else {
            for (Aresta a : G.adj(v)) {
                int x = a.getV2();
                if (!noCaminho[x])
                    dfs(G, x, vd);
            }
        }

        // feita a exploração de v, então o remove do caminho
        caminho.desempilha();
        noCaminho[v] = false;
    }

    // esta implementação simplesmente imprime o caminho
    private void imprimeCaminhoAtual() {
        Pilha<Integer> pilhaInvertida = new Pilha<Integer>();
        for (int v : caminho)
            pilhaInvertida.empilha(v);
        if (pilhaInvertida.tamanho() >= 1)
            System.out.print(pilhaInvertida.desempilha());
        while (!pilhaInvertida.isEmpty())
            System.out.print("-" + pilhaInvertida.desempilha());
        System.out.println();
    }

    // retorna o número de caminhos simples entre vo (vértice origem) e vd (vértice destino)
    public int numeroDeCaminhos() {
        return numeroDeCaminhos;
    }


    // Testa a classe AlgoritmoTodosCaminhos
    public static void main(String[] args) {
//        Grafo G = new Grafo(7);
        Scanner ler = new Scanner(System.in);
        Digrafo G = new Digrafo(new In("./Digrafo1.txt"));
//        G.addAresta(new Aresta(0, 1));
//        G.addAresta(new Aresta(0, 2));
//        G.addAresta(new Aresta(2, 3));
//        G.addAresta(new Aresta(3, 4));
//        G.addAresta(new Aresta(2, 5));
//        G.addAresta(new Aresta(1, 5));
//        G.addAresta(new Aresta(5, 4));
//        G.addAresta(new Aresta(3, 6));
//        G.addAresta(new Aresta(4, 6));
//        System.out.println(G);
        int origem, destino;
        System.out.println("Digite o ID do artigo origem: ");
        origem = ler.nextInt();
        System.out.println("Digite o ID do artigo destino: ");
        destino = ler.nextInt();
        AlgoritmoTodosCaminhos todosCaminhos1 = new AlgoritmoTodosCaminhos(G, origem, destino);
        System.out.println("# caminhos = " + todosCaminhos1.numeroDeCaminhos());
    }


}

