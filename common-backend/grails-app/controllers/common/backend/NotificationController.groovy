package common.backend

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class NotificationController {

    //NotificationService notificationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Notification.list(params), model:[notificationCount: Notification.count()]
    }

    def show(Long id) {
        respond Notification.get(id)
    }

    def create() {
        respond new Notification(params)
    }

    def save(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            Notification.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', 
                                        args: [
                                                message(code: 'notification.label', default: 'Notification'), 
                                                notification.id
                                              ]
                                       )
                                       
                redirect notification
            }
            '*' { respond notification, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond Notification.get(id)
    }

    def update(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            Notification.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', 
                                        args: [
                                               message(code: 'notification.label', default: 'Notification'), 
                                               notification.id
                                              ]
                                       )
                                       
                redirect notification
            }
            '*'{ respond notification, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Notification.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'notification.label', default: 'Notification'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'notification.label', default: 'Notification'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
