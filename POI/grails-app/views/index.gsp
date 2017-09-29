<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Points d'interet</title>

</head>
<body>
    <content tag="nav">
        <sec:ifNotLoggedIn>
           <li><a href="${createLink(controller:'login', action:'login')}">Login</a></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><a href="${createLink(controller:'login', action:'logout')}">Logout</a></li>
        </sec:ifLoggedIn>

    </content>

<div id="content">
    <sec:ifNotLoggedIn>
        <h2>Please log in</h2>
    </sec:ifNotLoggedIn>
    <sec:ifLoggedIn>
        <h2>Welcome, you can consult the groups and pois</h2>
        <nav class="navbar nav_category">
            <g:each in="${fr.mbds.poi.Groupe.list()}" var="g">
                <details class="groupe" data-category="${g.id}">
                    <summary class="title-category">${g.nom}</summary>
                    <ul class="list-pois">
                        <g:each in="${g.pois}" var="poi">
                            <li class="poi" draggable="true" data-poi="${poi.id}">
                                <g:link controller="poi" id="${poi.id}" action="show">${poi.nom}</g:link>
                            </li>
                        </g:each>
                    </ul>
                </details>

            </g:each>
        </nav>
    </sec:ifLoggedIn>

</div>


</body>
</html>
