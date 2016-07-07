package br.edu.ifpb.si.pdm.meuslocais;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 16/06/2016.
 */
public class CadastroLocal {
    private List<Local> locais;

    public CadastroLocal() {
        this.locais = new ArrayList<Local>();
        this.insere();
    }

    private void insere(){

        this.locais.add(new Local("local"));
    }

    public List<Local> get(){
        return this.locais;
    }

    public int quantidade(){
        return this.locais.size();
    }

    public void clear(){
        this.locais.clear();
    }

    public void delete(int pos){
        this.locais.remove(pos);
    }
}
