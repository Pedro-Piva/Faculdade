package main;
import java.util.ArrayList;
/**
 *
 * @author pedro
 */
public class Diretorio {
       private ArrayList<Diretorio> diretorio;
       private String nome;
       private ArrayList<String> filhos = new ArrayList<String>();
       private String pai;
       private String caminho;
       private int permissao;
       private ArrayList<Arquivo> arquivos = new ArrayList<Arquivo>();
       public Diretorio(String nome){
           this.nome = nome;
           diretorio = new ArrayList<>();
       }
    public boolean criado(){
           if(nome != null){
               return true;
           }
           else{
               return false;
           }
       }
    public ArrayList<Diretorio> getDiretorio() {
    	    return diretorio;
    	}
    public String getNome(){
    	   return nome;
       }
    public void setCaminho(String caminho) {
    	   this.caminho = caminho + "/" + nome;
       }
    public String getCaminho() {
    	   return caminho;
       }
    public Diretorio getDiretorios(){
    for(Diretorio item : diretorio) {
        return item;
    }
    return null;
    }   
    public void setPai(String pai){
        this.pai = pai;
    }
    public String getPai(){
        return pai;
    }
    public void setFilho(String filho){
        filhos.add(filho);
    }
    public ArrayList<String> getFilho(){
        return filhos;
    }
    public String getPFilho(int index){
        return filhos.get(index);
    }
    public void removeFilho(int pos){
        filhos.remove(pos);
    }
    public void criarArquivo(Arquivo a){
        arquivos.add(a);
    }
    public ArrayList<Arquivo> getArquivo(){
        return arquivos;
    }
    public int getPermissao(){
        return permissao;
    }
    public void setPermissao(int permissao){
        this.permissao = permissao;
    }
}
