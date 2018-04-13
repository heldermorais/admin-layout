package app.teste.pessoa

import grails.persistence.Entity
import app.teste.cidade.Cidade


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
