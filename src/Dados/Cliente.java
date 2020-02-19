/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import Conexão.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author João
 */
public class Cliente {
    private String nome;
    private String telefone;
    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private  int numero;
    private int codigo_cliente;

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }
    
    

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente(String nome, String telefone, String rua, String bairro, String complemento, String cidade, int numero) {
        this.nome = nome;
        this.telefone = telefone;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.numero = numero;
    }

    public Cliente() {
    }

    public Cliente(String nome, String telefone, String rua, String bairro, String complemento, String cidade, int numero, int codigo_cliente) {
        this.nome = nome;
        this.telefone = telefone;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.numero = numero;
        this.codigo_cliente = codigo_cliente;
    }
    
    @SuppressWarnings("null")
    public void InserirBd(){
        Conexao minhaConexao = new Conexao();
        minhaConexao.getConexao();
        String meuInsert = "INSERT INTO dados.cliente(\n" +
"	 nome, telefone, rua, bairro, cidade, numero, complemento)\n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement smtInsereCliente = minhaConexao.getConexao().prepareStatement(meuInsert);
            smtInsereCliente.setString(1, this.getNome());
            smtInsereCliente.setString(2, this.getTelefone());
            smtInsereCliente.setString(3, this.getRua());
            smtInsereCliente.setString(4, this.getBairro());
            smtInsereCliente.setString(5, this.getCidade());
            smtInsereCliente.setInt(6, this.getNumero());
            smtInsereCliente.setString(7, this.getComplemento());
            smtInsereCliente.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente Salvo com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(minhaConexao != null){
                
                minhaConexao.fecharConexao();
            }
        }
    }
}

