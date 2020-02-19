/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author joao
 */
public class TableCaixasRender extends DefaultTableCellRenderer{
    Color defaultBackground, defaultForeground;
    
    public TableCaixasRender(){
        this.defaultBackground = getBackground();
        this.defaultForeground = getForeground();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        float saldo_final = (float) value;
        if(saldo_final < 0){
            c.setBackground(Color.red);
            c.setForeground(Color.black);
        }else{
            c.setBackground(Color.cyan);
            c.setForeground(Color.black);
        }
        DecimalFormat format = new DecimalFormat("0.00");
        setText(format.format(saldo_final));
        return c;
    }
    
}
