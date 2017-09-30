<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="POI"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">

        <div class="container">
            <div class="navbar-header">

                <a class="navbar-brand" href="/projet">
                    <i class="fa grails-icon">
                        <asset:image src="grails-cupsonly-logo-white.svg"/>
                    </i> POI
                </a>
            </div>

            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">

                <ul class="nav navbar-nav navbar-right">




                    <g:pageProperty name="page.nav" />
                    <sec:ifNotLoggedIn>
                        <a href="${createLink(controller:'login', action:'login')}">Login</a>
                    </sec:ifNotLoggedIn>
                    <sec:ifLoggedIn>
                        <a href="${createLink(controller:'login', action:'logout')}">Logout</a>
                    </sec:ifLoggedIn>
                </ul>
            </div>

        </div>
    </div>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
