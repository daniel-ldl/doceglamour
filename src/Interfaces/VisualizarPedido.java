/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Conexão.Conexao;
import Dados.Pedido;
import Dados.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TabelaProdutosVisualizarPedido;

/**
 *
 * @author joao
 */
public class VisualizarPedido extends javax.swing.JInternalFrame {

    /**
     * Creates new form AlterarPedido
     */
    TabelaProdutosVisualizarPedido model = new TabelaProdutosVisualizarPedido();
    Pedido carregarPedido = new Pedido();
    List <Produto> produtos = new ArrayList<>();
    public VisualizarPedido(Pedido pedido) {
        initComponents();
        jTableProdutos.setModel(model);
        carregarPedido = pedido;
        loadOrder();
        fecharCampos();
    }
    public void loadOrder(){
        
        //preencher campos
        Conexao minhaConexao = new Conexao();
        minhaConexao.getConexao();
        ResultSet rs = null;
        ResultSet rsProdutos = null;
        ResultSet rsUsuario = null;
        String nomeCliente = "";
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataEntregaCalendar = carregarPedido.getData_entrega();
        Calendar dataSinalCalendar = carregarPedido.getData_sinal();
        String dataSinalString;
        String dataEntregaString;
        int codigoCliente = carregarPedido.getCodigo_cliente();
        int codigoPedido = carregarPedido.getCodigo_pedido();
        List <Integer> quantidades = new ArrayList<>();
        
        String sql = "SELECT cliente.nome FROM dados.cliente where codigo_cliente = ?";
        String sqlBucarProdutos = "SELECT codigo_produto, quantidade FROM dados.produto_pedido WHERE codigo_pedido = '"+codigoPedido+"';";
        String sqlBuscarUsuario = "SELECT nome FROM dados.usuario WHERE id_usuario = ?";
        try {
            PreparedStatement stmBuscarNomeCliente = minhaConexao.getConexao().prepareStatement(sql);
            PreparedStatement stmBuscarProdutos = minhaConexao.getConexao().prepareStatement(sqlBucarProdutos);
            PreparedStatement stmBuscarUsuario = minhaConexao.getConexao().prepareStatement(sqlBuscarUsuario);
            stmBuscarUsuario.setInt(1, carregarPedido.getCodigo_usuario());
            stmBuscarNomeCliente.setInt(1, codigoCliente);
            rs = stmBuscarNomeCliente.executeQuery();
            rsProdutos = stmBuscarProdutos.executeQuery(); 
            rsUsuario = stmBuscarUsuario.executeQuery();
            rsUsuario.next();
            rs.next();
            nomeCliente = rs.getString("nome");
        } catch (SQLException ex) {
            Logger.getLogger(VisualizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(carregarPedido.getStatus().equals("Aberto")){
            rbAberto.setSelected(true);
        }else{
            rbFechado.setSelected(true);
        }
        jtCliente.setText(nomeCliente);
        jtCrianca.setText(carregarPedido.getNome_crianca());
        jtIdade.setText(String.valueOf(carregarPedido.getIdade_crianca()));
        jtSinal.setText(String.valueOf(carregarPedido.getValor_sinal()));
        jtTaxaDeEntrega.setText(String.valueOf(carregarPedido.getTaxa_entrega()));
        jtCod.setText(String.valueOf(carregarPedido.getCodigo_pedido()));
        jtObs.setText(carregarPedido.getObs());
        jtTema.setText(carregarPedido.getTema());
        jtValorTotal.setText(String.valueOf(carregarPedido.getValor_total()));
        jtDesconto.setText(String.valueOf(carregarPedido.getDesconto()));
        try {
            jtUsuario.setText(rsUsuario.getString("nome"));
        } catch (SQLException ex) {
            Logger.getLogger(VisualizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataEntregaString = data.format(dataEntregaCalendar.getTime());
        if(dataSinalCalendar == null){
            
        }else{
             dataSinalString = data.format(dataSinalCalendar.getTime());
             jfDataDeEntrega.setText(dataSinalString);
        }
        jfDataDeEntrega.setText(dataEntregaString);
         //preencher tabela
         int codProduto = 0;
         int codSabor = 0;
         ResultSet rsDadosProdutos = null;
         ResultSet rsSabor = null;
        try {
           
            while(rsProdutos.next()){
                quantidades.add(rsProdutos.getInt("quantidade"));
                codProduto = rsProdutos.getInt("codigo_produto");
                String sqlBuscarProduto = "SELECT nome, descricao, valor, categoria, sabor FROM dados.produto where codigo_produto = '"+codProduto+"';";
                PreparedStatement stmBuscarDadosProduto = minhaConexao.getConexao().prepareStatement(sqlBuscarProduto);
                rsDadosProdutos = stmBuscarDadosProduto.executeQuery();
                rsDadosProdutos.next();
                codSabor = rsDadosProdutos.getInt("sabor");
                String sqlBuscarSabor = "SELECT nome_sabor FROM dados.sabor where id_sabor = '"+codSabor+"';";
                PreparedStatement stmBuscarSabor = minhaConexao.getConexao().prepareStatement(sqlBuscarSabor);
                rsSabor = stmBuscarSabor.executeQuery();
                rsSabor.next();
                Produto novo = new Produto();
                novo.setCategoria(rsDadosProdutos.getString("categoria"));
                novo.setCodigo_produto(codProduto);
                novo.setDescricao(rsDadosProdutos.getString("descricao"));
                novo.setSabor(rsSabor.getString("nome_sabor"));
                novo.setValor(rsDadosProdutos.getFloat("valor"));
                novo.setQuantidade(rsProdutos.getInt("quantidade"));
                novo.setNome(rsDadosProdutos.getString("nome"));
                produtos.add(novo);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VisualizarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Produto p : produtos){
            model.addRow(p);
        }
        
        
        
        
        
        
       
    }
    public void fecharCampos(){
        jtCliente.setEditable(false);
        jtCod.setEnabled(false);
        jtCrianca.setEditable(false);
        jtDesconto.setEditable(false);
        jtIdade.setEditable(false);
        jtObs.setEditable(false);
        jtSinal.setEditable(false);
        jtTaxaDeEntrega.setEditable(false);
        jtTema.setEditable(false);
        jtValorTotal.setEditable(false);
        rbAberto.setEnabled(false);
        rbFechado.setEnabled(false);
        jtUsuario.setEditable(false);
        jfDataDeEntrega.setEditable(false);
        jfDataSinal.setEditable(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtTema = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtCrianca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtIdade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jfDataDeEntrega = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jtTaxaDeEntrega = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtSinal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jfDataSinal = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        rbAberto = new javax.swing.JRadioButton();
        rbFechado = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jtObs = new javax.swing.JTextField();
        jtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jtValorTotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtDesconto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtUsuario = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Gerais"));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setText("Cliente:");

        jtCliente.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setText("Tema:");

        jtTema.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setText("Criança:");

        jtCrianca.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel5.setText("Idade:");

        jtIdade.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel6.setText("Data de Entrega:");

        try {
            jfDataDeEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataDeEntrega.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jfDataDeEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jfDataDeEntregaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel7.setText("Taxa de Entrega:");

        jtTaxaDeEntrega.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setText("Valor do Sinal:");

        jtSinal.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel9.setText("Data Sinal:");

        try {
            jfDataSinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jfDataSinal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel10.setText("Status:");

        buttonGroup1.add(rbAberto);
        rbAberto.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        rbAberto.setText("Aberto");

        buttonGroup1.add(rbFechado);
        rbFechado.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        rbFechado.setText("Fechado");

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel11.setText("Obs:");

        jtObs.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbAberto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbFechado))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jfDataDeEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 221, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtSinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jtCrianca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                        .addComponent(jtCliente, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtTaxaDeEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtObs, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtTema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jfDataSinal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtTaxaDeEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jfDataSinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jtObs))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtCrianca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jfDataDeEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtSinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(rbAberto)
                            .addComponent(rbFechado))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setText("Cod");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Produtos"));

        jTableProdutos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableProdutos);

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel12.setText("Valor Total:");

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel13.setText("Valor Desconto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel14.setText("Vendedor:");

        jtUsuario.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14)
                    .addComponent(jtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jfDataDeEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfDataDeEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jfDataDeEntregaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JFormattedTextField jfDataDeEntrega;
    private javax.swing.JFormattedTextField jfDataSinal;
    private javax.swing.JTextField jtCliente;
    private javax.swing.JTextField jtCod;
    private javax.swing.JTextField jtCrianca;
    private javax.swing.JTextField jtDesconto;
    private javax.swing.JTextField jtIdade;
    private javax.swing.JTextField jtObs;
    private javax.swing.JTextField jtSinal;
    private javax.swing.JTextField jtTaxaDeEntrega;
    private javax.swing.JTextField jtTema;
    private javax.swing.JTextField jtUsuario;
    private javax.swing.JTextField jtValorTotal;
    private javax.swing.JRadioButton rbAberto;
    private javax.swing.JRadioButton rbFechado;
    // End of variables declaration//GEN-END:variables
}
