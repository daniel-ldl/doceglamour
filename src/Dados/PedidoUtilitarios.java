/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import Conexão.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class PedidoUtilitarios {

    public PedidoUtilitarios() {
    }
    
    
    public List<Pedido> preencherJTable(){
        java.sql.Date dataSql = null;
        Date dataEntrega;
        Date dataSinal;             
        Conexao minhaConexao = new Conexao();
        minhaConexao.getConexao();
        String sqlBuscar = "SELECT * from dados.pedido where status = ? order by data_entrega asc";
        ResultSet rs = null;
        List<Pedido> novaLista = new ArrayList<>();
        try {
            PreparedStatement stmBuscarPedido = minhaConexao.getConexao().prepareStatement(sqlBuscar);
            stmBuscarPedido.setString(1, "Aberto");
            rs = stmBuscarPedido.executeQuery();
            while(rs.next()){
                
                Pedido novo =  new Pedido();
                 Calendar convertido = Calendar.getInstance();
                 Calendar convertidoDataSinal = Calendar.getInstance();
                
                if(rs.getDate("data_sinal") == null){

                }else{
                    dataSinal = rs.getDate("data_sinal");
                    convertidoDataSinal.setTimeInMillis(dataSinal.getTime());
                    novo.setData_sinal(convertidoDataSinal);  
                   

                }
                dataEntrega = rs.getDate("data_entrega");
                convertido.setTimeInMillis(dataEntrega.getTime());
                novo.setData_entrega(convertido);
                novo.setCodigo_pedido(rs.getInt("codigo_pedido"));
                novo.setCodigo_cliente(rs.getInt("codigo_cliente"));
                novo.setDesconto(rs.getFloat("desconto"));
                novo.setIdade_crianca(rs.getInt("idadecrianca"));
                novo.setNome_crianca(rs.getString("nome_crianca"));
                novo.setObs(rs.getString("obs"));
                novo.setTaxa_entrega(rs.getFloat("taxa_entrega"));
                novo.setTema(rs.getString("tema"));
                novo.setValor_sinal(rs.getFloat("valor_sinal"));
                novo.setValor_total(rs.getFloat("valor_total"));
                novo.setStatus(rs.getString("status"));  
                novo.setCodigo_usuario(rs.getInt("codigo_usuario"));
                
               
               
                novaLista.add(novo);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            minhaConexao.fecharConexao();
            
        }
        return novaLista;
        
    }
}
