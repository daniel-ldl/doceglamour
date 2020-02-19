/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import Conexão.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class Pedido {
    private int codigo_pedido;
    private int codigo_cliente;
    private String tema;
    private float valor_total;
    private String nome_crianca;
    private int idade_crianca;
    private Calendar data_entrega;
    private float valor_sinal;
    private float taxa_entrega;
    private Calendar data_sinal;
    private float desconto;
    private String obs;
    private String status;
    private int codigo_usuario;
    private Calendar data_pedido;

    public Calendar getData_pedido() {
        return data_pedido;
    }
    public String getData_Pedido_String(){
        SimpleDateFormat novo = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = novo.format(this.data_pedido.getTime());
        return convertido;
    }

    public void setData_pedido(Calendar data_pedido) {
        this.data_pedido = data_pedido;
    }
    

    public int getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }
    
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
    
    public void setData_sinal(Calendar data_sinal) {
        this.data_sinal = data_sinal;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Calendar getData_sinal() {
        return data_sinal;
    }

    public float getDesconto() {
        return desconto;
    }

    public String getObs() {
        return obs;
    }

    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public void setNome_crianca(String nome_crianca) {
        this.nome_crianca = nome_crianca;
    }

    public void setIdade_crianca(int idade_crianca) {
        this.idade_crianca = idade_crianca;
    }

    public void setData_entrega(Calendar data_entrega) {
        this.data_entrega = data_entrega;
    }

    public void setValor_sinal(float valor_sinal) {
        this.valor_sinal = valor_sinal;
    }

    public void setTaxa_entrega(float taxa_entrega) {
        this.taxa_entrega = taxa_entrega;
    }

    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public String getTema() {
        return tema;
    }

    public float getValor_total() {
        return valor_total;
    }

    public String getNome_crianca() {
        return nome_crianca;
    }

    public int getIdade_crianca() {
        return idade_crianca;
    }

    public Calendar getData_entrega() {
        return data_entrega;
    }
    public String getData_entrega_string(){
        SimpleDateFormat novo = new SimpleDateFormat("dd/MM/yyyy");
        String convertido = novo.format(data_entrega.getTime());
        return convertido;
    }

    public long getData_entregaLong() {
        return data_entrega.getTimeInMillis();
    }

    public void setData_entrega(long data) {
        data_entrega.setTimeInMillis(data);
    }

    public float getValor_sinal() {
        return valor_sinal;
    }

    public float getTaxa_entrega() {
        return taxa_entrega;
    }

    public Pedido(int codigo_pedido, int codigo_cliente, String tema, float valor_total, String nome_crianca, int idade_crianca, Calendar data_entrega, float valor_sinal, float taxa_entrega, Calendar data_sinal, float desconto, String obs, String status, int codigo_usuario, Calendar data_pedido) {
        this.codigo_pedido = codigo_pedido;
        this.codigo_cliente = codigo_cliente;
        this.tema = tema;
        this.valor_total = valor_total;
        this.nome_crianca = nome_crianca;
        this.idade_crianca = idade_crianca;
        this.data_entrega = data_entrega;
        this.valor_sinal = valor_sinal;
        this.taxa_entrega = taxa_entrega;
        this.data_sinal = data_sinal;
        this.desconto = desconto;
        this.obs = obs;
        this.status = status;
        this.codigo_usuario = codigo_usuario;
        this.data_pedido = data_pedido;
    }

    public Pedido() {

    }

    public void InserirBd() {
        Conexao minhaConexao = new Conexao();
        minhaConexao.getConexao();
        String pedidoInserte = "INSERT INTO dados.pedido(codigo_pedido, codigo_cliente, tema, valor_total, nome_crianca, idadecrianca, data_entrega, valor_sinal, taxa_entrega, desconto, data_sinal, obs, status, codigo_usuario, data_pedido)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            
            PreparedStatement smtInserePedido = minhaConexao.getConexao().prepareStatement(pedidoInserte);
            smtInserePedido.setInt(1, this.codigo_pedido);
            smtInserePedido.setInt(2, this.codigo_cliente);
            smtInserePedido.setString(3, this.tema);
            smtInserePedido.setFloat(4, this.valor_total);
            smtInserePedido.setString(5, this.nome_crianca);
            smtInserePedido.setInt(6, this.idade_crianca);
            smtInserePedido.setDate(7, new java.sql.Date(data_entrega.getTimeInMillis()));
            smtInserePedido.setFloat(8, this.valor_sinal);
            smtInserePedido.setFloat(9, this.taxa_entrega);
            smtInserePedido.setFloat(10, this.desconto);
           
            if(this.data_sinal == null){
                smtInserePedido.setDate(11, null);
            }else{
                 smtInserePedido.setDate(11, new java.sql.Date(data_sinal.getTimeInMillis()));
            }
            smtInserePedido.setString(12, this.obs);
            smtInserePedido.setString(13, this.status);
             smtInserePedido.setInt(14, codigo_usuario);
             smtInserePedido.setDate(15, new java.sql.Date(data_pedido.getTimeInMillis()));
            smtInserePedido.executeUpdate();
            JOptionPane.showMessageDialog(null, "Pedido Cadastrado com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            minhaConexao.fecharConexao();
        }
    }

    void setData_entregaSql(java.sql.Date date) {
        this.data_entrega.setTime(date);
    }
    
}
