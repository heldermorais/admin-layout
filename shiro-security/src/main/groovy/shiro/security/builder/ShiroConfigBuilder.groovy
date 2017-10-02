package shiro.security.builder

import shiro.security.ShiroApp

class ShiroConfigBuilder {

    ShiroApp app = null


    private ShiroConfigBuilder(){

    }

    public static ShiroConfigBuilder define(){
        return new ShiroConfigBuilder()
    }

    public ShiroConfigBuilder newApp ( String nome, String sigla, String descricao=null, String icone=null){

        if (this.app == null){
            this.app = new ShiroApp()
        }
        this.app.nome = nome
        this.app.sigla = sigla
        this.app.descricao= descricao
        this.app.icone=icone

        return this

    }
}
