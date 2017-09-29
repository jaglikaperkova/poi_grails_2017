<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'image.label', default: 'Image')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-image" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="col-md-4 col-sm-4 col-xs-12 ">
    <div class="product">
        <div class="product-img ">
            <img name="pictures" class="picture" src="${grailsApplication.config.images.test.url+this.image.nom }>
        </div>
    </div>
</div>
    <g:form resource="${this.image}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.image}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
