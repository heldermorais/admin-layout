package app.teste.pessoa

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PessoaController {

    PessoaService pessoaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pessoaService.list(params), model:[pessoaCount: pessoaService.count()]
    }

    def show(Long id) {
        respond pessoaService.get(id)
    }

    def create() {
        respond new Pessoa(params)
    }

    def save(Pessoa pessoa) {
        if (pessoa == null) {
            notFound()
            return
        }

        try {
            pessoaService.save(pessoa)
        } catch (ValidationException e) {
            respond pessoa.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoa.id])
                redirect pessoa
            }
            '*' { respond pessoa, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pessoaService.get(id)
    }

    def update(Pessoa pessoa) {
        if (pessoa == null) {
            notFound()
            return
        }

        try {
            pessoaService.save(pessoa)
        } catch (ValidationException e) {
            respond pessoa.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoa.id])
                redirect pessoa
            }
            '*'{ respond pessoa, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pessoaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
