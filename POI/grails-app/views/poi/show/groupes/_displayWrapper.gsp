    <span id="${label}" class="${label}"></span>
    <g:each in="${value}" var="g">
        <span class="property-value" aria-labelledby="${label}">
            <g:link controller="groupe" action="show" id="${g.id}">
                ${g.nom} </g:link></span>

    </g:each>
