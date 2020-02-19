/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Conexão.Conexao;
import Dados.Pedido;
import Dados.PedidoUtilitarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joao
 */
public class TabelaPedidosTelaPrincipalModel extends AbstractTableModel{
    
    private List<Pedido> pedido = new ArrayList<>();
    private List<String> nomeCliente = new ArrayList<>();
    private String [] colunas = {"CODIGO", "CLIENTE", "TEMA", "NOME DA CRIANÇA", "DATA DE ENTREGA", "STATUS"};
    
    public TabelaPedidosTelaPrincipalModel() {
    }

    @Override
    public String getColumnName(int column) {
        return colunas [column];
    }
    
    
    
    public TabelaPedidosTelaPrincipalModel(List<Pedido> pedido){
        this.pedido = pedido;
    }
    @Override
    public int getRowCount() {
        return pedido.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    public  void addRow(Pedido p){
        this.pedido.add(p);
        this.fireTableDataChanged();
          
    }
    public void addCliente(String nomeCliente){
        this.nomeCliente.add(nomeCliente);
    }
    
    public void removeRow(int index){
        this.pedido.remove(index);
        this.fireTableRowsDeleted(index, index);
    }
    public void removeAllRow(){
        this.pedido.clear();
        this.nomeCliente.clear();
        this.fireTableDataChanged();
    }
    public void atualizarTable(){
        this.fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                 pedido.get(rowIndex).setCodigo_pedido(Integer.parseInt((String) aValue));
                 break;
            case 1:
                 pedido.get(rowIndex).setCodigo_cliente(Integer.parseInt((String) aValue));
                 break;
            case 2:
                 pedido.get(rowIndex).setTema((String) aValue);
                 break;
            case 3:
                 pedido.get(rowIndex).setNome_crianca((String) aValue);
                 break;
            case 4:
                 pedido.get(rowIndex).setData_entrega((Calendar) aValue);
                 break;
            case 5:
                 pedido.get(rowIndex).setStatus((String) aValue);
                 break;
        }
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 0:
                return pedido.get(rowIndex).getCodigo_pedido();
            case 1:
                return nomeCliente.get(rowIndex);
            case 2:
                return pedido.get(rowIndex).getTema();
            case 3:
                return pedido.get(rowIndex).getNome_crianca();
            case 4:
                return pedido.get(rowIndex).getData_entrega_string();
            case 5:
                return pedido.get(rowIndex).getStatus();
           
            default:
                    return null;
        }
    }
    
    public Pedido getPedidoCompleto(int rowIndex){
        
        return pedido.get(rowIndex);
    }
    
    public void preencherJTable(){
        Conexao minhaConexao = new Conexao();
        minhaConexao.getConexao();
        ResultSet rs = null;
        List<Pedido> teste = new ArrayList<>();
        List<Integer> codigosClientes = new ArrayList<>();
        String nome;
        PedidoUtilitarios novo = new PedidoUtilitarios();
        teste = novo.preencherJTable();
        for(Pedido p : teste){
            codigosClientes.add(p.getCodigo_cliente());
        this.pedido.add(p);
        }
        for(Integer p : codigosClientes){
            try {
                String sql = "SELECT cliente.nome FROM dados.cliente where codigo_cliente = ?";
                PreparedStatement stmBuscarNome = minhaConexao.getConexao().prepareStatement(sql);
                stmBuscarNome.setInt(1, p);
                rs = stmBuscarNome.executeQuery();
                rs.next();
                nomeCliente.add(rs.getString("nome"));
            } catch (SQLException ex) {
                Logger.getLogger(TabelaPedidosTelaPrincipalModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
}
