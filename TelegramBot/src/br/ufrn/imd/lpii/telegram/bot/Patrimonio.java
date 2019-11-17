package br.ufrn.imd.lpii.telegram.bot;

import java.util.HashMap;
import java.util.Map;

public class Patrimonio {
    Map<String,Localizacao> localizacaoList;
    Map<String,Categoria> categoriaList;
    Map<String, Bem> bemMap;
    
    Patrimonio(){
        localizacaoList = new HashMap<String,Localizacao>();
        categoriaList = new HashMap<String,Categoria>();
        bemMap = new HashMap<String, Bem>();

    }

    public void cadastrarLocalizacao(Localizacao localizacao){
        localizacao.setCodigo("loc0"+String.valueOf(localizacaoList.size()));
        localizacaoList.put(localizacao.getCodigo(),localizacao);
    }

    public void cadastrarCategoria(Categoria categoria){
        categoria.setCodigo("cat0"+String.valueOf(categoriaList.size()));
        categoriaList.put(categoria.getCodigo(),categoria);
    }

    public void cadastrarBem(Bem bem){
        bem.setCodigo("bem0"+String.valueOf(bemMap.size()));
        bem.getLocalizacao().addBem(bem);
        bemMap.put(bem.getCodigo(),bem);
    }

    public Localizacao getLocalizacao(String msg) {
        msg = msg.replace("/","");
        return localizacaoList.get(msg);
    }

    public Categoria getCategoria(String msg) {
        msg = msg.replace("/","");
        return categoriaList.get(msg);
    }

    public String listarLocalizacoes(){
        String list = "Código - Nome - Descrição";
        for (String c : localizacaoList.keySet()) {
            Localizacao l =localizacaoList.get(c);
            list = list.concat("\n/"+l.getCodigo()+" - "+l.getNome()+" - "+l.getDescricao());
        }
        return list;
    }

    public String listarCategorias(){
        String list = "Código - Nome - Descrição";
        for (String c : categoriaList.keySet()) {
            Categoria cat = categoriaList.get(c);
            list = list.concat("\n/"+cat.getCodigo()+" - "+cat.getNome()+" - "+cat.getDescricao());
        }
        return list;
    }


/*
    public Bem buscarBemPorCodigo(String codigo){
    }
    public Bem buscarBemPorNome(String nome){
    }
    public Bem buscarBemPorDescricao(){
    }
    public void movimentarBem(Localizacao outroLocal){
    }
    public String gerarRelatorio(){
    }
 */
}
