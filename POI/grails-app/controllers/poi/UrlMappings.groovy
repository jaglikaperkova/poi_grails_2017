package poi

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/#\\"(view:"/index")
        "/login"(controller:"login",action: "login")
       "/"(view: "/index")
        //"/"(controller: "login", action: "login")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
