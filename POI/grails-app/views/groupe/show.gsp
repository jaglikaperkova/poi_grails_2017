<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'groupe.label', default: 'Groupe')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-groupe" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-groupe" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list">
        <f:display  bean="groupe" property="nom"/>
        <f:display  bean="groupe" property="pois"/>
        <f:display  bean="groupe" property="images"/>

    </ol>

    <div id="map" style="width: 90%;height:400px">
    </div>

    <script type="application/javascript">
        <g:applyCodec encodeAs="none">
        var poisList = ${this.groupe.pois as grails.converters.JSON}
            </g:applyCodec>
            console.log(poisList);

        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 46, lng: 5},
                zoom: 3
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

                marker.setMap(map);
            }
        }
    </script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCyV8_Z6hw9b9WXEtzFNgp7K9Qpt_--L9Q&callback=initMap"></script>

    <g:form resource="${this.groupe}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.groupe}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
