var Map = (function MapClass(){
    var markers = [];

    var MapObj = function MapObject(el, options){
        this.el = el;
        this.opt = options;
        this.mapsApi = new google.maps.Map(this.el, getMapOptions());
    }

    function getMapOptions(){
        return {
            center: new google.maps.LatLng(46.8, 2.8),
            zoom: 6
        }
    }

    function addMarkerToMap(poi){
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(poi.lat, poi.lng),
            map: this.mapsApi,
            title: poi.nom,
            draggable: this.opt.markerDraggable || false,
            infoWindow: new google.maps.InfoWindow({
                maxWidth: 350

            })
        });

        // Event Listener
        if(this.opt.markerClickEvent)
            google.maps.event.addListener(marker, 'click', this.opt.markerClickEvent);
        if(this.opt.markerStartDragEvent)
            google.maps.event.addListener(marker, 'dragstart', this.opt.markerStartDragEvent);
        if(this.opt.markerEndDragEvent)
            google.maps.event.addListener(marker, 'dragend', this.opt.markerEndDragEvent);

        marker.poi = poi;
        markers.push(marker);

        return marker;
    }

    function getMarkerOfPoi(idPoi){
        for( var i in markers ){
            var marker = markers[i];

            if(marker.poi.id === idPoi){
                return marker;
            }
        }
        return null;
    }

    function removeMarkerOfPoi(idPoi){
        var marker = getMarkerOfPoi(idPoi);

        if(!marker){
            return false;
        }

        marker.setMap(null);
        return true;
    }

    function trigger(obj, nameEvent){
        new google.maps.event.trigger( obj, nameEvent );
    }

    MapObj.prototype = {
        getMarker: getMarkerOfPoi,
        addMarker: addMarkerToMap,
        removeMarker: removeMarkerOfPoi,
        trigger:trigger
    };

    return MapObj;
})();
//Markers

//Initialisation de la Map
