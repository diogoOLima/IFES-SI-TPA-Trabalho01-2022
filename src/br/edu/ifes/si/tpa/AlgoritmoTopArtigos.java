/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.si.tpa;

/**
 *
 * @author diogo
 */
public class AlgoritmoTopArtigos {
    private int[] qtdCitacoesArtigo;
    public AlgoritmoTopArtigos(Digrafo D){
        qtdCitacoesArtigo = new int[D.V()];
        
    }
    public int[] citacoesArtigos(Digrafo D){
        for(int x = 0 ; x < D.V() ; x++){
            for(Aresta a : D.arestas()){
                if(a.getV2() == x){
                    qtdCitacoesArtigo[x]++;
                }
            }
        }
        return qtdCitacoesArtigo;
    }
}
