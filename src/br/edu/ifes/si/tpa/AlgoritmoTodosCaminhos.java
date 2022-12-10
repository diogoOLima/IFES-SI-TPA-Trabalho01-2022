package br.edu.ifes.si.tpa;

import java.util.Scanner;

public class AlgoritmoTodosCaminhos {
    private boolean[] noCaminho;        // vértices no caminho atual
    private Pilha<Integer> caminho;     // o caminho atual
    private int numeroDeCaminhos;       // número de caminhos simples

    // mostra todos os caminhos simples de vo (vértice origem) para vd (vértice destino) - usando DFS
    public AlgoritmoTodosCaminhos(Digrafo D, int vo, int vd) {
        noCaminho = new boolean[D.V()];
        caminho   = new Pilha<Integer>();
        dfs(D, vo, vd);
    }

    // usando a ideia de exploração do método DFS
    private void dfs(Digrafo D, int v, int vd) {

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
            for (Aresta a : D.adj(v)) {
                int x = a.getV2();
                if (!noCaminho[x])
                    dfs(D, x, vd);
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
        Scanner ler = new Scanner(System.in);
        Digrafo D = new Digrafo(new In("./Digrafo1.txt"));
        int origem, destino;
        System.out.println("Digite o ID do artigo origem: ");
        origem = ler.nextInt();
        System.out.println("Digite o ID do artigo destino: ");
        destino = ler.nextInt();
        AlgoritmoTodosCaminhos todosCaminhos1 = new AlgoritmoTodosCaminhos(D, origem, destino);
        System.out.println("# caminhos = " + todosCaminhos1.numeroDeCaminhos());
    }


}

