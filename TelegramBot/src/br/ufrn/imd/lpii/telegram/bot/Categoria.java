package br.ufrn.imd.lpii.telegram.bot;

public class Categoria{
    private String codigo;
    private String nome;
    private String descricao;

    public Categoria(){}

    public Categoria(String codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public void setCodigo(String cod) {
        codigo = cod;
    }

    public String getCodigo() {
        return codigo;
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

   
}
