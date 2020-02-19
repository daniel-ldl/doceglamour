/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Conexão.Conexao;
import Dados.Pedido;
import Dados.PedidoProduto;
import Dados.Produto;
import static Interfaces.Principal.jDesktopPane1;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.TabelaProdutosVisualizarPedido;
import util.TeclasPermitidas;

/**
 *
 * @author joao
 */
public class AlterarPedido extends javax.swing.JInternalFrame {

    /**
     * Creates new form AlterarPedido
     */
    Pedido pedido = new Pedido();
    TabelaProdutosVisualizarPedido modelTable = new TabelaProdutosVisualizarPedido();
    private float valorTotal;
    private float desconto;
    private float valorFinal;
    
    public AlterarPedido(Pedido pedido) {
        initComponents();
        jtdesconto.setDocument(new TeclasPermitidas());
        jtValorTotal.setEditable(false);
        jtfinal.setEditable(false);
        jtCliente.setEditable(false);
        jtCod.setEditable(false);
        this.pedido = pedido;
        jTable1.setModel(modelTable);
        loadOrder();
        carregarCombobox();
    }

    public void loadOrder(){
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        Date dataa = pedido.getData_entrega().getTime();
        Conexao minhaConexao = new Conexao();
        Calendar dataEntregaCalendar = pedido.getData_entrega();
        Calendar dataSinalCalendar = pedido.getData_sinal();
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        String dataEntregaString = data.format(dataEntregaCalendar.getTime());
        if(dataSinalCalendar == null){
            
        }else{
            String dataSinalString = data.format(dataSinalCalendar.getTime());
            jfDataSinal.setText(dataSinalString);
        }
        jfDataDeEntrega.setText(dataEntregaString);
        jfDataDeEntrega.setText(pedido.getData_entrega().toString());
        jtCod.setText(String.valueOf(pedido.getCodigo_pedido()));
        jtCrianca.setText(pedido.getNome_crianca());
        jtIdade.setText(String.valueOf(pedido.getIdade_crianca()));
        jtObs.setText(pedido.getObs());
        jtSinal.setText(String.valueOf(pedido.getValor_sinal()));
        jtTaxaDeEntrega.setText(String.valueOf(pedido.getTaxa_entrega()));
        jtTema.setText(pedido.getTema());
        jtdesconto.setText(String.valueOf(pedido.getDesconto()));
       
        if(pedido.getStatus().equals("Aberto")){
            jrAberto.setSelected(true);
        }else{
            jrFechado.setSelected(true);
        }
        String buscarCliente = "select nome from dados.cliente where codigo_cliente = ?";
        PreparedStatement stmBuscarCliente;
        ResultSet rsNomeCliente = null;
        try {
            stmBuscarCliente = minhaConexao.getConexao().prepareStatement(buscarCliente);
            stmBuscarCliente.setInt(1, pedido.getCodigo_cliente());
            rsNomeCliente = stmBuscarCliente.executeQuery();
            rsNomeCliente.next();
            jtCliente.setText(rsNomeCliente.getString("nome"));

        } catch (SQLException ex) {
            Logger.getLogger(AlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
            minhaConexao.fecharConexao();
        }
        String buscarProdutos = "select codigo_produto, quantidade from dados.produto_pedido where codigo_pedido = ?";
        String buscarDados = "select * from dados.produto where codigo_produto = ?";
        String nomeSabor = "select nome_sabor from dados.sabor where id_sabor = ?";
        ResultSet rsBuscarProdutos = null;
        ResultSet rsBuscarDados = null; 
        ResultSet rsNomeSabor = null;
        List<Integer>quantidade = new ArrayList<>();
        List<Integer>codigo_produto = new ArrayList<>();
        try {
            PreparedStatement stmBuscarProdutos = minhaConexao.getConexao().prepareStatement(buscarProdutos);
            PreparedStatement stmBuscarDados = minhaConexao.getConexao().prepareStatement(buscarDados);
            PreparedStatement stmBuscarNomeSabor = minhaConexao.getConexao().prepareStatement(nomeSabor);

            stmBuscarProdutos.setInt(1, pedido.getCodigo_pedido());
            rsBuscarProdutos = stmBuscarProdutos.executeQuery();
            while(rsBuscarProdutos.next()){
                
                stmBuscarDados.setInt(1, rsBuscarProdutos.getInt("codigo_produto"));
                rsBuscarDados= stmBuscarDados.executeQuery();
                rsBuscarDados.next();
                Produto novo = new Produto();
                novo.setCategoria(rsBuscarDados.getString("categoria"));
                novo.setCodigo_produto(rsBuscarProdutos.getInt("codigo_produto"));
                novo.setDescricao(rsBuscarDados.getString("descricao"));
                novo.setNome(rsBuscarDados.getString("nome"));
                novo.setQuantidade(rsBuscarProdutos.getInt("quantidade"));
                stmBuscarNomeSabor.setInt(1, Integer.parseInt(rsBuscarDados.getString("sabor")));
                rsNomeSabor = stmBuscarNomeSabor.executeQuery();
                rsNomeSabor.next();
                novo.setSabor(rsNomeSabor.getString("nome_sabor"));
                novo.setValor(rsBuscarDados.getFloat("valor"));
                modelTable.addRow(novo);
               
            }
            valorFinal = pedido.getValor_total();
            desconto = pedido.getDesconto();
            valorTotal = valorFinal + desconto;
            jtfinal.setText(String.valueOf(valorFinal));
            jtValorTotal.setText(String.valueOf(valorTotal));
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            minhaConexao.fecharConexao();
        }
        
        
        
        
    }
    public void carregarCombobox(){
        String sqlBuscarProduto = "select * from dados.produto";
        String sqlBuscarSabor = "select nome_sabor from dados.sabor where id_sabor = ?";
        ResultSet rsBucarSabor = null;
        ResultSet rsBuscarProduto = null;
        Conexao minhaConexao = new Conexao();
        List<Produto> produtos = new ArrayList<>();
        
        try {
            PreparedStatement stmBuscarProduto = minhaConexao.getConexao().prepareStatement(sqlBuscarProduto);
            PreparedStatement stmBuscarSabor = minhaConexao.getConexao().prepareStatement(sqlBuscarSabor);
            rsBuscarProduto = stmBuscarProduto.executeQuery();
            while(rsBuscarProduto.next()){
                Produto novo = new Produto();
                novo.setCategoria(rsBuscarProduto.getString("categoria"));
                novo.setCodigo_produto(rsBuscarProduto.getInt("codigo_produto"));
                novo.setDescricao(rsBuscarProduto.getString("descricao"));
                novo.setNome(rsBuscarProduto.getString("nome"));
                stmBuscarSabor.setInt(1, rsBuscarProduto.getInt("sabor"));
                rsBucarSabor = stmBuscarSabor.executeQuery();
                rsBucarSabor.next();
                novo.setSabor(rsBucarSabor.getString("nome_sabor"));
                novo.setValor(rsBuscarProduto.getFloat("valor"));
                produtos.add(novo);
                cbProduto.addItem(rsBuscarProduto.getString("nome"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtSinal = new javax.swing.JTextField();
        jfDataDeEntrega = new javax.swing.JFormattedTextField();
        jtCrianca = new javax.swing.JTextField();
        jtCliente = new javax.swing.JTextField();
        jtTema = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jtIdade = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jtTaxaDeEntrega = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jfDataSinal = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jtObs = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jrAberto = new javax.swing.JRadioButton();
        jrFechado = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jtCod = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbProduto = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jtValorTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfinal = new javax.swing.JTextField();
        jtdesconto = new javax.swing.JTextField();
        jlquantidade = new javax.swing.JLabel();
        jtquantidade = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("(Indique o Valor e Pressione Enter)");

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Gerais"));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel14.setText("Cliente:");

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel15.setText("Criança:");

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel16.setText("Data de Entrega:");

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel17.setText("Valor do Sinal:");

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel18.setText("Status:");

        try {
            jfDataDeEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel19.setText("Tema:");

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel20.setText("Idade:");

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel21.setText("Taxa de Entrega:");

        try {
            jfDataSinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel22.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel22.setText("Data Sinal:");

        jLabel23.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel23.setText("Obs:");

        buttonGroup1.add(jrAberto);
        jrAberto.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jrAberto.setText("Aberto");

        buttonGroup1.add(jrFechado);
        jrFechado.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jrFechado.setText("Fechado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jrAberto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrFechado))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtCrianca, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jfDataDeEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtSinal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtObs, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))))
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtTaxaDeEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jfDataSinal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jtIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jtTaxaDeEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jfDataSinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jtCrianca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jfDataDeEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jtSinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jrAberto)
                            .addComponent(jrFechado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtObs, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jLabel23))))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setText("Cod");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Produtos"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setText("Produto:");

        cbProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Selecione--" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/lupa.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/adicionar_produto.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/delete.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel5.setText("Sub Total:");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel8.setText("Descontos:");

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel9.setText("Valor Total:");

        jtfinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfinalActionPerformed(evt);
            }
        });

        jtdesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtdescontoActionPerformed(evt);
            }
        });
        jtdesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtdescontoKeyPressed(evt);
            }
        });

        jlquantidade.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jlquantidade.setText("Quantidade:");

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Sair");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Salvar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("(Indique o Valor e Pressione Enter)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jtquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlquantidade))
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfinal, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtdesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtdesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jlquantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtquantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String tela = "Alterar Pedido";
       BuscarProduto novoBuscarProduto = new BuscarProduto(tela);
       jDesktopPane1.add(novoBuscarProduto);
       novoBuscarProduto.setLocation(jDesktopPane1.getWidth() / 2 - novoBuscarProduto.getWidth() / 2, jDesktopPane1.getHeight() / 2 - novoBuscarProduto.getHeight() / 2);
       novoBuscarProduto.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtfinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfinalActionPerformed

    private void jtdescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtdescontoActionPerformed

        
    }//GEN-LAST:event_jtdescontoActionPerformed

    private void jtdescontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtdescontoKeyPressed
      if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(jtdesconto.getText().equals("")){
                valorFinal = valorFinal + desconto;
                jtfinal.setText(String.valueOf(valorFinal));
                desconto = 0;
                
                
            }else{
                desconto = Float.parseFloat(jtdesconto.getText().replace(",", "."));
                valorFinal = valorTotal - desconto;
                jtfinal.setText(String.valueOf(valorFinal));
            }

        }

    }//GEN-LAST:event_jtdescontoKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(cbProduto.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Selecione um Produto");
            return;
        }
        if(jtquantidade.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Indique uma quantidade", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
        String produtoSelecionado = (String) cbProduto.getSelectedItem();
        String buscarProduto = "select * from dados.produto where nome = ?";
        String buscarSabor = "select nome_sabor from dados.sabor where id_sabor = ?";
        Conexao minhaConexao  = new Conexao();
        ResultSet rsBuscarProduto = null;
        ResultSet rsBuscarSabor = null;
        try {
            PreparedStatement stmBuscarProduto = minhaConexao.getConexao().prepareStatement(buscarProduto);
            PreparedStatement stmBuscarSabor = minhaConexao.getConexao().prepareStatement(buscarSabor);
            stmBuscarProduto.setString(1, produtoSelecionado);
            rsBuscarProduto = stmBuscarProduto.executeQuery();
            rsBuscarProduto.next();
            Produto novo = new Produto();
            novo.setCategoria(rsBuscarProduto.getString("categoria"));
            novo.setCodigo_produto(rsBuscarProduto.getInt("codigo_produto"));
            novo.setDescricao(rsBuscarProduto.getString("descricao"));
            novo.setNome(rsBuscarProduto.getString("nome"));
            novo.setQuantidade(Integer.parseInt(jtquantidade.getText()));
            stmBuscarSabor.setInt(1, rsBuscarProduto.getInt("sabor"));
            rsBuscarSabor = stmBuscarSabor.executeQuery();
            rsBuscarSabor.next();
            novo.setSabor(rsBuscarSabor.getString("nome_sabor"));
            novo.setValor(rsBuscarProduto.getFloat("valor"));
            modelTable.addRow(novo);
            float valorProduto = novo.getValor() * novo.getQuantidade();
            valorTotal = valorTotal + valorProduto;
            jtValorTotal.setText(String.valueOf(valorTotal));
            valorFinal = valorTotal - desconto;
            jtfinal.setText(String.valueOf(valorFinal));
        } catch (SQLException ex) {
            Logger.getLogger(AlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            minhaConexao.fecharConexao();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jTable1.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Selecione uma produto para Excluir", "Atenção", JOptionPane.INFORMATION_MESSAGE);
           
        }else{
            int index = jTable1.getSelectedRow();
            Produto novo = new Produto();
            DecimalFormat format = new DecimalFormat("0.00");
            novo = modelTable.buscarProdutoCompleto(index);
            float auxValorTotal, auxValorFinal;
            
            valorTotal = valorTotal - (novo.getValor() * novo.getQuantidade());
            valorFinal = (valorFinal - (novo.getValor() * novo.getQuantidade())) - desconto;
            jtValorTotal.setText(String.valueOf(format.format(valorTotal)));
            jtfinal.setText(String.valueOf(format.format(valorFinal)));
            modelTable.removeRow(index);
            
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    if(jtTema.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Campo Tema nao pode ser em Branco", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    if(jfDataDeEntrega.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Campo Data de Entrega nao pode ser em Branco", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    

    if(jrAberto.isSelected()){
        pedido.setStatus("Aberto");
    }else{
        pedido.setStatus("Fechado");
    }
    PedidoProduto novo = new PedidoProduto();
    Calendar dataEntrega = Calendar.getInstance();
    Calendar dataSinal = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<String> nomeProduto = new ArrayList<>();
    ArrayList<String> quantidade = new ArrayList<>();
    Conexao minhaConexao = new Conexao();
    
        try {
            
            dataEntrega.setTime(format.parse(jfDataDeEntrega.getText()));
            if(jfDataSinal.getText().equals("  /  /    ")){
                dataSinal = null;
            }else{
                dataSinal.setTime(format.parse(jfDataSinal.getText()));
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(AlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        pedido.setData_entrega(dataEntrega);
        pedido.setData_sinal(dataSinal);
        if(jtdesconto.getText().equals("")){
            
        }else{
            pedido.setDesconto(Float.parseFloat(jtdesconto.getText().replace(",", ".")));
        }
       
        pedido.setIdade_crianca(Integer.parseInt(jtIdade.getText()));
        pedido.setNome_crianca(jtCrianca.getText());
        pedido.setObs(jtObs.getText());
        if(jtTaxaDeEntrega.getText().equals("")){
            
        }else{
            pedido.setTaxa_entrega(Float.parseFloat(jtTaxaDeEntrega.getText().replace(",", ".")));
        }
       
        pedido.setTema(jtTema.getText());
        
        if(jtSinal.getText().equals("")){
            
        }else{
            pedido.setValor_sinal(Float.parseFloat(jtSinal.getText().replace(",", ".")));
        }
        
        pedido.setValor_total(Float.parseFloat(jtfinal.getText()));
        for(int i = 0 ; i < modelTable.getRowCount() ; i++){
            nomeProduto.add((String) modelTable.getValueAt(i, 0));
            quantidade.add(String.valueOf(modelTable.getValueAt(i, 1)));
        }
        String alterarPedido = "UPDATE dados.pedido SET tema=?, valor_total=?, nome_crianca=?, idadecrianca=?, data_entrega=?, valor_sinal=?, taxa_entrega=?, desconto=?, data_sinal=?, obs=?, status=? WHERE codigo_pedido = ? ";
        String deletarPedidoProduto = "DELETE FROM dados.produto_pedido WHERE codigo_pedido = ?";
        try {
            PreparedStatement stmUpdate = minhaConexao.getConexao().prepareStatement(alterarPedido);
            PreparedStatement stmDelete = minhaConexao.getConexao().prepareStatement(deletarPedidoProduto);
            stmUpdate.setString(1, pedido.getTema());
            stmUpdate.setFloat(2, pedido.getValor_total());
            stmUpdate.setString(3, pedido.getNome_crianca());
            stmUpdate.setInt(4, pedido.getIdade_crianca());
            stmUpdate.setDate(5, new java.sql.Date(pedido.getData_entrega().getTimeInMillis()));
            stmUpdate.setFloat(6,pedido.getValor_sinal());
            stmUpdate.setFloat(7, pedido.getTaxa_entrega());
            stmUpdate.setFloat(8, pedido.getDesconto());
            if(pedido.getData_sinal() == null){
                stmUpdate.setDate(9, null);
            }else{
                stmUpdate.setDate(9, new java.sql.Date(pedido.getData_sinal().getTimeInMillis()));
            }
            stmUpdate.setString(10, pedido.getObs());
            stmUpdate.setString(11, pedido.getStatus());
            stmUpdate.setInt(12, pedido.getCodigo_pedido());
            stmUpdate.executeUpdate();
            stmDelete.setInt(1, pedido.getCodigo_pedido());
            stmDelete.executeUpdate();
            if(modelTable.getRowCount() == 0){
        
            }else{
                PedidoProduto pedidoproduto = new PedidoProduto();
                pedidoproduto.setProduto(nomeProduto);
                pedidoproduto.setQuantidade(quantidade);
                pedidoproduto.setCodigoPedido(pedido.getCodigo_pedido());
                pedidoproduto.InserirBd();
            }
            
            
            JOptionPane.showMessageDialog(null, "Alteração efetuado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            Principal.tableModel.removeAllRow();
            Principal.tableModel.preencherJTable();
            this.dispose();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            minhaConexao.fecharConexao();
        }
    
    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox<String> cbProduto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField jfDataDeEntrega;
    private javax.swing.JFormattedTextField jfDataSinal;
    private javax.swing.JLabel jlquantidade;
    private javax.swing.JRadioButton jrAberto;
    private javax.swing.JRadioButton jrFechado;
    private javax.swing.JTextField jtCliente;
    private javax.swing.JTextField jtCod;
    private javax.swing.JTextField jtCrianca;
    private javax.swing.JTextField jtIdade;
    private javax.swing.JTextField jtObs;
    private javax.swing.JTextField jtSinal;
    private javax.swing.JTextField jtTaxaDeEntrega;
    private javax.swing.JTextField jtTema;
    private javax.swing.JTextField jtValorTotal;
    private javax.swing.JTextField jtdesconto;
    private javax.swing.JTextField jtfinal;
    private javax.swing.JTextField jtquantidade;
    // End of variables declaration//GEN-END:variables
}
