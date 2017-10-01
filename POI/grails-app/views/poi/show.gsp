<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'poi.label', default: 'Poi')}" />

    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-poi" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-poi" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <tr>
            <td>
                Nom:
            </td>
            <td>
                <f:display  bean="poi" property="nom"/>
            </td>

        </tr>

        <tr>
            <td>
                User:
            </td>
            <td>
                <f:display  bean="poi" property="user"/>
            </td>
        </tr>

        <tr>
            <td>
                Adresse:
            </td>
            <td>
                <f:display  bean="poi" property="adresse"/>
            </td>

        </tr>

        <tr>
            <td>
                Longitude : <f:display  bean="poi" property="lng"/>
            </td>
            <td>
                Latitude :<f:display  bean="poi" property="lat"/>
            </td>

        </tr>

        <tr>
            <td>
                Groupe :
            </td>
            <td>

                    <span class="property-value" aria-labelledby="${label}">
                        <g:each in="${poi.groupes}" var="g">
                            <g:link controller="groupe" action="show" id="${g.id}">
                                ${g.nom} </g:link></span>
                        </g:each>



            </td>

        </tr>
        <tr>
            <td>
                Description:
            </td>
            <td>
                <f:display  bean="poi" property="description"/>
            </td>

        </tr>
        <tr>
            <td>
                Images :
            </td>
            <td>
                <g:if test="${poi?.images}">

                    <div class="form-group">
                        <div class="container_pictures">
                            <g:each in="${poi.images}" var="p">
                                <div class="row">
                                    <img class="picture" height="100px" width="100px" name="${p.name}" src="${grailsApplication.config.images.pois.url + p.name}"/>
                                    <g:link controller="image" id="${p.id}" action="show">View</g:link>
                                </div>

                            </g:each>
                        </div>
                    </div>
                </g:if>
            </td>
        </tr>

    </table>


    <g:form resource="${this.poi}" method="DELETE">
    <fieldset class="buttons">
        <g:link class="edit" action="edit" resource="${this.poi}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
    </fieldset>
</g:form>
</div>
</body>
</html>
