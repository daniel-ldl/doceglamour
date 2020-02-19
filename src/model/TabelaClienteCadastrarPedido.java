/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Dados.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joao
 */
public class TabelaClienteCadastrarPedido extends AbstractTableModel{

    private List<Cliente> cliente = new ArrayList<>();
    private String [] colunas = {"NOME", "TELEFONE", "RUA", "BAIRRO"};

    public TabelaClienteCadastrarPedido() {
    }
    
    public void addRow(Cliente p){
        this.cliente.add(p);
        fireTableDataChanged();
    }
    
    public void removeRow(int index){
        this.cliente.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
     public void removeAllRow(){
        cliente.clear();
    }
    
     public Cliente getRowAt(int index){
        return cliente.get(index);
     }
    

    @Override
    public String getColumnName(int column) {
        return colunas [column];
    }
    
    @Override
    public int getRowCount() {
        return cliente.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch(columnIndex){
            case 0:
                return cliente.get(rowIndex).getNome();
            case 1:
                return cliente.get(rowIndex).getTelefone();
            case 2:
                return cliente.get(rowIndex).getRua();
            case 3:
                return cliente.get(rowIndex).getBairro();
            default:
                return null;
                    
        }
    }
 
}
