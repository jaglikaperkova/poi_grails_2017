<%@ page import="fr.mbds.poi.Poi" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Points d'interet</title>
    <asset:stylesheet src="jquery-3.2.1.min.js"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">



</head>
<body>


<div id="content">
    <sec:ifNotLoggedIn>
        <h2>Please log in</h2>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
        <h2>Welcome, you can consult the groups and pois</h2>
        <div class="index">
        <nav class="navbar nav_groupe">
            <g:each in="${fr.mbds.poi.Groupe.list()}" var="g">
                <details  class="groupe" data-category="${g.id}">
                    <summary class="title-groupe">${g.nom} </summary>
                    <ul class="list-pois">
                        <g:each in="${g.pois}" var="poi">
                            <li class="poi" draggable="true" data-poi="${poi.id}">
                                <g:link controller="poi" id="${poi.id}" action="show">${poi.nom}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </details>
                <g:link controller="groupe" id="${g.id}" action="show">view groupe</g:link>

            </g:each>
        </nav>


        </div>

    </sec:ifLoggedIn>

</div>
<div id="map" style="width: 100%;height:400px">
</div>

<script type="application/javascript">
    <g:applyCodec encodeAs="none">
    var poisList = ${Poi.list() as grails.converters.JSON}
        </g:applyCodec>
        console.log(poisList);

    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: 46, lng: 5},
            zoom: 5
        });
        for(var i=0;i<Object.keys(poisList).length;i++) {
            console.log(poisList[i].nom);
            var myLatlng = new google.maps.LatLng(poisList[i].lat,poisList[i].lng);
            // Création du Marker
            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                title:  poisList[i].nom
            });
            google.maps.event.addListener(marker, 'dragend', function (event) {
               console.log(this.getPosition().lat());
                console.log(this.getPosition().lng());
            });
            marker.setMap(map);
        }
    }
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCyV8_Z6hw9b9WXEtzFNgp7K9Qpt_--L9Q&callback=initMap"></script>

<!--asset:javascript src="map.js"/-->
<!--asset:javascript src="index.js"/>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBaYjfpRuvx3WWn7_OdWTaCmH26SjquKV0&callback=initMap"></script-->

</body>
</html>
