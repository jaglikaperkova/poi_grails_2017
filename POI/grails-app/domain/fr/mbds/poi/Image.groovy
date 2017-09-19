package fr.mbds.poi

class Image {
    String name
    String url
    static constraints = {
        name blank: false
        url blank: false

    }
}
