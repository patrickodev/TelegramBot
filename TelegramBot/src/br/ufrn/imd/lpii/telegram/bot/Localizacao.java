package br.ufrn.imd.lpii.telegram.bot;

import java.util.HashSet;
import java.util.Set;

public class Localizacao {
    private String codigo;
    private String nome;
    private String descricao;
    private Set<Bem> bens;

    public Localizacao(){
        bens = new HashSet<Bem>();
    }    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getBens(){
        String list = "Código - Nome";
        for (Bem b:bens) {
            list = list.concat("\n"+b.getCodigo()+" - "+b.getNome());
        }
        return list;
    }
    
    public void addBem(Bem bem){
        bens.add(bem);
    }
}
