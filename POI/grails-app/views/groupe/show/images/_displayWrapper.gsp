<li class="fieldcontain">
    <span id="${label}" class="${label}"><g:message code="${label}" default="${label}" /></span>
    <g:each in="${value}" var="i">
        <span class="property-value" aria-labelledby="${label}">
            <g:link controller="image" action="show" id="${i.id}">
                ${i.name} :::: ${i.url}</g:link></span>
            <img style="align-content: center" class="img" >

    </g:each>
</li>