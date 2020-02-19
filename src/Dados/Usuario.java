/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

/**
 *
 * @author joao
 */
public class Usuario {
    private String usuario;
    private int id_usuario;
    private String senha;
    private int nivel;
    private String nome;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public int getNivel() {
        return nivel;
    }

    public String getNome() {
        return nome;
    }

    public Usuario(String usuario, int id_usuario, String senha, int nivel, String nome) {
        this.usuario = usuario;
        this.senha = senha;
        this.nivel = nivel;
        this.nome = nome;
        this.id_usuario = id_usuario;
    }

    public Usuario() {
    }
    
    
    
}
