package fr.mbds.poi

class Poi {
    String nom
    User user
    String adresse
    double lng
    double lat
    String description

    static hasMany = [groupes:Groupe,images:Image]
    static belongsTo = [Groupe]

    static constraints = {
        nom blank: false, unique: true
    }
}
