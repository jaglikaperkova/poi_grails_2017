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

        def monuments = new Groupe(nom:"Monuments").addToImages(new Image(name: "monuments.jpg",url: "groupes"))
        def batiments = new Groupe(nom:"Batiments").addToImages(new Image(name:"batiments.jpg", url: "groupes"))
        def parcs = new Groupe(nom:"Parcs").addToImages(new Image(name: "parcs.jpg",url: "groupes"))
        def restaurants = new Groupe(nom:"Restaurants").addToImages(new Image(name:"restaurants.jpg", url: "groupes"))
        def villes = new Groupe(nom:"Villes").addToImages(new Image(name:"cities.png", url: "groupes"))

        //monuments:
        // Statue of Liberty; New York, NY 10004, USA; lat:40.6892494, lng:-74.0445004; Statue of liberty.jpg
        // Eiffel Tower; Champ de Mars, 5 Avenue Anatole France, 75007 Paris; lat:48.8583701, lng:2.2944813; eiffel-tower.jpg
        // Big Ben; Westminster, London SW1A 0AA, UK; lat:51.499894, lng:-0.1514193; Big-Ben.jpg
        // Empire State Building; 350 5th Ave, New York, NY 10118, USA; lat:40.7338085, lng:-74.005148; emp_state_building.jpg
        // Colosseum; Piazza del Colosseo, 1, 00184 Roma RM, Italy; lat:41.8902102, lng:12.4922309; colosseum-rome.jpg

        monuments.addToPois(new Poi(nom:"Statue of Liberty", user: adminuser, adresse: "New York, NY 10004, USA", lat: 40.6892494, lng: -74.0445004, description: "The Statue of Liberty is a colossal neoclassical sculpture on Liberty Island in New York Harbor in New York City, in the United States.")
                .addToImages(new Image(name: "Statue of liberty.jpg",url: "pois")))
        monuments.addToPois(new Poi(nom: "Eiffel Tower", user: adminuser, adresse: "Champ de Mars, 5 Avenue Anatole France, 75007 Paris",lat: 48.8583701,lng: 2.2944813,description: "The Eiffel Tower is a wrought iron lattice tower on the Champ de Mars in Paris, France. It is named after the engineer Gustave Eiffel, whose company designed and built the tower."))
                .addToImages(new Image(name: "eiffel-tower.jpg", url: "pois"))
        monuments.addToPois(new Poi(nom: "Big Ben", user: adminuser, adresse: "London SW1A 0AA, UK",lat: 51.499894,lng: -0.1514193,description: "Big Ben is the nickname for the Great Bell of the clock at the north end of the Palace of Westminster in London and is usually extended to refer to both the clock and the clock tower as well."))
                .addToImages(new Image(name: "Big-Ben.jpg", url: "pois"))
        monuments.addToPois(new Poi(nom: "Empire State Building", user: adminuser, adresse: "350 5th Ave, New York, NY 10118, USA",lat: 40.7338085,lng: -74.005148,description: "The Empire State Building is a 102-story skyscraper located on Fifth Avenue between West 33rd and 34th Streets in Midtown, Manhattan, New York City."))
                .addToImages(new Image(name: "emp_state_building.jpg", url: "pois"))
        monuments.addToPois(new Poi(nom: "Colosseum", user: adminuser, adresse: "Piazza del Colosseo, 1, 00184 Roma RM, Italy",lat: 41.8902102,lng: 12.4922309,description: "The Colosseum or Coliseum, also known as the Flavian Amphitheatre, is an oval amphitheatre in the centre of the city of Rome, Italy. Built of concrete and sand, it is the largest amphitheatre ever built."))
                .addToImages(new Image(name: "colosseum-rome.jpg", url: "pois"))
        monuments.save(flush: true, failOnError : true)

        //batiments:
        //Parthenon; Athens 105 58, Greece; lat:37.9715, lng:23.7267; parthenon.jpg
        //Burj Khalifa; 1 Sheikh Mohammed bin Rashid Blvd - Dubai - United Arab Emirates; lat:‎25.197525, lng:‎55.274288; Burj-Khalifa.jpg
        //Dubai Mall; Financial Centre Road, Downtown Dubai - Dubai - United Arab Emirates; lat:25.199514, lng:55.277397; dubai_mall.jpg
        //Shanghai Tower; 501 Yincheng Middle Rd, LuJiaZui, China; lat:31.2335, lng:121.5058; Shanghai Tower.jpg
        //Aqua;  225 N Columbus Dr #220, Chicago, IL 60601, USA; lat:41.8865, lng:87.6201; aqua.jpg
        batiments.addToPois(new Poi(nom:"Parthenon", user: moderatoruser2, adresse: "Athens 105 58, Greece", lat: 37.9715, lng: 23.7267, description: "The Parthenon is a former temple, on the Athenian Acropolis, Greece, dedicated to the goddess Athena, whom the people of Athens considered their patron. Construction began in 447 BC when the Athenian Empire was at the peak of its power.")
                .addToImages(new Image(name: "parthenon.jpg", url: "pois")))
        batiments.addToPois(new Poi(nom: "Burj Khalifa", user: moderatoruser2, adresse: "1 Sheikh Mohammed bin Rashid Blvd - Dubai - United Arab Emirates",lat: 25.199514,lng: 55.277397,description: "The Burj Khalifa, known as the Burj Dubai before its inauguration, is a megatall skyscraper in Dubai, United Arab Emirates."))
                .addToImages(new Image(name: "Burj-Khalifa.jpg", url: "pois"))
        batiments.addToPois(new Poi(nom: "Dubai Mall", user: moderatoruser2, adresse: "Financial Centre Road, Downtown Dubai - Dubai - United Arab Emirates",lat: 199514,lng: 55.277397,description: "The Dubai Mall is a shopping mall in Dubai and the largest mall in the world by total area. It is the nineteenth largest shopping mall in the world by gross leasable area."))
                .addToImages(new Image(name: "dubai_mall.jpg", url: "pois"))
        batiments.addToPois(new Poi(nom: "Shanghai Tower", user: moderatoruser2, adresse: "501 Yincheng Middle Rd, LuJiaZui, China",lat: 31.2335,lng: 121.5058,description: "The Shanghai Tower is a 632-metre, 128-story megatall skyscraper in Lujiazui, Pudong, Shanghai. As of 2015, it is the world's tallest building, by height to highest usable floor."))
                .addToImages(new Image(name: "Shanghai Tower.jpg", url: "pois"))
        batiments.addToPois(new Poi(nom: "Aqua", user: moderatoruser2, adresse: "225 N Columbus Dr 220, Chicago, IL 60601, USA",lat: 41.8865,lng: 87.6201,description: "Aqua is an 82-story mixed-use residential skyscraper in the Lakeshore East development in downtown Chicago, Illinois."))
                .addToImages(new Image(name: "aqua.jpg", url: "pois"))
        batiments.save(flush: true, failOnError : true)

        //parcs
        //Parc Du Paradou; boulevard des 1926 Boulevard des Horizons, 06220 Vallauris; lat:43.5606,lng:7.0581; parc paradou.jpg
        //Parc naturel d'Armorique; 15 Place aux Foires, 29590 Le Faou; lat:48.2935, lng:4.1781; parc armorique.jpg
        // Disneyland Paris; Boulevard du Parc 77700 Serris/Coupvray , France; lat:48.8693, lng:2.8043; disneyland.png
        //Prater ; Verein Prater Aktiv, Prater 7 A-1020 Vienna , Austria; lat:48.2135, lng:16.4254; prater.png
        // Drayton Manor Theme Park; Drayton Manor Dr, Mile Oak, Tamworth B78 3TW, England; lat:52.610833, lng: -1.713333; drayton.png

        parcs.addToPois(new Poi(nom: "Parc Du Paradou", user: adminuser, adresse: "1926 Boulevard des Horizons, 06220 Vallauris",lat: 43.5606,lng: 7.0581,description: "Le Parc naturel départemental du Massif du Paradou, d'une surface de 12 hectares, est situé sur la commune de Vallauris Golfe-Juan."))
                .addToImages(new Image(name: "parc paradou.jpg", url: "pois"))
        parcs.addToPois(new Poi(nom: "Parc naturel d'Armorique", user: adminuser, adresse: "15 Place aux Foires, 29590 Le Faou",lat: 48.2935,lng: 4.1781,description: "The Parc naturel régional d'Armorique, or Armorica Regional Natural Park, is a rural protected area located in Brittany. The park land reaches from the Atlantic Ocean to hilly inland countryside."))
                .addToImages(new Image(name: "parc armorique.jpg", url: "pois"))
        parcs.addToPois(new Poi(nom: "Disneyland Paris", user: adminuser, adresse: "Boulevard du Parc 77700 Serris Coupvray , France",lat: 48.8693,lng: 2.8043,description: "Disneyland Paris, originally Euro Disney Resort, is an entertainment resort in Marne-la-Vallée, a new town located 32 km east of the centre of Paris, and is the most visited theme park in all of Europe."))
                .addToImages(new Image(name: "disneyland.png", url: "pois"))
        parcs.addToPois(new Poi(nom: "Prater", user: adminuser, adresse: "Verein Prater Aktiv, Prater 7 A-1020 Vienna, Austria",lat: 48.2135,lng: 16.4254,description: "The Prater is a large public park in Vienna's 2nd district. The Wurstelprater amusement park, often simply called \"Prater\", lies in one corner of the Wiener Prater and includes the Wiener Riesenrad Ferris wheel."))
                .addToImages(new Image(name: "prater.png", url: "pois"))
        parcs.addToPois(new Poi(nom: "Drayton Manor Theme Park", user: adminuser, adresse: "Drayton Manor Dr, Mile Oak, Tamworth B78 3TW, England",lat: 52.610833,lng: -1.713333,description: "Drayton Manor is a theme park resort and zoo in the grounds of the former Drayton Manor, in Drayton Bassett, Staffordshire, England, UK. The park covers 280 acres of which about 113 acres are in use, and hosts about 1.5 million people each year."))
                .addToImages(new Image(name: "drayton.png", url: "pois"))
        parcs.save(flush: true, failOnError : true)


        //restaurants
        //Domino's;  473 Roman Rd, London E3 5LX, UK; lat:51.532236, lng:-0.034098; dominos.jpg
        //Eleven Madison Park; 11 Madison Ave, New York, NY 10010, USA; lat:40.7415114, lng:-73.9869677; eleven-madison-park.jpg
        //Alinea; Chicago; 1723 N Halsted St, Chicago, IL 60614, USA; lat:41.9134555, lng:-87.648164; Alinea.jpg
        //Osteria Francescana; Via Stella, 22, 41121 Modena MO, Italy; lat:44.644794, lng:10.921589; Osteria_francescana.jpg
        // Noma; Strandgade 93, 1401 København K, Denmark; lat:55.677903, lng:12.5941113; Noma_entrance.jpg

        restaurants.addToPois(new Poi(nom: "Domino s pizza", user: moderatoruser2, adresse: "473 Roman Rd, London E3 5LX, UK",lat: 51.532236,lng: -0.034098,description: "Delivery carryout chain offering a wide range of pizza, plus chicken & other sides."))
                .addToImages(new Image(name: "dominos.jpg", url: "pois"))
        restaurants.addToPois(new Poi(nom: "Eleven Madison Park", user: moderatoruser2, adresse: "11 Madison Ave, New York, NY 10010, USA",lat: 40.7415114,lng: -73.9869677 ,description: "Upscale American tasting menus from chef Daniel Humm served in a high-ceilinged art deco space."))
                .addToImages(new Image(name: "eleven-madison-park.jpg", url: "pois"))
        restaurants.addToPois(new Poi(nom: "Alinea", user: moderatoruser2, adresse: "1723 N Halsted St, Chicago, IL 60614, USA",lat: 41.9134555,lng: -87.648164,description: "Chef Grant Achatz draws foodies with New American tasting menus featuring highly creative plates."))
                .addToImages(new Image(name: "Alinea.jpg", url: "pois"))
        restaurants.addToPois(new Poi(nom: "Osteria Francescana", user: moderatoruser2, adresse: "Via Stella, 22, 41121 Modena MO, Italy",lat:44.644794,lng: 10.921589,description: "Osteria Francescana is a restaurant owned and run by chef Massimo Bottura in Modena, Italy."))
                .addToImages(new Image(name: "Osteria_francescana.jpg", url: "pois"))
        restaurants.addToPois(new Poi(nom: "Noma", user: moderatoruser2, adresse: "Strandgade 93, 1401 København K, Denmark",lat: 55.677903,lng: 12.5941113,description: "Noma is a two-Michelin-star restaurant run by chef René Redzepi in Copenhagen, Denmark."))
                .addToImages(new Image(name: "Noma_entrance.jpg", url: "pois"))
        restaurants.save(flush: true, failOnError : true)


        //villes
        //Veles; Veles, Macedonia; lat:41.72, lng:21.793333; veles.jpg
        //Bangkok, Thailand; lat:13.6753, lng:100.9341; bangkok.jpg
        //London,UK; lat: 51.4574, lng: 0.3131; london.jpg
        //Paris, France; lat:48.8032, lng:2.7905; paris.jpg
        //Dubai; lat:25.1329, lng:55.7089; dubai.jpg
        // Lyon , France; lat:45.7043, lng:5.2817; lyon.png
        villes.addToPois(new Poi(nom: "Veles", user: adminuser, adresse: "Veles, Macedonia",lat: 41.72,lng: 21.793333,description: "Veles is a city in the central part of the Republic of Macedonia on the Vardar river. The city of Veles is the seat of Veles Municipality."))
                .addToImages(new Image(name: "veles.jpg", url: "pois"))
        villes.addToPois(new Poi(nom: "Bangkok", user: adminuser, adresse: "Bangkok, Thailand",lat: 13.6753,lng: 100.9341,description: "Bangkok, Thailand s capital, is a large city known for ornate shrines and vibrant street life.  "))
                .addToImages(new Image(name: "bangkok.jpg", url: "pois"))
        villes.addToPois(new Poi(nom: "London", user: adminuser, adresse: "London, UK",lat: 51.4574,lng: 0.3131,description: "London, the capital of England and the United Kingdom, is a 21st-century city with history stretching back to Roman times."))
                .addToImages(new Image(name: "london.jpg", url: "pois"))
        villes.addToPois(new Poi(nom: "Paris", user: adminuser, adresse: "Paris Ile de France, France",lat: 48.8032,lng: 2.7905,description: "Paris, France's capital, is a major European city and a global center for art, fashion, gastronomy and culture."))
                .addToImages(new Image(name: "paris.jpg", url: "pois"))
        villes.addToPois(new Poi(nom: "Dubai", user: adminuser, adresse: "Dubai Emirates",lat: 25.1329,lng: 55.7089,description: "Dubai is a city and emirate in the United Arab Emirates known for luxury shopping, ultramodern architecture and a lively nightlife scene."))
                .addToImages(new Image(name: "dubai.jpg", url: "pois"))
        villes.addToPois(new Poi(nom: "Lyon", user: adminuser, adresse: "Lyons, France",lat: 45.7043,lng: 5.2817,description: "Lyon, the capital city in France’s Auvergne-Rhône-Alpes region, sits at the junction of the Rhône and Saône rivers. "))
                .addToImages(new Image(name: "lyon.png", url: "pois"))
        villes.save(flush: true, failOnError : true)

    }
    def destroy = {
    }
}