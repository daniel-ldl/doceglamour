/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Dados.Pedido;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author joao
 */
public class TabelaPedidoRelatorio extends AbstractTableModel{

   private List<Pedido> pedido = new ArrayList<>();
    private List<String> nomeCliente = new ArrayList<>();
    private List<String> vendedor = new ArrayList<>();
    private String [] colunas = {"CODIGO", "CLIENTE", "DATA PEDIDO", "DATA ENTREGA", "SINAL", "DESCONTO", "VALOR FINAL", "STATUS", "VENDEDOR"};
    
    public TabelaPedidoRelatorio() {
    }

    @Override
    public String getColumnName(int column) {
        return colunas [column];
    }
    
    
    
    public TabelaPedidoRelatorio(List<Pedido> pedido){
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
    
    public  void addRow(Pedido p, String nomeCliente, String nomeVendedor){
        this.pedido.add(p);
        this.nomeCliente.add(nomeCliente);
        this.vendedor.add(nomeVendedor);
        this.fireTableDataChanged();
          
    }
    public void addCliente(String nomeCliente){
        this.nomeCliente.add(nomeCliente);
    }
    
    public void removeRow(int index){
        this.pedido.remove(index);
        this.nomeCliente.remove(index);
        this.vendedor.remove(index);
        this.fireTableRowsDeleted(index, index);
    }
    public void removeAllRow(){
        this.pedido.clear();
        this.nomeCliente.clear();
        this.vendedor.clear();
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
                return pedido.get(rowIndex).getData_Pedido_String();
            case 3:
                return pedido.get(rowIndex).getData_entrega_string();
            case 4:
                return pedido.get(rowIndex).getValor_sinal();
            case 5:
                return pedido.get(rowIndex).getDesconto();
            case 6:
                return pedido.get(rowIndex).getValor_total();
            case 7:
                return pedido.get(rowIndex).getStatus();
            case 8:
                return vendedor.get(rowIndex);
           
            default:
                    return null;
        }
    }
    
    public Pedido getPedidoCompleto(int rowIndex){
        
        return pedido.get(rowIndex);
    }
}
