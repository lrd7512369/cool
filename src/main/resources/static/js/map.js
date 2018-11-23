let map;

let marker;

function initMap() {
    if (map === null || map === undefined) {
        map = new AMap.Map('map', {
            resizeEnable: true
        });
    }
}

function addMarker(lng, lat) {
    map.setZoomAndCenter(16, [lng, lat]);
    map.clearMap();
    marker = new AMap.Marker({
        position: new AMap.LngLat(lng, lat)
    });
    map.add(marker);
}