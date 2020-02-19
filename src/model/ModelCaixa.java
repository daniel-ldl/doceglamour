/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Dados.Caixa;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joao
 */
public class ModelCaixa extends AbstractTableModel{
    DecimalFormat format = new DecimalFormat("0.00");
    List<Caixa> caixas = new ArrayList<>();
    List<Float> retiradasTotais = new ArrayList<>();
    String [] colunas = {"VALOR DE ABERTURA", "VALOR DE FECHAMENTO", "DATA DE ABERTURA", "DATA DE FECHAMENTO", "RETIRADAS TOTAIS", "SALDO FINAL"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return caixas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    public void addRow(Caixa caixa, float retirada){
        caixas.add(caixa);
        retiradasTotais.add(retirada);
        this.fireTableRowsInserted(0, 0);
        
    }
    public void removeRow(int index){
        caixas.remove(index);
        retiradasTotais.remove(index);
        this.fireTableRowsDeleted(index, index);
    }
    public void removeAllRow(){
        caixas.clear();
        retiradasTotais.clear();
        this.fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                float retorno = caixas.get(rowIndex).getValorAbertura();
                return format.format(retorno);
            case 1:
                float retorno2 = caixas.get(rowIndex).getValorFechamento();
                return format.format(retorno2);
            case 2:
                return caixas.get(rowIndex).getDataAberturaString();
            case 3:
                return caixas.get(rowIndex).getDataFechamentoString();
            case 4:
                return retiradasTotais.get(rowIndex);
            case 5:
                return ((caixas.get(rowIndex).getValorAbertura() + caixas.get(rowIndex).getValorFechamento()) - retiradasTotais.get(rowIndex));
            default:
                    return null;
        }
            
    }
    
}
