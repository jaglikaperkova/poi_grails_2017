<%@ page import="fr.mbds.poi.Poi" %>
<style>

.container_pictures{
    display: block;
    text-align: center;
}

.picture_form{
    position: relative;
    display: inline-block;
    width: 100px;
    height: 100px;
    background-color: white;
    margin: 5px;
    vertical-align: middle;

.btn_delete_picture{
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

.btn_delete_picture:hover{
    background-color: #d43f3a;
    border: none;
    color: white;
}

.picture{
    border-radius: 10px;
    width: 50px;
    height: 50px;
}

}

.contButtPictureLoader{
    text-align: center;
}

.add-picture-loader{
    color:#4cae4c;
    cursor: pointer;
    font-size: 20px;
}

.remove-picture-loader{
    color:#cc3333;
    cursor: pointer;
    font-size: 20px;
}
</style>

<table onscroll="true">

    <tr>
        <td>
            <g:message code="poi.label.nom" default="Nom"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:textField name="nom" class="form-control" required="" value="${poi?.nom}"/>
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="poi.label.adresse" default="Adresse"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:textField name="adresse" class="form-control" required="" value="${poi?.adresse}"/>
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="poi.label.desc" default="Description"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:textField name="description" class="form-control" required="" value="${poi?.description}"/>
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="poi.label.user" default="User"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:select class="form-control" id="user" name="user.id" from="${fr.mbds.poi.User.list()}"
                      optionKey="id"
                      required=""
                      optionValue="username"
                      value="${poi?.user?.id}"/>
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="poi.label.groupes" default="Groupes"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:select class="form-control" id="groupes" name="groupes.id" from="${fr.mbds.poi.Groupe.list()}"
                      optionKey="id"
                      required=""
                      optionValue="nom"
                      value="${poi?.groupes?.id}"/>
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="poi.label.longitude" default="Longitude"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:field name="lng" class="form-control" type="number" pattern="[0-9]+,[0-9]+" step="any" value="${(int)poi.lng}" required=""/>
        </td>
    </tr>
    <tr>
        <td>
            <g:message code="poi.label.latitude" default="Latitude"/>
            <span class="required-indicator">*</span>
        </td>
        <td>
            <g:field name="lat" class="form-control" type="number" pattern="[0-9]+,[0-9]+" step="any" value="${(int)poi.lat}" required=""/>
        </td>
    </tr>
    <tr>
        <td>
            <label class="col-sm-3 control-label">Pictures</label>
        </td>
        <td>
            <g:each in="${poi.images}" var="p">
                <div class="picture_form" data-picture="${p.id}">
                    <span class="btn_delete_picture glyphicon glyphicon-remove"></span>
                    <img name="pictures" class="picture" src="${grailsApplication.config.images.pois.url + p.name}">
                </div>
            </g:each>
            <div class="contButtPictureLoader">
                <span class="glyphicon glyphicon-plus-sign add-picture-loader"></span>
                <span class="glyphicon glyphicon-minus-sign remove-picture-loader"></span>
            </div>
            <div class="col-sm-3 list-picture-loader">
                <input type="file" name="uploadFile"/>
            </div>
        </td>
    </tr>
</table>





