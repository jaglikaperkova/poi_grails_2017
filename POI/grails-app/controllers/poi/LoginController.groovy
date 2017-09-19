package poi

import fr.mbds.poi.User
import fr.mbds.poi.UserService
import grails.gorm.transactions.Transactional
import org.springframework.security.core.context.SecurityContextHolder

@Transactional(readOnly = true)
class LoginController {
    def springSecurityService
    // Bean where Spring Security store logout handlers
    def logoutHandlers
    def passwordEncoder
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def index() { }
    def login(){

        if(springSecurityService.isLoggedIn() /*.currentUser!=null*/){
            println("logged in")
            println(springSecurityService.authentication.name)
            redirect(uri:"/user/show/" + springSecurityService.currentUser.id)

        }
    }
    def loginuser(){
        def usrn=params.get("j_username")
        def paswd=params.get("j_password")
        User u = User.findWhere(username: usrn)
        if(u!=null){
            println("found user in db")

            //making the current user
            springSecurityService.reauthenticate(usrn,paswd)
            redirect(uri:"/user/show/" + u.id)
        }
        else {
            println("no user in db")
            //new user
            /*UserService.createNewUser(usrn,paswd)
            springSecurityService.reauthenticate(usrn,paswd)*/
            redirect(uri:"/")
        }
    }

    def logout(){
        /*// Logout programmatically
        Authentication auth = SecurityContextHolder.context.authentication
    if (auth) {
        logoutHandlers.each  { handler->
            handler.logout(request,response,auth)
        }
    }*/
        SecurityContextHolder.clearContext()
        redirect(uri: "/")
    }


}
