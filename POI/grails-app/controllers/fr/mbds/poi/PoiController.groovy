package fr.mbds.poi

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
@Secured(['isAuthenticated()'])
class PoiController {

    static allowedMethods = [save: "POST", update: ["POST","PUT"], delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Poi.list(params), model:[poiCount: Poi.count()]
    }

    def show(Poi poi) {
        respond poi
    }

    def list(){
        respond Poi.list()
    }

    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def create() {
        respond new Poi(params)
    }

    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def save(Poi poi) {
        if (poi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (poi.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond poi.errors, view:'create'
            return
        }

        if(params.containsKey('uploadFile')){
            List files = request.getFiles('uploadFile')
            files.each {f->
                if(!f.empty){
                    def nom = f.getOriginalFilename()
                    poi.addToImages(new Image(name: nom,url: nom))
                    f.transferTo(new File(grailsApplication.config.images.pois.path + nom))
                }
            }
        }

        poi.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'poi.label', default: 'Poi'), poi.id])
                redirect poi
            }
            '*' { respond poi, [status: CREATED] }
        }
    }

    def edit(Poi poi) {
        respond poi
    }

    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def update(Poi poi) {
        if (poi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (poi.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond poi.errors, view:'edit'
            return
        }

        if(params.containsKey('uploadFile')){
            def file = request.getFile('uploadFile')
            println(file)
            //files.each {f->
                if(!file.empty){
                    def nom = file.getOriginalFilename()
                    poi.addToImages(new Image(name: nom,url: nom))
                    file.transferTo(new File(grailsApplication.config.images.pois.path + nom))
                }
            //}
        }

        poi.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'poi.label', default: 'Poi'), poi.id])
                redirect poi
            }
            '*'{ respond poi, [status: OK] }
        }
    }

    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def delete(Poi poi) {

        if (poi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        poi.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'poi.label', default: 'Poi'), poi.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'poi.label', default: 'Poi'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
