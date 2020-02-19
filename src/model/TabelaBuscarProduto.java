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
public class TabelaBuscarProduto extends AbstractTableModel{
    List<Produto> produto = new ArrayList<>();
    String [] colunas = {"NOME", "DESCRIÇÃO", "VALOR", "SABOR"};
    
    public TabelaBuscarProduto() {
    }
    
    

    @Override
    public String getColumnName(int column) {
        return colunas [column];
    }

    public TabelaBuscarProduto(List <Produto> produto) {
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
    
    public void removeAllRow(){
        this.produto.clear();
    }
    

    @Override
    public int getRowCount() {
        return produto.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return produto.get(rowIndex).getNome();
            case 1:
                return produto.get(rowIndex).getDescricao();
            case 2:
                return produto.get(rowIndex).getValor();
            case 3:
                return produto.get(rowIndex).getSabor();
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
   