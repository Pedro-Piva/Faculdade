package main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import operatingSystem.Kernel;
import java.util.ArrayList;

/**
 * Kernel desenvolvido pelo aluno. Outras classes criadas pelo aluno podem ser
 * utilizadas, como por exemplo: - Arvores; - Filas; - Pilhas; - etc...
 *
 * @author nome do aluno...
 */
public class MyKernel implements Kernel {

    Diretorio d = new Diretorio("Raiz");
    ArrayList<Arquivo> a = new ArrayList<Arquivo>();
    int index = 0;
    int atual = 0;
    int indexa = 0;
    boolean raiz = true;
    public MyKernel() {
        
    }

    public String ls(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: ls");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        System.out.println("ola pessoal!");
        String s = "";
        for(int i = 0;i<d.getDiretorio().size();i++){
            result = result + " " + d.getDiretorio().get(i).getNome();
        }
        for(int i = 0;i < a.size(); i++){
            s = a.get(i).getNome() + s;
        }
        result = "Diretorios:" + result + " Arquivos: " + s;
        //fim da implementacao do aluno
        return result;
    }

    public String mkdir(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: mkdir");
        System.out.println("\tParametros: " + parameters);
        //inicio da implementacao do aluno
        boolean f = true;
        System.out.println(d.getDiretorio().size());
        for(int i = 0; i < d.getDiretorio().size()  ;i++ ){
            String s = d.getDiretorio().get(i).getNome();
            if(parameters.equals(s)){
                f = false;
                break;
            } 
        }
        String[] s = parameters.split(" ");
        try{
            if(s[1] != ""){
                f = false;
            }
        }catch(Exception e){
            
        }
        if(f){
            d.getDiretorio().add(new Diretorio(parameters));
            if(index == 0){
                d.setPai("");
                d.setFilho(d.getDiretorio().get(index).getNome());
                d.getDiretorio().get(index).setPai("Raiz");
                d.getDiretorio().get(index).setCaminho("");
                System.out.println(d.getFilho() + " " + d.getNome());
                System.out.println(d.getDiretorio().get(index).getCaminho());
                index++;
            }else{
                if(raiz){
                    d.getDiretorio().get(index).setPai(d.getNome());
                    d.setFilho(d.getDiretorio().get(index).getNome());
                    d.getDiretorio().get(index).setCaminho("");
                    System.out.println(d.getFilho());
                    index++;
                }else{
                    d.getDiretorio().get(index).setPai(d.getDiretorio().get(atual).getNome());
                    d.getDiretorio().get(atual).setFilho(d.getDiretorio().get(index).getNome());
                    d.getDiretorio().get(index).setCaminho(d.getDiretorio().get(atual).getCaminho());
                    System.out.println(d.getDiretorio().get(atual).getFilho());
                    index++;
                }
            }
            result = "Diretorio " +  parameters + " criado com Sucesso" ;
        }else{
            result = "Nome do Diretorio ja existente";
        }
        try{
            if(s[1] != ""){
                result = "Nome Invalido";
            }
        }catch(Exception e){
            
        }
        //fim da implementacao do aluno
        return result;
    }

    public String cd(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        String currentDir = "";
        System.out.println("Chamada de Sistema: cd");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        //indique o diretório atual. Por exemplo... /
        boolean f = false;
        for(int i = 0; i < d.getDiretorio().size()  ;i++ ){
            String s = d.getDiretorio().get(i).getNome();
            if(parameters.equals(s)){
                atual = i;
                f = true;
                break;
            } 
        }
        if(parameters.equals("Raiz")){
            currentDir = "/";
            result = "Posicao atual: " + d.getNome();
            raiz = true;
        }else{
            if(f){
                currentDir = d.getDiretorio().get(atual).getCaminho();
                result = "Posicao atual: " + d.getDiretorio().get(atual).getNome();
                raiz = false;
            }else{
                result = "Diretorio ou Arquivo Inexistente";
            }
        }
        
        //setando parte gráfica do diretorio atual
        operatingSystem.fileSystem.FileSytemSimulator.currentDir = currentDir;

        //fim da implementacao do aluno
        return result;
    }

