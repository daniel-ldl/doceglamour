/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Dados.Retirada;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Suporte
 */
public class ModelRetiradas extends AbstractTableModel{
    List<Retirada> retiradas = new ArrayList<>();
    String [] colunas = {"Descrição", "Valor"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return retiradas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    public void removeRowAt(int index){
        retiradas.remove(index);
        this.fireTableRowsDeleted(index, index);
    }
    public void addRow(Retirada retirada){
        retiradas.add(retirada);
        this.fireTableDataChanged();
    }
    public void removeAllRow(){
        retiradas.clear();
        this.fireTableDataChanged();
    }
    public List<Retirada> getAllRows(){
        return this.retiradas;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return retiradas.get(rowIndex).getDescricao();
            case 1:
                return retiradas.get(rowIndex).getValorRetirada();
            default:
                return null;
        }
    }
    
    
    
}
