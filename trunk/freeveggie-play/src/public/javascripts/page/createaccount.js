//Action on the cancel button
$('#cancel').click(function() {
	window.location.href = "/login";
});

//Action on the create action
$('#createAccount').form({
		successAddress : "/validateaccount"
});

//Action on the address picker field

var mapOptions = {
	zoom: 12,
	panControl : false,
	scaleControl: false,
	streetViewControl: false,
	mapTypeControl : false,
	mapTypeId: google.maps.MapTypeId.ROADMAP
};

$("#address").addressPicker({
    boundElements: {
    	'#latitude' : 'lat' ,
		'#longitude' : 'lng',
		'#streetNumber' : 'street_number',
		'#street' : 'route',
		'#locality' : 'locality',
		'#administrativeAreaLevel2' : 'administrative_area_level_2',
		'#administrativeAreaLevel1' : 'administrative_area_level_1',
		'#country' : 'country',
		'#postalCode' : 'postal_code',
		'#type' : 'type',
        '#event_foo': function (data) {
			var map = new google.maps.Map(document.getElementById('map'), mapOptions);
			var pos = new google.maps.LatLng($('#latitude').val(),$('#longitude').val());
			map.setCenter(pos);
			   
			var marker = new google.maps.Marker();
			marker.setPosition(pos);
			marker.setMap(map);  
        }
    }
});

//If the user agree the address can be set automaticly base on his position
function initialize() {
  map = new google.maps.Map(document.getElementById('map'),
		  mapOptions);

  // Try HTML5 geolocation
  if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = new google.maps.LatLng(position.coords.latitude,
                                       position.coords.longitude);
      
      
      map.setCenter(pos);
      
      var marker = new google.maps.Marker();
      marker.setPosition(pos);
      marker.setMap(map); 
      
      loadData(position.coords.latitude, position.coords.longitude);
      
    }, function() {
      handleNoGeolocation(true);
    });
  } else {
    // Browser doesn't support Geolocation
    handleNoGeolocation(false);
  }
}

function loadData(latitude,longitude){

	$.getJSON('http://maps.googleapis.com/maps/api/geocode/json?latlng='+latitude+','+longitude+'&sensor=false', 
	  function(address){
		$('#streetNumber').val(address.results[0].address_components[0].long_name);
		$('#street').val(address.results[0].address_components[1].long_name);
		$('#locality').val(address.results[0].address_components[2].long_name);
		$('#administrativeAreaLevel2').val(address.results[0].address_components[3].long_name);
		$('#administrativeAreaLevel1').val(address.results[0].address_components[4].long_name);
		$('#country').val(address.results[0].address_components[5].long_name);
		$('#postalCode').val(address.results[0].address_components[6].long_name);
		$('#latitude').val(address.results[0].geometry.location.lat);
		$('#longitude').val(address.results[0].geometry.location.lng);
		
		$('#address').val(address.results[0].formatted_address);
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

initialize();