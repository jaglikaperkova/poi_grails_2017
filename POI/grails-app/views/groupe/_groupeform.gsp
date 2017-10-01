<%@ page import="fr.mbds.poi.Groupe" %>

<style>

.container_images{
    display: block;
    text-align: center;
}

.image_form {
    position: relative;
    display: inline-block;
    width: 100px;
    height: 100px;
    background-color: white;
    margin: 5px;
    vertical-align: middle;

.btn_delete_image {
    position: absolute;
    top: 0px;
    right: 0px;
    margin-top: 4px;
    margin-right: 4px;
    color: white;
    font-size: 16px;
    border-radius: 20px;
    background-color: rgba(0, 0, 0, 0.5);
    padding: 3px;
    cursor: pointer;
}

.btn_delete_image:hover {
    background-color: #d43f3a;
    border: none;
    color: white;
}

.image {
    border-radius: 10px;
    width: 50px;
    height: 50px;
}

}

.contButtImageLoader{
    text-align: center;
}

.add-image-loader{
    color:#4cae4c;
    cursor: pointer;
    font-size: 20px;
}

.remove-image-loader{
    color:#cc3333;
    cursor: pointer;
    font-size: 20px;
}
</style>
<table >

    <tr>
        <td>
            <g:message code="groupe.label.nom" default="Nom"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:textField name="nom" class="form-control" required="" value="${groupe?.nom}"/>
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="groupe.label.pois" default="Pois"/>
        </td>
        <td>
            <ul class="one-to-many">
                <g:each in="${groupe?.pois ?}" var="p">
                    <li><g:link controller="poi" action="show" id="${p.id}">${p?.nom}</g:link></li>
                </g:each>
            </ul>
            <div class="col-sm-4">


                <p class="add">
                    <g:link controller="poi" action="create"
                            params="['groupe.id': groupe?.id]">${message(code: 'default.add.label', args: [message(code: 'poi.label', default: 'Poi')])}</g:link>
                </p>
            </div>
            <g:select class="many-to-many" id="poi" name="pois" multiple="multiple" from="${fr.mbds.poi.Poi.list()}"
                            optionKey="id"
                            required=""
                            optionValue="nom"
                            value="${groupe?.pois*.id}"/>

        </td>
    </tr>
    <tr>
        <td>
            <g:message code="groupe.label.images" default="Images"/>

            <div class="col-sm-3 list-picture-loader">
                <input type="file" name="uploadFile"/>
            </div>
        </td>
        <td>
            <g:each in="${groupe.images}" var="p">
                <div class="image_form" data-picture="${p.id}">
                    <img class="image" height="100px" width="100px" src="${grailsApplication.config.images.groupes.url + p.name}">
                </div>
            </g:each>
        </td>
    </tr>

</table>


