
//List of all the ref products
var product;
var markersArray = [];
$.address.state('/').change(function(event) {
	if($.address.value() != "/search"){
		$('#content').loadTemplate($.getTemplateAddress(),$.getDataAddress(), initTab);
	} else {
		initialize();
	}
});

google.maps.Map.prototype.clearMarkers = function() {
    for(var i=0; i < markersArray.length; i++){
        markersArray[i].setMap(null);
    }
    markersArray= new Array();
};

//Set the referenced products typeahead
$.get("/reference/product", function(pData){
	var productNames = new Array();
	var data = jQuery.parseJSON(pData)
	product = data;
	for (i=0;i<data.refProductMsg.length;i++){
		var productName = data.refProductMsg[i].name;
		productNames[i] = productName;
	}
	
	$('#product').typeahead({
	    source: function (typeahead, query) {
	    	return productNames;
	   	},

	    // typeahead calls this function when a object is selected, and
	    // passes an object or string depending on what you processed, in this case a string
	    onselect: function (obj) {		    
			for (i=0;i<data.refProductMsg.length;i++){
				var productName = data.refProductMsg[i].name;
				if(productName == obj){
					//If we find a coresponding value we set it and exit
					$("#refProductId").attr('value',data.refProductMsg[i].id);
					loadSearch(map.getBounds())
					return;
				}
			}
	    	//If we dont find any we set -1
	    	$("#refProductId").attr('value','-1');
	    	loadSearch(map.getBounds())
	    }
	})
});

var mapOptions = {
    zoom: 12,
    panControl : false,
    scaleControl: false,
    zoomControl : true,
    streetViewControl: false,
    mapTypeControl : false,
    zoomControlOptions: {
        style: google.maps.ZoomControlStyle.LARGE,
        position: google.maps.ControlPosition.LEFT_TOP 
      },
    mapTypeId: google.maps.MapTypeId.ROADMAP
};

function initialize() {
  map = new google.maps.Map(document.getElementById('map-canvas'),
		  mapOptions);

  // Try HTML5 geolocation
  if(navigator.geolocation) {
	  navigator.geolocation.getCurrentPosition(function(position) {
      loadContent(position.coords.latitude, position.coords.longitude);
    }, function() {
      handleNoGeolocation(true);
    });
  } else {
    // Browser doesn't support Geolocation
    handleNoGeolocation(false);
  }

  initProductField();
}

function initTab(){
	map = new google.maps.Map(document.getElementById('map-canvas'),
		  mapOptions);
	var pos = new google.maps.LatLng($("address").attr('data-lat'),$("address").attr('data-lng'));
    map.setCenter(pos);
    map.setOptions(mapOptions)
    map.setZoom(15);
	updateMarker();
	initProductField();
}

function initProductField(){
	$('#product').change(function() {
	  	data = product;
	  	for (i=0;i<data.refProductMsg.length;i++){
	  		var productName = data.refProductMsg[i].name;
	  		if(productName == $("#product").attr('value')){
	  			//If we find a coresponding value we set it and exit
	  			$("#refProductId").attr('value',data.refProductMsg[i].id);
	  			loadSearch(map.getBounds())
	  			return;
	  		}
	  	}
	  	//If we dont find any we set -1
	  	$("#refProductId").attr('value','-1');
	  	loadSearch(map.getBounds())
	  });
}


function loadPage(latitude, longitude) {
	var pos = new google.maps.LatLng(latitude,longitude);
    map.setCenter(pos);
    map.setOptions(mapOptions)
    loadSearch(map.getBounds());
    
}
function loadSearch(bounds){
	var refProductId = $('#refProductId').val();
	if(refProductId == -1){
		address = '/search/garden.mustache.html';
	} else {
		address = '/search/product.mustache.html';
	}
	param = {
		templateAddress : address,
		dataAddress: 'search/garden/'+bounds.getNorthEast().lat()+'/'+bounds.getSouthWest().lat()+'/'+bounds.getNorthEast().lng()+'/'+bounds.getSouthWest().lng() + '/' + refProductId,
		callbackFnk : updateMarker
	}
	$('#content').loadTableTemplate(param);
}

function updateMarker(){
	map.clearMarkers();
	//We add all the address marker
	$.each($("address"), function(index, value) {
	  var pos = new google.maps.LatLng($(value).attr('data-lat'), $(value).attr('data-lng'));
	  var marker = new google.maps.Marker();
	  marker.setPosition(pos);
	  marker.setMap(map);
	  marker.setVisible(true);
	  markersArray.push(marker);
	  google.maps.event.addListener(marker,"click",function(){});
	});


	  //When the user change the zoom we run a new seach
	  google.maps.event.addListener(map, 'zoom_changed', function() {
		  loadSearch(map.getBounds());
	  });

	  //When the user change the map position we run a new search
	  google.maps.event.addListener(map, 'dragend', function() {
		  loadSearch(map.getBounds());
	  });
}

function loadContent(latitude, longitude){
	
	$.getJSON('http://maps.googleapis.com/maps/api/geocode/json?latlng='+latitude+','+longitude+'&sensor=false', 
	  function(address){
		cityName = address.results[1].address_components[1].long_name;
		cityZipCode = address.results[1].address_components[0].long_name;
		countryCode = address.results[1].address_components[4].short_name;;
		country = address.results[1].address_components[4].long_name;
		$('#address').val(cityName + ', ' + country );
		loadPage(latitude, longitude)
	});
}

function handleNoGeolocation(errorFlag) {
  if (errorFlag) {
    var content = 'Error: The Geolocation service failed.';
  } else {
    var content = 'Error: Your browser doesn\'t support geolocation.';
  }

  var options = {
    map: map,
    position: new google.maps.LatLng(60, 105),
    content: content
  };

  var infowindow = new google.maps.InfoWindow(options);
  map.setCenter(options.position);
}

	


$("#address").addressPicker({
    boundElements: {
    	'#latitude' : 'lat' ,
		'#longitude' : 'lng',
        '#event_foo': function (data) {
        	loadPage($('#latitude').val(), $('#longitude').val())
        }
    }
});

$("#seach").click(function(){
	loadSearch(map.getBounds());
	return false;
});

$('#seachForm').submit(function() {
	  alert('Handler for .submit() called.');
});

//google.maps.event.addDomListener(window, 'load', initialize);