/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Dados.Produto;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paladino
 */
public class ModelProdutos extends AbstractTableModel {

    List<Produto> produto = new ArrayList<>();
    String[] colunas = {"NOME", "DESCRIÇÃO", "VALOR", "CATEGORIA", "SABOR"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void removeAllRows() {
        this.produto.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.produto.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    public void addRow(Produto p) {
        this.produto.add(p);
        fireTableDataChanged();
    }

    public Produto recuperaProduto(int linha) {
        return produto.get(linha);
    }

    public void removeRow(int index) {
        this.produto.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return produto.get(rowIndex).getNome();

            case 1:
                return produto.get(rowIndex).getDescricao();

            case 2:
                return produto.get(rowIndex).getValor();

            case 3:
                return produto.get(rowIndex).getCategoria();

            case 4:
                return produto.get(rowIndex).getSabor();

            default:
                return null;

        }
    }

}
