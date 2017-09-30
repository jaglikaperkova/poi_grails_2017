<li class="fieldcontain">
    <span id="${label}" class="${label}"><g:message code="${label}" default="${label}" /></span>
    <g:each in="${value}" var="i">
        <span class="property-value" aria-labelledby="${label}">
            <img class="picture" width="100px" height="100px" src="${grailsApplication.config.images.groupes.url +i.name}"/>
        </span>


    </g:each>
</li>