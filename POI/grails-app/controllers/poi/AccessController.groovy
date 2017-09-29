package poi

import grails.converters.JSON

class AccessController {

    def springSecurityService

    def index() {

        if(springSecurityService.getPrincipal().authorities.contains('ROLE_ADMIN'))
            render text: "Is admin"
        else if(springSecurityService.getPrincipal().authorities.contains('ROLE_MODERATOR'))
            render text: "Is moderator"
        else if(springSecurityService.getPrincipal().authorities.contains('ROLE_USER'))
            render text: "Is user"

    }
}
