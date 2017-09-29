    <span id="${label}" class="${label}"></span>
    <g:each in="${value}" var="i">
        <span class="property-value" aria-labelledby="${label}">
            <g:link controller="image" action="show" id="${i.id}">
                ${i.name}</g:link></span>
    </g:each>
