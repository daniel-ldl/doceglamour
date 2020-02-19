/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Conexão.Conexao;
import Dados.Caixa;
import Dados.Retirada;
import static Interfaces.Principal.jDesktopPane1;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ModelCaixa;
import model.ModelRetiradas;
import model.TableCaixasRender;
import util.TeclasPermitidas;

/**
 *
 * @author Suporte
 */
public class CaixaInterface extends javax.swing.JInternalFrame {

    /**
     * Creates new form Caixa
     */
    static boolean possuiCaixaAberto;
    static ModelRetiradas modelTable = new ModelRetiradas();
    static ModelCaixa modelTableCaixa = new ModelCaixa();
    TeclasPermitidas novo = new TeclasPermitidas();
    static float totalRetiradas = 0;
    static float valorFechamento;
    static Caixa caixaAtual = new Caixa();

    public CaixaInterface() {
        initComponents();
        this.jTable1.setModel(modelTable);
        this.jTable2.setModel(modelTableCaixa);
        this.jTable2.getColumnModel().getColumn(5).setCellRenderer(new TableCaixasRender());
        jtDataAbertura.setEditable(false);
        jtRetirada.setEditable(false);
        jtValorAbertura.setEditable(false);
        jtValorFechamento.setDocument(novo);
        caixasanteriores();
        buscarCaixaAtual();
        
    }

