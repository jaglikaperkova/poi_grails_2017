<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Points d'interet</title>
    <asset:javascript src="index.js"/>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBaYjfpRuvx3WWn7_OdWTaCmH26SjquKV0&callback=initMap"></script>
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
            <div class="map"></div>
        </div>

    </sec:ifLoggedIn>

</div>


</body>
</html>
