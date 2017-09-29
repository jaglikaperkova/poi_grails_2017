package poi

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/login"(controller:"login",action: "login")
       "/"(view: "/index")
        "/image/uploadImage" (controller: "image",action: "uploadImage")
        //"/"(controller: "login", action: "login")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
