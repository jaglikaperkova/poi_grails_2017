package fr.mbds.poi

class Groupe {
    String nom
    static hasMany = [pois:Poi, images:Image]

    static constraints = {
        nom blank: false, unique: true
    }
}
