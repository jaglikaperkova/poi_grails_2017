<li class="fieldcontain">
    <span id="${label}" class="${label}"><g:message code="${label}" default="${label}" /></span>
    <g:each in="${value}" var="i">
        <span class="property-value" aria-labelledby="${label}">
            <div class="row">
                <img class="picture" width="100px" height="100px" src="${grailsApplication.config.images.groupes.url +i.name}"/>
                <g:link controller="image" id="${i.id}" action="show">View</g:link>
            </div>

        </span>


    </g:each>
</li>