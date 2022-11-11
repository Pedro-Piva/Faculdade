/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author pedro
 */
public class Arquivo {
    private String pai;
    private String conteudo = "";
    private String nome;
    private String extencao;
    private String caminho = "";
    private int permissao;
    
    public Arquivo(String pai, String c, String n, String e, int p){
        setPai(pai);
        setConteudo(c);
        setNome(n);
        setExtencao(e);
        setPermissao(p);
    }
    
    public void setPai(String pai){
        this.pai = pai;
    }
    public String getPai(){
        return pai;
    }
    public void setConteudo(String s){
        this.conteudo = s + conteudo;
    }
    public String getConteudo(){
        return conteudo;
    }
     public void setNome(String s){
        this.nome = s;
    }
    public String getNome(){
        return nome;
    }
    public void setExtencao(String s){
        this.extencao = s;
    }
    public String getExtencao(){
        return extencao;
    }
    public void setPermissao(int i){
        this.permissao = i;
    }
    public int getPermissao(){
        return permissao;
    }
    public void setCaminho(String s){
        this.caminho = s + "/" + nome;
    }
    public String getCaminho(){
        return caminho;
    }
}
