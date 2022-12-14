/*******************************************************************************
 *  Compilação:        javac AlgoritmoBFSDigrafo.java
 *  Execução:          java AlgoritmoBFSDigrafo dados.txt vo
 *  Dependências:      Digrafo.java Fila.java Pilha.java
 *  Arquivos de dados: Digrafo1.txt
 *  Link dos dados:    https://drive.google.com/open?id=0B3q56TwNCeXoc2tlbllCRmo1MTQ
 *
 *  Executa pesquisa em largura em um dígrafo.
 *
 *  % java AlgoritmoBFSDigrafo Digrafo1.txt 3
 *  3 para 0 (2):  3->2->0
 *  3 para 1 (3):  3->2->0->1
 *  3 para 2 (1):  3->2
 *  3 para 3 (0):  3
 *  3 para 4 (2):  3->5->4
 *  3 para 5 (1):  3->5
 *  3 para 6 (-):  não conectado
 *  3 para 7 (-):  não conectado
 *  3 para 8 (-):  não conectado
 *  3 para 9 (-):  não conectado
 *  3 para 10 (-):  não conectado
 *  3 para 11 (-):  não conectado
 *  3 para 12 (-):  não conectado
 *
 ******************************************************************************/

package br.edu.ifes.si.tpa;

import java.util.Scanner;

/**
 * Esta classe implementa o algoritmo de busca em largura em um dígrafo.
 * Para documentação adicional, acesse:
 * <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class AlgoritmoMenorCaminho {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marcado;    // marcado[v1] = existe um caminho do vértice origem vo->v1?
    private int[] arestaPara;     // arestaPara[v1] = última aresta no menor caminho vértice origem vo->v1
    private int[] distanciaPara;  // distanciaPara[v1] = tamanho do menor caminho vértice origem vo->v1

    /**
     * Verifica o menor caminho de um vértice origem vo e todos os demais vértices do dígrafo
     * @param G o dígrafo
     * @param vo o vértice origem
     */
    public AlgoritmoMenorCaminho(Digrafo D, int vo) {
        marcado = new boolean[D.V()];
        distanciaPara = new int[D.V()];
        arestaPara = new int[D.V()];
        for (int v = 0; v < D.V(); v++) {
            distanciaPara[v] = INFINITY;
        }
        bfs(D, vo);
    }

    /**
     * Método algoritmoBFS para um vértice origem
     * @param D o dígrafo
     * @param vo o vértice origem
     */
    private void bfs(Digrafo D, int vo) {
        Fila<Integer> f = new Fila<Integer>();
        f.enfileira(vo);
        marcado[vo] = true;
        distanciaPara[vo] = 0;
        while (!f.isEmpty()) {
            int v = f.desenfileira();
            for (Aresta a : D.adj(v)) {
                int x = a.getV2();
                if (!marcado[x]) {
                    arestaPara[x] = v;
                    distanciaPara[x] = distanciaPara[v] + 1;
                    marcado[x] = true;
                    f.enfileira(x);
                }
            }
        }
    }

    /**
     * Existe um caminho direcionado do vértice atual para o vértice v
     * @param v o vértice
     * @return verdadeiro se existir um caminho direcionado, ou falso, caso contrário
     */
    public boolean temCaminhoPara(int v) {
        return marcado[v];
    }

    /**
     * Retorna o número de arestas no menor caminho do vértice origem v
     * @param v o vértice
     * @return o número de arestas no menor caminho
     */
    public int distanciaPara(int v) {
        return distanciaPara[v];
    }

    /**
     * Retorna um caminho do vértice origem para o vértice v ou null se não existir caminho
     * @param v o vértice
     * @return a sequência de vértices no menor caminho, como iterable
     */
    public Iterable<Integer> caminhoPara(int v) {
        if (!temCaminhoPara(v)) {
            return null;
        }
        Pilha<Integer> caminho = new Pilha<Integer>();
        int x;
        for (x = v; distanciaPara[x] != 0; x = arestaPara[x]) {
            caminho.empilha(x);
        }
        caminho.empilha(x);
        return caminho;
    }

    /**
     * Testa a classe AlgoritmoMenorCaminho.
     */
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Digrafo D = new Digrafo( new In("./Digrafo1.txt"));

        int origem, destino;
        System.out.println("Digite o ID do artigo origem: ");
        origem = ler.nextInt();
        System.out.println("Digite o ID do artigo destino: ");
        destino = ler.nextInt();
        AlgoritmoMenorCaminho algoritmoBFS = new AlgoritmoMenorCaminho(D, origem);

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

}