    public static void buscarCaixaAtual() {
        modelTable.removeAllRow();
        totalRetiradas = 0;
        Conexao minhaConexao = new Conexao();
        String sqlBuscarCaixa = "Select * from dados.caixa where status = 'Aberto'";
        String sqlBuscarRetiradas = "Select * from dados.retiradas where codigo_caixa = ?";
        ResultSet rsBuscarCaixa = null;
        ResultSet rsBuscarRetiradas = null;
        try {
            PreparedStatement stmBuscarCaixa = minhaConexao.getConexao().prepareStatement(sqlBuscarCaixa);
            rsBuscarCaixa = stmBuscarCaixa.executeQuery();
            if (rsBuscarCaixa.next()) {
                PreparedStatement stmBuscarRetiradas = minhaConexao.getConexao().prepareStatement(sqlBuscarRetiradas);
                stmBuscarRetiradas.setInt(1, rsBuscarCaixa.getInt("codigo"));
                
                rsBuscarRetiradas = stmBuscarRetiradas.executeQuery();
                Calendar dataAberturaCalendar = Calendar.getInstance();
                Date dataAberturaDate;
                dataAberturaDate = rsBuscarCaixa.getDate("data_abertura");
                dataAberturaCalendar.setTimeInMillis(dataAberturaDate.getTime());
                caixaAtual.setDataAbertura(dataAberturaCalendar);
                caixaAtual.setCodigo(rsBuscarCaixa.getInt("codigo"));
                caixaAtual.setValorAbertura(rsBuscarCaixa.getFloat("valor_abertura"));
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                jtDataAbertura.setText(format.format(dataAberturaDate));
                jtValorAbertura.setText(String.valueOf(caixaAtual.getValorAbertura()).replace(".", ","));
                if (rsBuscarRetiradas.next()) {
                    do {
                        Retirada retiradas = new Retirada();
                        retiradas.setCodigoCaixa(rsBuscarCaixa.getInt("codigo"));
                        retiradas.setDescricao(rsBuscarRetiradas.getString("descricao"));
                        retiradas.setValorRetirada(rsBuscarRetiradas.getFloat("valor_retirada"));
                        CaixaInterface.totalRetiradas = totalRetiradas + rsBuscarRetiradas.getFloat("valor_retirada");
                        CaixaInterface.modelTable.addRow(retiradas);

                    } while (rsBuscarRetiradas.next());
                    jtRetirada.setText(String.valueOf(totalRetiradas).replace(".", ","));
                } else {
                    jtRetirada.setText("0,00");
                }
                possuiCaixaAberto = true;

            } else {
                jtDataAbertura.setText("");
                jtRetirada.setText("");
                jtValorAbertura.setText("");
                jtValorFechamento.setText("");
                possuiCaixaAberto = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CaixaInterface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            minhaConexao.fecharConexao();
        }

    }

    public void caixasanteriores() {
        modelTableCaixa.removeAllRow();
        String sqlBuscarCaixasAnteriores = "SELECT caixa.data_abertura, caixa.data_fechamento, caixa.valor_abertura, caixa.valor_fechamento, caixa.codigo, caixa.status FROM dados.caixa where caixa.status = ?";
        String sqlRetiradas = "SELECT * FROM dados.retiradas where codigo_caixa = ?";
        Conexao minhaConexao = new Conexao();
        ResultSet rsCaixas = null;
        ResultSet rsRetiradas = null;
        DecimalFormat format = new DecimalFormat("0.00");
        
        Date dataA, dataF;
        float contadorRetiradas;

        try {
            PreparedStatement stmBuscarCaixas = minhaConexao.getConexao().prepareStatement(sqlBuscarCaixasAnteriores);
            PreparedStatement stmBuscarRetiradas = minhaConexao.getConexao().prepareStatement(sqlRetiradas);
            stmBuscarCaixas.setString(1, "Fechado");
            rsCaixas = stmBuscarCaixas.executeQuery();

            
            if (rsCaixas.next()) {
                do {
                        
                    contadorRetiradas = 0;
                    Calendar dataAbertura = Calendar.getInstance(), dataFechamento = Calendar.getInstance();
                    Caixa caixa = new Caixa();
                    caixa.setCodigo(rsCaixas.getInt("codigo"));
                    dataA = rsCaixas.getDate("data_abertura");
                    dataF = rsCaixas.getDate("data_fechamento");
                    dataAbertura.setTimeInMillis(dataA.getTime());
                    dataFechamento.setTimeInMillis(dataF.getTime());
                    caixa.setDataAbertura(dataAbertura);
                    caixa.setDataFechamento(dataFechamento);
                    caixa.setStatus(rsCaixas.getString("status"));
                    caixa.setValorAbertura(rsCaixas.getFloat("valor_abertura"));
                    caixa.setValorFechamento(rsCaixas.getFloat("valor_fechamento"));
                    stmBuscarRetiradas.setInt(1, caixa.getCodigo());
                    rsRetiradas = stmBuscarRetiradas.executeQuery();
                    if (rsRetiradas.next()) {
                        do {
                            contadorRetiradas += rsRetiradas.getFloat("valor_retirada");

                        } while (rsRetiradas.next());
                    }
                    modelTableCaixa.addRow(caixa, contadorRetiradas);
                } while (rsCaixas.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(CaixaInterface.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            minhaConexao.fecharConexao();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtDataAbertura = new javax.swing.JTextField();
        jtValorAbertura = new javax.swing.JTextField();
        jtRetirada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jtValorFechamento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Caixa Atual"));
        jPanel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Data de Abertura:");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Valor de Abertura:");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Retiradas:");

        jtDataAbertura.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        jtValorAbertura.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jtValorAbertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtValorAberturaActionPerformed(evt);
            }
        });

        jtRetirada.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Retiradas");

        jButton1.setBackground(new java.awt.Color(58, 65, 84));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Fechar Caixa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setText("Adicionar Retirada");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton3.setText("Excluir Retirada");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(58, 65, 84));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Abrir Novo Caixa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Valor de Fechamento:");

        jtValorFechamento.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(245, 245, 245))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jtValorFechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtValorAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtDataAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtDataAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtValorAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtValorFechamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Caixas Anteriores"));

