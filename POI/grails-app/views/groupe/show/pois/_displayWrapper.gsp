<li class="fieldcontain">
    <span id="${label}" class="${label}"><g:message code="${label}" default="${label}" /></span>
    <g:each in="${value}" var="p">
        <span class="property-value" aria-labelledby="${label}"><g:link controller="poi" action="show" id="${p.id}">${p.nom} - ${p.adresse}</g:link></span>
    </g:each>
</li>