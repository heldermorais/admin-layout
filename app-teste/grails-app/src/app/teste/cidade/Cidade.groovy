package app.teste.cidade

import grails.persistence.Entity


@Entity
class Cidade {

    String nome

    static constraints = {
        nome unique: true, nullable: false
    }

    @Override
    String toString() {
        return nome
    }

}
