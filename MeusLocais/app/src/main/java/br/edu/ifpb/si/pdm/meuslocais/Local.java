package br.edu.ifpb.si.pdm.meuslocais;

/**
 * Created by home on 16/06/2016.
 */
public class Local {
    private String nome;
    private String descricao;
    private String foto;
    private String local;

    public Local(String nome){
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFoto() {
        return foto;
    }

    public String getLocal() {
        return local;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public String toString(){
        return this.getNome();
    }
}
