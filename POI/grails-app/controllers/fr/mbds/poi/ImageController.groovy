package fr.mbds.poi

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional

@Transactional (readOnly = true)
@Secured(['isAuthenticated()'])
class ImageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Image.list(params), model:[imageCount: Image.count()]
    }

    def show(Image image) {
        respond image
    }

    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def create() {
        respond new Image(params)
    }

    def list(){
        respond Image.list()
    }
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def uploadImage(){
        def file=request.getFile('uploadFile')
        def nom = file.getOriginalFilename();
        Image i = new Image(name: nom, url: nom).save(flush:true,failOnError:true)

        file.transferTo(new File(grailsApplication.config.images.test.path + nom))
        println(params.list())

    }
    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def save(Image image) {
        if (image == null) {
            //transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (image.hasErrors()) {
            //transactionStatus.setRollbackOnly()
            respond image.errors, view:'create'
            return
        }

        image.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'image.label', default: 'Image'), image.id])
                redirect image
            }
            '*' { respond image, [status: CREATED] }
        }
    }

    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def edit(Image image) {
        respond image
    }

    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def update(Image image) {
        if (image == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (image.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond image.errors, view:'edit'
            return
        }

        image.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'image.label', default: 'Image'), image.id])
                redirect image
            }
            '*'{ respond image, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def delete(Image image) {

        if (image == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        image.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'image.label', default: 'Image'), image.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'image.label', default: 'Image'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
