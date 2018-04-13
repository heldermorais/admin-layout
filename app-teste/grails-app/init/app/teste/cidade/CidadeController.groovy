package app.teste.cidade

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CidadeController {

    CidadeService cidadeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond cidadeService.list(params), model:[cidadeCount: cidadeService.count()]
    }

    def show(Long id) {
        respond cidadeService.get(id)
    }

    def create() {
        respond new Cidade(params)
    }

    def save(Cidade cidade) {
        if (cidade == null) {
            notFound()
            return
        }

        try {
            cidadeService.save(cidade)
        } catch (ValidationException e) {
            respond cidade.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cidade.label', default: 'Cidade'), cidade.id])
                redirect cidade
            }
            '*' { respond cidade, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond cidadeService.get(id)
    }

    def update(Cidade cidade) {
        if (cidade == null) {
            notFound()
            return
        }

        try {
            cidadeService.save(cidade)
        } catch (ValidationException e) {
            respond cidade.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cidade.label', default: 'Cidade'), cidade.id])
                redirect cidade
            }
            '*'{ respond cidade, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cidadeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cidade.label', default: 'Cidade'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cidade.label', default: 'Cidade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
