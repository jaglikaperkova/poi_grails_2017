package poi

import fr.mbds.poi.Groupe
import fr.mbds.poi.Image
import fr.mbds.poi.Poi
import fr.mbds.poi.Role
import fr.mbds.poi.User
import fr.mbds.poi.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminrole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
        def adminuser = new User(username: 'admin',password: 'password').save(flush: true, failOnError: true)
        UserRole.create(adminuser,adminrole,true);

        def moderatorrole = new Role(authority: 'ROLE_MODERATOR').save(flush: true, failOnError: true)
        def moderatoruser2 = new User(username: 'moderator',password: 'password').save(flush: true, failOnError: true)
        UserRole.create(moderatoruser2,moderatorrole,true);

        def adminrole2 = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)
        def adminuser2 = new User(username: 'user',password: 'password').save(flush: true, failOnError: true)
        UserRole.create(adminuser2,adminrole2,true);

        def groupeInstance1 = new Groupe(nom:"Monuments").addToImages(new Image(name: "paris_galeries.jpg",url: "groupes"))
        def groupeInstance2 = new Groupe(nom:"Batiments").addToImages(new Image(name:"lyon_hoteldeville.jpg", url: "groupes"))
        groupeInstance1.addToPois(new Poi(nom:"KFC Monument", user: adminuser, adresse: "11 Route des dolines", lat: 15, long:-10, description: "KFC est nouveau grand monu")
                .addToImages(new Image(name: "kfc_sa_hed_2016.jpg",url: "pois")))
        groupeInstance1.save(flush: true, failOnError : true)
        groupeInstance2.addToPois(new Poi(nom:"Golf St Philippe", user: moderatoruser2, adresse: "25 St Philippe Carrefour", lat: -22, long:18, description: "Ce golf est nouveau et permet lot of fun ")
                .addToImages(new Image(name: "golfPark.jpg", url: "pois")))
        groupeInstance2.save(flush: true, failOnError : true)
       /* int inx=0
        (1..5).each {
            int groupIndex ->
                def groupeInstance = new Groupe(nom: "Groupe Pois "+groupIndex.toString()).addToImages(new Image(name: "Koala.jpg",url: "koala.jpg"))
                (1..5).each {
                    int poiIndex ->
                        groupeInstance.addToPois( new Poi(nom: "Poi int "+inx, user:adminuser,  adresse: poiIndex+ ", Route de Colles",lat: 10,lng: 10,description: "description ici "+poiIndex)
                                .addToImages(new Image(name: "Koala"+poiIndex+".jpg",url: "koala"+poiIndex+".jpg")))
                        inx=inx+1
                }
                groupeInstance.save(flush: true, failOnError : true)
        }*/
    }
    def destroy = {
    }
}