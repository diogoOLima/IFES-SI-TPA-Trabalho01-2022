/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.si.tpa;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author diogo
 */
public class AlgoritmoTopAutores {
    private Map<Integer, Integer> artigoAutor;

    private int[] qtdArtigoAutor;
    
    public AlgoritmoTopAutores(Digrafo D){
        qtdArtigoAutor = new int[D.getArtigoAutor().size()];
        artigoAutor = D.getArtigoAutor();
    }
    
    public int[] qtdArtigosAutores(Digrafo D){
        for(int x = 0 ; x < D.V() ; x++){
            for(Aresta a : D.arestas()){
                if(a.getV2() == x){
                    qtdArtigoAutor[x]++;
                }
            }
        }
        return qtdArtigoAutor;
    }
}
