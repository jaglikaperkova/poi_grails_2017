package fr.mbds.poi

import grails.converters.JSON
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

    def containsImage(Image image){
        List<Poi> listPoi = Poi.list()
        for(int i=0;i<listPoi.size();i++){
            if(listPoi.images.contains(image)){
                println(listPoi.get(i).nom+" "+"contains img "+image.name)
            }
        }

    }
    //Precondition: Unicite des images
    @Transactional
    @Secured(['ROLE_MODERATOR', 'ROLE_ADMIN'])
    def delete(Image image) {
        String groupesPath =""
        String poisPath=""
        if (image == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        Poi p;
        Groupe g;
        List<Poi> myPois=Poi.list()
        List<Groupe> myGroupes = Groupe.list()
        //delete from the server
        //if image.id belongs to a poi then path is to poi
        for(int i=0;i<myPois.size();i++){
            if(myPois.get(i).images.contains(image)){
                p=myPois.get(i)
                println(myPois.get(i).nom+" "+"contains img "+image.name)
                poisPath=grailsApplication.config.images.pois.path
                break
            }

            /*
            for(int j=0;j<Poi.list().get(i).images.size();j++){
                if(image.name.compareTo(Poi.list().get(i).images.getAt(j).name)){
                    poisPath=grailsApplication.config.images.pois.path
                    println("Image found in POI: "+Poi.list().get(i).nom)
                    break
                }
            }*/

        }
        for(int j=0;j<myGroupes.size();j++){
            if(myGroupes.get(j).images.contains(image)){
                g=myGroupes.get(j)
                println(myGroupes.get(j).nom+" "+"contains img "+image.name)
                groupesPath=grailsApplication.config.images.groupes.path
                break
            }
        }

        //if image.id belongs to a groupe then path is to groupe
        /*for(int i=0;i<Groupe.list().size();i++){
            for(int j=0;j<Groupe.list().get(i).images.size();j++){
                if(image.name.compareTo(Groupe.list().get(i).images.getAt(j).name)){
                    groupesPath=grailsApplication.config.images.groupes.path
                    println("Image found in GROUPE: "+Groupe.list().get(i).nom)
                    break
                }
            }
        }*/
        println("Pois: "+poisPath)
        println("Groupes :"+groupesPath)

        if(!poisPath.isEmpty()){
            new File(poisPath + image.name).delete()
            p.images.remove(image)
        }
        else if(!groupesPath.isEmpty()){
            new File(groupesPath + image.name).delete()
            g.images.remove(image)
        }


        //delete from gorm

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
