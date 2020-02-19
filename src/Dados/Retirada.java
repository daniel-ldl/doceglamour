/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

/**
 *
 * @author Suporte
 */
public class Retirada {
    private int codigoCaixa;
    private float valorRetirada;
    private String descricao;

    public int getCodigoCaixa() {
        return codigoCaixa;
    }

    public void setCodigoCaixa(int codigoCaixa) {
        this.codigoCaixa = codigoCaixa;
    }

    public float getValorRetirada() {
        return valorRetirada;
    }

    public void setValorRetirada(float valorRetirada) {
        this.valorRetirada = valorRetirada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Retirada() {
    }
    
}