    public String rmdir(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: rmdir");
        System.out.println("\tParametros: " + parameters);
        //inicio da implementacao do aluno
        boolean f = false;
        int pos = 0;
        
        for(int i = 0; i < d.getDiretorio().size()  ;i++ ){
            String s = d.getDiretorio().get(i).getNome();
            if(parameters.equals(s)){
                pos = i;
                f = true;
                break;
            } 
        }
            if(f){
                if(atual == pos){
                    result = "Nao eh possivel excluir o diretorio atual";
                }else{
                    if(d.getDiretorio().get(pos).getFilho().isEmpty()){
                        int pai = 0;
                        for(int i = 0; i < d.getDiretorio().size()  ;i++ ){
                            String s = d.getDiretorio().get(i).getNome();
                            if(d.getDiretorio().get(pos).getPai().equals(s)){
                                pai = i;
                                System.out.println("Pai = " + i);
                                break;
                            } 
                        }
                        int filho = 0;
                        for(int i = 0; i < d.getDiretorio().get(pai).getFilho().size()  ;i++ ){
                            String s = d.getDiretorio().get(pos).getNome();
                            if(d.getDiretorio().get(pai).getPFilho(i).equals(s)){
                                filho = i;
                                System.out.println("Filho = " + i);
                                break;
                            } 
                        }
                        d.getDiretorio().get(pai).removeFilho(filho);
                        d.getDiretorio().remove(pos);
                        index--;
                        result = "Diretorio excluido com sucesso";
                    }else{  
                        result = "O Diretorio nao esta vazio";
                    }
                }
            }else{
            result = "Diretorio nao encontrado";
            } 
        

        //fim da implementacao do aluno
        return result;
    }

    public String cp(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: cp");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        //fim da implementacao do aluno
        return result;
    }

    public String mv(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: mv");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        String[] s = parameters.split(" ");
        boolean dirf = false;
        boolean dirp = false;
        boolean arqf = false;
        boolean arqp = false;
        int posf = 0;
        int posp = 0;
        for(int i = 0;i < d.getDiretorio().size();i++){
            if(s[0].equals(d.getDiretorio().get(i).getNome())){
                posf = i;
                dirf = true;
                break;
            }
        }
        if(dirf == false){
            for(int i = 0;i < a.size();i++){
                if(s[0].equals(a.get(i).getNome())){
                    posf = i;
                    arqf = true;
                    break;
                }
            }
        }
        if(dirf){
            for(int i = 0;i < d.getDiretorio().size();i++){
                if(s[1].equals(d.getDiretorio().get(i).getNome())){
                    posp = i;
                    dirp = true;
                    break;
                }
            }
            if(dirp){
                
            }else{
            result = "Arquivo ou Diretorio nao encontrado";
            }
        }
        else if(arqf){
            for(int i = 0;i < a.size();i++){
                if(s[0].equals(a.get(i).getNome())){
                    posp = i;
                    arqp = true;
                    break;
                }
            }
            if(arqp){
               d.getDiretorio().get(posf).setPai(d.getDiretorio().get(posp).getNome());
               d.getDiretorio().get(posf).setCaminho(d.getDiretorio().get(posp).getCaminho());
               for(int i = 0; i < d.getDiretorio().get(posf).getFilho().size();i++){
                   boolean f = false;
                   int p = 0;
                   String x = d.getDiretorio().get(posf).getPFilho(i);
                   for(int j = 0; j < d.getDiretorio().size();j++){
                       if(x.equals(d.getDiretorio().get(i).getNome())){
                           f = true;
                           p = i;
                           break;
                       }
                   }
               }
            }else{
            result = "Arquivo ou Diretorio nao encontrado";
            }
        }else{
            result = "Arquivo ou Diretorio nao encontrado";
        }
        //fim da implementacao do aluno
        return result;
    }

