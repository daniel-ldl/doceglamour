/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Conexão.Conexao;
import Dados.Pedido;
import Dados.Usuario;
import javax.swing.JOptionPane;
import model.TabelaPedidosTelaPrincipalModel;
import java.awt.Image;
import java.awt.Graphics;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Aluno
 */
public class Principal extends javax.swing.JFrame {

    public static boolean verificarProduto = true;
    public static boolean verificarPedido = true;
    public static boolean verificarCliente = true;

    @SuppressWarnings("OverridableMethodCallInConstructor")

    public static TabelaPedidosTelaPrincipalModel tableModel = new TabelaPedidosTelaPrincipalModel();
    public static Usuario usuario;

    public Principal(Usuario usuario) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        jtpedidos.setModel(tableModel);
        tableModel.preencherJTable();
        this.usuario = usuario;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Imagens/fundo_2.jpg"));
        Image image = icon.getImage();
        jDesktopPane1 = new javax.swing.JDesktopPane(){

            public void paintComponent(Graphics g){

                g.drawImage(image,0, 0, getWidth(),getHeight(), this);
            }
        };
        jButton3 = new javax.swing.JButton();
        jbcadastrarcliente = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpedidos = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Visualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jbcadastrarcliente.setBackground(new java.awt.Color(255, 255, 255));
        jbcadastrarcliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/adicionar cliente.png"))); // NOI18N
        jbcadastrarcliente.setToolTipText("Cadastrar Cliente");
        jbcadastrarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcadastrarclienteActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/adicionar produto.jpg"))); // NOI18N
        jButton2.setToolTipText("Cadastrar Produto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/adicionarpedido.jpg"))); // NOI18N
        jButton1.setToolTipText("Cadastrar Pedido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtpedidos.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jtpedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtpedidos);

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Alterar");
        jButton4.setMaximumSize(new java.awt.Dimension(97, 29));
        jButton4.setMinimumSize(new java.awt.Dimension(97, 29));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Excluir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buscarcliente.png"))); // NOI18N
        jButton6.setToolTipText("Buscar Cliente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buscarproduto.jpg"))); // NOI18N
        jButton7.setToolTipText("Buscar Produto");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buscarpedido.png"))); // NOI18N
        jButton8.setToolTipText("Buscar Pedido");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/relatorio.jpg"))); // NOI18N
        jButton9.setToolTipText("Relatorios");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Cadastrar Cliente");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Cadastrar Produto");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Cadastrar Pedido");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Buscar Cliente");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Buscar Produto");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Buscar Pedido");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Relatórios");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/caixa.png"))); // NOI18N
        jButton10.setToolTipText("Fluxo de Caixa");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Caixa");

        jDesktopPane1.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jbcadastrarcliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbcadastrarcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)))
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(29, 29, 29)))
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel8)))
                                .addGap(0, 137, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addGap(14, 14, 14))))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbcadastrarcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4))
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5))
                .addGap(142, 142, 142))
        );

        jMenu1.setText("Arquivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cliente");

        jMenuItem1.setText("Cadastrar Cliente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setText("Buscar Cliente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pedido");

        jMenuItem4.setText("Cadastrar Pedido");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Buscar Pedido");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Relatorio de Pedidos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Produto");

        jMenuItem8.setText("Cadastrar Produto");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Buscar Produto");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Relatorio novo = new Relatorio();
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (verificarCliente == true) {
            CadastrarCliente novo = new CadastrarCliente();
            novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
            jDesktopPane1.add(novo);
            novo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ja possui uma janela de cadastro de cliente aberta", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (verificarPedido == true) {
            CadastrarPedido novo = new CadastrarPedido(usuario);
            novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
            jDesktopPane1.add(novo);
            novo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ja possui uma janela de cadastro de pedido aberta ", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if (usuario.getNivel() == 2) {
            JOptionPane.showMessageDialog(null, "Operação valida somente para Administradores", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (verificarProduto == true) {
            CadastrarProduto novo = new CadastrarProduto();
            novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
            jDesktopPane1.add(novo);
            novo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ja possui uma janela de cadastro de produto aberta", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int linha = jtpedidos.getSelectedRow();
        if (linha >= 0) {
            Pedido selecionado = new Pedido();
            selecionado = tableModel.getPedidoCompleto(linha);
            VisualizarPedido alterarPedido = new VisualizarPedido(selecionado);
            alterarPedido.setLocation(jDesktopPane1.getWidth() / 2 - alterarPedido.getWidth() / 2, jDesktopPane1.getHeight() / 2 - alterarPedido.getHeight() / 2);
            jDesktopPane1.add(alterarPedido);
            alterarPedido.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (usuario.getNivel() == 2) {
            JOptionPane.showMessageDialog(null, "Operação valida somente para Administradores", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (verificarProduto == true) {
            CadastrarProduto novo = new CadastrarProduto();
            novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
            jDesktopPane1.add(novo);
            novo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ja possui uma janela idêntica aberta", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (verificarPedido == true) {
            CadastrarPedido novo = new CadastrarPedido(usuario);
            novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
            jDesktopPane1.add(novo);
            novo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ja possui uma janela de cadastro de pedido aberta ", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbcadastrarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcadastrarclienteActionPerformed
        if (verificarCliente == true) {
            CadastrarCliente novo = new CadastrarCliente();
            novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
            jDesktopPane1.add(novo);
            novo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Ja possui uma janela de cadastro de cliente aberta", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbcadastrarclienteActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (usuario.getNivel() == 2) {
            JOptionPane.showMessageDialog(null, "Operação valida somente para Administradores", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int linha = jtpedidos.getSelectedRow();
        if (linha >= 0) {

            Pedido selecionado = new Pedido();
            selecionado = tableModel.getPedidoCompleto(linha);
            AlterarPedido alterarPedido = new AlterarPedido(selecionado);
            alterarPedido.setLocation(jDesktopPane1.getWidth() / 2 - alterarPedido.getWidth() / 2, jDesktopPane1.getHeight() / 2 - alterarPedido.getHeight() / 2);
            jDesktopPane1.add(alterarPedido);
            alterarPedido.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (usuario.getNivel() == 2) {
            JOptionPane.showMessageDialog(null, "Operação valida somente para Administradores", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (jtpedidos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int escolha = JOptionPane.showConfirmDialog(null, "Deseja Mesmo Excluir Pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (escolha == 0) {
                Pedido novo = new Pedido();
                novo = tableModel.getPedidoCompleto(jtpedidos.getSelectedRow());
                String deletePedido = "DELETE FROM dados.pedido WHERE codigo_pedido = ?";
                String deletarPedidoProduto = "DELETE FROM dados.produto_pedido WHERE codigo_pedido = ?";
                String conferirProdutoPedido = "select * from dados.produto_pedido where codigo_pedido = ?";
                Conexao minhaConexao = new Conexao();
                ResultSet rs = null;
                try {
                    PreparedStatement stmDeletarPedido = minhaConexao.getConexao().prepareStatement(deletePedido);
                    PreparedStatement stmDeletarPedidoProduto = minhaConexao.getConexao().prepareStatement(deletarPedidoProduto);
                    PreparedStatement stmconferirProdutoPedido = minhaConexao.getConexao().prepareStatement(conferirProdutoPedido);
                    stmconferirProdutoPedido.setInt(1, novo.getCodigo_pedido());
                    stmDeletarPedido.setInt(1, novo.getCodigo_pedido());
                    stmDeletarPedidoProduto.setInt(1, novo.getCodigo_pedido());
                    rs = stmconferirProdutoPedido.executeQuery();
                    if (rs.next()) {
                        stmDeletarPedidoProduto.executeUpdate();
                        stmDeletarPedido.executeUpdate();
                        tableModel.removeAllRow();
                        tableModel.preencherJTable();
                        JOptionPane.showMessageDialog(null, "Pedido Excluido com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        stmDeletarPedido.executeUpdate();
                        tableModel.removeAllRow();
                        tableModel.preencherJTable();
                        JOptionPane.showMessageDialog(null, "Pedido Excluido com Sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    minhaConexao.fecharConexao();
                }
            } else {
                return;
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        BuscarPedido novo = new BuscarPedido(usuario.getNivel());
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        BuscarClientePrincipal novo = new BuscarClientePrincipal();
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        BuscarProdutoPrincipal novo = new BuscarProdutoPrincipal();
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);

    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        BuscarClientePrincipal novo = new BuscarClientePrincipal();
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        BuscarProdutoPrincipal novo = new BuscarProdutoPrincipal();
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        BuscarPedido novo = new BuscarPedido(usuario.getNivel());
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Relatorio novo = new Relatorio();
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        CaixaInterface novo = new CaixaInterface();
        novo.setLocation(jDesktopPane1.getWidth() / 2 - novo.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novo.getHeight() / 2);
        jDesktopPane1.add(novo);
        novo.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal(usuario).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbcadastrarcliente;
    private javax.swing.JTable jtpedidos;
    // End of variables declaration//GEN-END:variables
}