        jTable2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (possuiCaixaAberto) {
            JOptionPane.showMessageDialog(null, "Já possui um caixa aberto", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        AbrirCaixa novo2 = new AbrirCaixa();
        novo2.setLocation(jDesktopPane1.getWidth() / 2 - novo2.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo2.getHeight() / 2);
        jDesktopPane1.add(novo2);
        novo2.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jtValorFechamento.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite um valor de Fechamento", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (possuiCaixaAberto) {
            Conexao minhaConexao = new Conexao();
            List<Retirada> retiradas = new ArrayList<>();

            String sqlSetRetiradas = "INSERT INTO dados.retiradas(valor_retirada, descricao, codigo_caixa) VALUES (?, ?, ?)";
            String sqlSetCaixa = "UPDATE dados.caixa SET data_fechamento=?, valor_fechamento=?, status=? WHERE codigo = ?";
            retiradas = modelTable.getAllRows();
            caixaAtual.setValorFechamento(Float.parseFloat(jtValorFechamento.getText().replace(",", ".")));
            caixaAtual.setDataFechamento(Calendar.getInstance());
            try {

                PreparedStatement stmSetRetiradas = minhaConexao.getConexao().prepareStatement(sqlSetRetiradas);
                PreparedStatement stmSetCaixa = minhaConexao.getConexao().prepareStatement(sqlSetCaixa);
                stmSetCaixa.setDate(1, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                stmSetCaixa.setFloat(2, caixaAtual.getValorFechamento());
                stmSetCaixa.setString(3, "Fechado");
                stmSetCaixa.setInt(4, caixaAtual.getCodigo());
                stmSetCaixa.executeUpdate();
                possuiCaixaAberto = false;
                totalRetiradas = 0;
                buscarCaixaAtual();
                caixasanteriores();
                JOptionPane.showMessageDialog(null, "Caixa Fechado com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                Logger.getLogger(CaixaInterface.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                minhaConexao.fecharConexao();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Sem caixa aberto para ser fechado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtValorAberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtValorAberturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtValorAberturaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(possuiCaixaAberto){
            AdicionarRetirada novo2 = new AdicionarRetirada(caixaAtual.getCodigo());
            novo2.setLocation(jDesktopPane1.getWidth() / 2 - novo2.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo2.getHeight() / 2);
            jDesktopPane1.add(novo2);
            novo2.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Não possui um caixa aberto", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTable1.getSelectedRow() >= 0) {
            Conexao minhaConexao = new Conexao();
            int selecionado = jTable1.getSelectedRow();
            String sqlApagarRetiradas = "DELETE FROM dados.retiradas WHERE codigo_caixa = ?";
            String sqlSetRetiradas = "INSERT INTO dados.retiradas(valor_retirada, descricao, codigo_caixa) VALUES (?, ?, ?)";
            List<Retirada> retiradas = new ArrayList<>();
            CaixaInterface.totalRetiradas = CaixaInterface.totalRetiradas - (float) modelTable.getValueAt(selecionado, 1);
            modelTable.removeRowAt(selecionado);
            retiradas = modelTable.getAllRows();
            try {
                PreparedStatement stmApagarRetiradas = minhaConexao.getConexao().prepareStatement(sqlApagarRetiradas);
                PreparedStatement stmSetRetiradas = minhaConexao.getConexao().prepareStatement(sqlSetRetiradas);
                stmApagarRetiradas.setInt(1, caixaAtual.getCodigo());
                stmApagarRetiradas.executeUpdate();
                for (Retirada e : retiradas) {
                    stmSetRetiradas.setFloat(1, e.getValorRetirada());
                    stmSetRetiradas.setString(2, e.getDescricao());
                    stmSetRetiradas.setInt(3, e.getCodigoCaixa());
                    stmSetRetiradas.executeUpdate();

                }
                CaixaInterface.buscarCaixaAtual();
                JOptionPane.showMessageDialog(null, "Retirada Excluida com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                Logger.getLogger(CaixaInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private static javax.swing.JTextField jtDataAbertura;
    public static javax.swing.JTextField jtRetirada;
    private static javax.swing.JTextField jtValorAbertura;
    private static javax.swing.JTextField jtValorFechamento;
    // End of variables declaration//GEN-END:variables
}
