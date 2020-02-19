/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Suporte
 */
public class Caixa {
    private float valorAbertura;
    private float valorFechamento;
    private Calendar dataAbertura;
    private Calendar dataFechamento;
    private String Status;
    private int codigo;
    

    public float getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(float valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public float getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(float valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Calendar getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Calendar dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDataAberturaString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = format.format(this.dataAbertura.getTime());
        return convertido;
    }
    public String getDataFechamentoString(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = format.format(this.dataFechamento.getTime());
        return convertido;
    }

    public Caixa() {
    }
    
}
