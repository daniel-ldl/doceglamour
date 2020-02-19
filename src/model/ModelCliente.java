/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.table.AbstractTableModel;
import Dados.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paladino
 */
public class ModelCliente extends AbstractTableModel {
    
    List<Cliente> cliente = new ArrayList<>();
    String[] colunas = {"NOME", "TELEFONE", "RUA", "BAIRRO", "NUMERO", "COMPLEMENTO", "CIDADE"};
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    public void removeAllRows() {
        this.cliente.clear();
        fireTableDataChanged();
    }
    
    public void addRows(Cliente c) {
        this.cliente.add(c);
        fireTableDataChanged();
    }
    
    public void removeRows(int index) {
        this.cliente.remove(index);
        fireTableRowsDeleted(index, index);      
    }
    
    @Override
    public int getRowCount() {
        return this.cliente.size();
    }
    
    @Override
    public int getColumnCount() {
        return this.colunas.length;
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
                
            case 4:
                return cliente.get(rowIndex).getNumero();
                
            case 5:
                return cliente.get(rowIndex).getComplemento();
                
            case 6:
                return cliente.get(rowIndex).getCidade();
                
            default:
                return null;
        }
        
    }
    
}
