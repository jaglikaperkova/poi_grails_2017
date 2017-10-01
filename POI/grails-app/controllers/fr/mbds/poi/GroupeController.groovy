package fr.mbds.poi

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
//import grails.transaction.Transactional
import grails.gorm.transactions.Transactional


@Transactional(readOnly = true)
@Secured(['isAuthenticated()'])
class GroupeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Groupe.list(params), model:[groupeCount: Groupe.count()]
    }

    def show(Groupe groupe) {
        respond groupe
    }

    def list(){
        respond Groupe.list()
    }

    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def create() {
        respond new Groupe(params)
    }

    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def save(Groupe groupe) {
        if (groupe == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (groupe.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond groupe.errors, view:'create'
            return
        }

        if(params.containsKey('uploadFile')){
            def file = request.getFile('uploadFile')
            println(file)
            //files.each {f->
            if(!file.empty){
                def nom = file.getOriginalFilename()
                groupe.addToImages(new Image(name: nom,url: nom))
                file.transferTo(new File(grailsApplication.config.images.groupes.path + nom))
            }
            //}
        }
        groupe.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'groupe.label', default: 'Groupe'), groupe.id])
                redirect groupe
            }
            '*' { respond groupe, [status: CREATED] }
        }
    }

    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def edit(Groupe groupe) {
        respond groupe
    }

    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def update(Groupe groupe) {
        if (groupe == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (groupe.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond groupe.errors, view:'edit'
            return
        }
        if(params.containsKey('uploadFile')){
            def file = request.getFile('uploadFile')
            println(file)
            //files.each {f->
            if(!file.empty){
                def nom = file.getOriginalFilename()
                groupe.addToImages(new Image(name: nom,url: nom))
                file.transferTo(new File(grailsApplication.config.images.groupes.path + nom))
            }
            //}
        }

        groupe.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'groupe.label', default: 'Groupe'), groupe.id])
                redirect groupe
            }
            '*'{ respond groupe, [status: OK] }
        }
    }


    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def delete(Groupe groupe) {

        if (groupe == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        for(int i=0;i<groupe.pois.size();i++){
            groupe.pois.remove(i)
        }
        Set<Image> images = groupe.images
        for(int j=0;j<images.size();j++){
            images.getAt(j).delete()
        }
        groupe.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'groupe.label', default: 'Groupe'), groupe.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'groupe.label', default: 'Groupe'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