    public String rm(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: rm");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        //fim da implementacao do aluno
        return result;
    }

    public String chmod(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: chmod");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        String[] s = parameters.split(" ");
        boolean dir = false;
        boolean arq = false;
        int pos = 0;
        for(int i = 0; i < d.getDiretorio().size();i++){
            if(s[0].equals(d.getDiretorio().get(i).getNome())){
                dir = true;
                pos = i;
                break;
            }
        }
        for(int i = 0; i < a.size();i++){
            if(s[0].equals(a.get(i).getNome())||s[0].equals(a.get(i).getNome()+"."+a.get(i).getExtencao())){
                arq = true;
                pos = i;
                break;
            }
        }
        if(dir){
            d.getDiretorio().get(pos).setPermissao(Integer.parseInt(s[1]));
            result = "Permissao do diretorio alterada com sucesso";
            System.out.println(d.getDiretorio().get(pos).getPermissao());
        }
        else if(arq){
            a.get(pos).setPermissao(Integer.parseInt(s[1]));
            result = "Permissao do arquivo alterada com sucesso";
            System.out.println(a.get(pos).getPermissao());
        }else{
            result = "Diretorio ou Arquivo nao encontrado";
        }
        
        //fim da implementacao do aluno
        return result;
    }

    public String createfile(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: createfile");
        System.out.println("\tParametros: " + parameters);
        //inicio da implementacao do aluno
        boolean f1 = true;
        String[] s = parameters.split(" ");
        String[] e = s[0].split("\\.");
        for(int i = 0;i<a.size();i++){
            if(e[0] == a.get(i).getNome()){
                f1 = false;
                break;
            }
        }
        if(f1){
            if(raiz){
                result = "Nao eh possivel criar um arquivo a partir da Raiz";
            }else{
                String[] f = parameters.split(s[1]);
                String c = s[1];
                try{
                    c = s[1] + f[1];
                }catch(Exception ex){
                }
                //System.out.println(c);
                //System.out.println(c);
                //System.out.println(s[0] + "\n" + s[1] + "\n" + s[2]);
                //System.out.println(e[0]);
                //System.out.println(d.getDiretorio().get(atual).getNome());
                a.add(new Arquivo(d.getDiretorio().get(atual).getNome(),c, e[0], e[1], 0));
                d.getDiretorio().get(atual).criarArquivo(a.get(indexa));
                result = "Arquivo criado com sucesso";
            }
        }else{
            result = "Nome de Arquivo ja existente";
        }
        //fim da implementacao do aluno
        return result;
    }

    public String cat(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: cat");
        System.out.println("\tParametros: " + parameters);
        for(int i = 0; i < a.size(); i++){
            if(a.get(i).getNome().equals(parameters)){
                result = a.get(i).getConteudo();
                break;
            }
        }
        System.out.println(result);
        //inicio da implementacao do aluno
        //fim da implementacao do aluno
        return result;
    }

    public String batch(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: batch");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        //fim da implementacao do aluno
        return result;
    }

    public String dump(String parameters) {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: dump");
        System.out.println("\tParametros: " + parameters);

        //inicio da implementacao do aluno
        //fim da implementacao do aluno
        return result;
    }

    public String info() {
        //variavel result deverah conter o que vai ser impresso na tela apos comando do usuário
        String result = "";
        System.out.println("Chamada de Sistema: info");
        System.out.println("\tParametros: sem parametros");

        //nome do aluno
        String name = "Pedro Henrique de Oliveira Piva";
        //numero de matricula
        String registration = "202011020025";
        //versao do sistema de arquivos
        String version = "0.5";

        result += "Nome do Aluno:        " + name;
        result += "\nMatricula do Aluno:   " + registration;
        result += "\nVersao do Kernel:     " + version;

        return result;
    }

}
