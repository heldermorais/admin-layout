package app.teste.pessoa

import app.teste.cidade.Cidade
import grails.persistence.Entity



@Entity
class Pessoa {

    String nome
    String email
    Date   dataNascimento

    Cidade cidade

    static constraints = {
        nome nullable: false, blank: false
        email nullable:true
        dataNascimento nullable:true
    }
}
