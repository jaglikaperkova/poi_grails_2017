<%@ page import="grails.converters.JSON" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'image.label', default: 'Image')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-image" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-image"  role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table class="table">
                <g:each in="${fr.mbds.poi.Image.list()}" var="i">

                    <tr>
                        <td title="Image">
                            <g:if test="${i.url=="groupes" && fr.mbds.poi.Image.list().contains(i)}">
                                <img class="picture" height="100px" width="100px" src="${grailsApplication.config.images.groupes.url + i.name}"/>
                            </g:if>
                            <g:elseif test="${i.url=="pois" && fr.mbds.poi.Image.list().contains(i)}">
                                <img class="picture" height="100px" width="100px" src="${grailsApplication.config.images.pois.url + i.name}"/>
                            </g:elseif>
                        </td>
                        <td title="Link">
                            <g:link controller="image" action="show" id="${i.id}">View</g:link>
                        </td>

                    </tr>

                </g:each>
            </table>
            <div class="pagination">
                <g:paginate total="${imageCount ?: 0}" />
            </div>
        </div>
    </body>
</html>