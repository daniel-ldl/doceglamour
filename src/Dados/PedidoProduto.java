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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class PedidoProduto {

    private ArrayList<String> produto;
    private ArrayList<String> quantidade;
    private int codigoPedido;

    public ArrayList<String> getProduto() {
        return produto;
    }

    public ArrayList<String> getQuantidade() {
        return quantidade;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setProduto(ArrayList<String> produto) {
        this.produto = produto;
    }

    public void setQuantidade(ArrayList<String> quantidade) {
        this.quantidade = quantidade;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public PedidoProduto(ArrayList<String> produto, ArrayList<String> quantidade, int codigoPedido) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.codigoPedido = codigoPedido;
    }

    public PedidoProduto() {
    }
    

    public void InserirBd() {
        Conexao minhaConexao = new Conexao();
        minhaConexao.getConexao();
        @SuppressWarnings("UnusedAssignment")
        String obterCodProduto = "";
        int codProduto = 0;
        int quantidadeFinal;
        String sqlInserir = "INSERT INTO dados.produto_pedido(codigo_pedido, codigo_produto, quantidade) VALUES (?, ?, ?);";
        @SuppressWarnings("UnusedAssignment")
        String sqlBuscarCodProduto = "";
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        for (int i = 0; i < this.produto.size(); i++) {
            quantidadeFinal = Integer.parseInt(this.quantidade.get(i));
            obterCodProduto = this.produto.get(i);
            sqlBuscarCodProduto = "SELECT codigo_produto FROM dados.produto where nome = '" + obterCodProduto + "'";
            try {
                PreparedStatement smtBuscarCod = minhaConexao.getConexao().prepareStatement(sqlBuscarCodProduto);
                rs = smtBuscarCod.executeQuery();
                rs.next();
                codProduto = rs.getInt("codigo_produto");
            } catch (SQLException ex) {
                Logger.getLogger(PedidoProduto.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                PreparedStatement smtInserirBd = minhaConexao.getConexao().prepareStatement(sqlInserir);
                smtInserirBd.setInt(1, this.codigoPedido);
                smtInserirBd.setInt(2, codProduto);
                smtInserirBd.setInt(3, quantidadeFinal);
                smtInserirBd.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(PedidoProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        minhaConexao.fecharConexao();
        
    }
}
