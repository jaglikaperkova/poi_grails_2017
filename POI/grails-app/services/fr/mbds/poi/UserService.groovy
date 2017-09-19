package fr.mbds.poi

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def serviceMethod(u,p) {
    }
    def createNewUser(String u, String p){
        def role = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)
        def user = new User(username: u,password: p).save(flush: true, failOnError: true)
        UserRole.create(user,role,true);
    }
}
