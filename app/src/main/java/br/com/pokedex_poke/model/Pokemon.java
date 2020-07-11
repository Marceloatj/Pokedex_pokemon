package br.com.pokedex_poke.model;

import br.com.pokedex_poke.dao.generic.ClassBinding;
import br.com.pokedex_poke.dao.generic.FieldBinding;


@ClassBinding(value = "POKEMON")
public class Pokemon {

    @FieldBinding(value = "NOME")
    private String NOME;
    @FieldBinding(value = "NUMERO")
    private String NUMERO;
    @FieldBinding(value = "SORTUDO")
    private String SORTUDO;
    @FieldBinding(value = "SHINY")
    private String SHINY;
    @FieldBinding(value = "SOMBROSO")
    private String SOMBROSO;
    @FieldBinding(value = "PURIFICADO")
    private String PURIFICADO;
    @FieldBinding(value = "POKEMON_100")
    private String POKEMON_100;
    @FieldBinding(value = "POKEMON_0")
    private String POKEMON_0;
    @FieldBinding(value = "MACHO")
    private String MACHO;
    @FieldBinding(value = "FEMEA")
    private String FEMEA;
    @FieldBinding(value = "REGIAO")
    private String REGIAO;

    public String getREGIAO() {
        return REGIAO;
    }

    public void setREGIAO(String REGIAO) {
        this.REGIAO = REGIAO;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getSORTUDO() {
        return SORTUDO;
    }

    public void setSORTUDO(String SORTUDO) {
        this.SORTUDO = SORTUDO;
    }

    public String getSHINY() {
        return SHINY;
    }

    public void setSHINY(String SHINY) {
        this.SHINY = SHINY;
    }

    public String getSOMBROSO() {
        return SOMBROSO;
    }

    public void setSOMBROSO(String SOMBROSO) {
        this.SOMBROSO = SOMBROSO;
    }

    public String getPURIFICADO() {
        return PURIFICADO;
    }

    public void setPURIFICADO(String PURIFICADO) {
        this.PURIFICADO = PURIFICADO;
    }

    public String getPOKEMON_100() {
        return POKEMON_100;
    }

    public void setPOKEMON_100(String POKEMON_100) {
        this.POKEMON_100 = POKEMON_100;
    }

    public String getPOKEMON_0() {
        return POKEMON_0;
    }

    public void setPOKEMON_0(String POKEMON_0) {
        this.POKEMON_0 = POKEMON_0;
    }

    public String getMACHO() {
        return MACHO;
    }

    public void setMACHO(String MACHO) {
        this.MACHO = MACHO;
    }

    public String getFEMEA() {
        return FEMEA;
    }

    public void setFEMEA(String FEMEA) {
        this.FEMEA = FEMEA;
    }
}
