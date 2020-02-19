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
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class Produto {
    private int codigo_produto;
    private String nome;
    private String descricao;
    private float valor;
    private String sabor;
    private String categoria;
    private int quantidade;
    private float valorFinal;

    public float getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(float valorFinal) {
        this.valorFinal = valorFinal;
    }
    
    

    public int getQuantidade() {
        return quantidade;
    }
    
    

    public int getCodigo_produto() {
        return codigo_produto;
    }

    
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValor() {
        return valor;
    }

    public String getSabor() {
        return sabor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    

    public Produto(int codigo_produto, String nome, String descricao, float valor, String categoria, String sabor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.sabor = sabor;
        this.categoria = categoria;
        this.codigo_produto = codigo_produto;
    }
    
    public Produto(){
        
    }
    
    @SuppressWarnings("null")
    public void InserirBd(){
        Conexao minhaConexao = new Conexao();
        minhaConexao.getConexao();
        String meuInserte = "INSERT INTO dados.produto(nome, descricao, valor, categoria, sabor) VALUES (?, ?, ?, ?, ?);";
        String buscarSabor = "select id_sabor from dados.sabor where nome_sabor = '"+this.sabor+"'";
        ResultSet rs = null;
        
        try{
            if(this.categoria.equals("Alimentíceo")){
                PreparedStatement smtInsereProduto = minhaConexao.getConexao().prepareStatement(meuInserte);
                PreparedStatement smtBuscarcod = minhaConexao.getConexao().prepareStatement(buscarSabor);
                rs = smtBuscarcod.executeQuery();
                rs.next();
                int cod = rs.getInt("id_sabor");
                smtInsereProduto.setString(1, this.getNome());
                smtInsereProduto.setString(2, this.getDescricao());
                smtInsereProduto.setFloat(3, this.getValor());
                smtInsereProduto.setString(4, this.getCategoria());
                smtInsereProduto.setInt(5, cod);
                smtInsereProduto.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto Salvo com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            }
            else{
                PreparedStatement smtInsereProduto = minhaConexao.getConexao().prepareStatement(meuInserte);
                int cod = 11;
                smtInsereProduto.setString(1, this.getNome());
                smtInsereProduto.setString(2, this.getDescricao());
                smtInsereProduto.setFloat(3, this.getValor());
                smtInsereProduto.setString(4, this.getCategoria());
                smtInsereProduto.setInt(5, cod);
                smtInsereProduto.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto Salvo com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            if(minhaConexao != null){
                minhaConexao.fecharConexao();
            }
        }
   }
}
