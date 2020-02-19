/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Dados.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joao
 */
public class TabelaProdutosVisualizarPedido extends AbstractTableModel{
   private List<Produto> produto = new ArrayList<>();
    private String [] colunas = {"PRODUTO", "QUANTIDADE", "VALOR UNITARIO"};

    public TabelaProdutosVisualizarPedido() {
    }
    
    

   @Override
    public String getColumnName(int column) {
        return colunas [column];
    }

    public TabelaProdutosVisualizarPedido(List <Produto> produto) {
        this.produto = produto;
    }
    
    public void addRow(Produto p){
        this.produto.add(p);
        fireTableDataChanged();
    }
    
    public void removeRow(int index){
        this.produto.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    

    @Override
    public int getRowCount() {
        return produto.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    } 
    
    public Produto buscarProdutoCompleto(int index){
        return produto.get(index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return produto.get(rowIndex).getNome();
            case 1:
                return produto.get(rowIndex).getQuantidade();
            case 2:
                return produto.get(rowIndex).getValor();
            default:
                return null;
                    
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                this.produto.get(rowIndex).setNome((String ) aValue);
                break;
            case 1:
                this.produto.get(rowIndex).setQuantidade((Integer) aValue);
                break;
            case 2:
                this.produto.get(rowIndex).setValor((Float) aValue);
                break;
            default:
                break;
        }
    
    }
   
}
