$.address.state('/').change(function(event) {
	$('#content').loadTemplate($.getTemplateAddress('generalInformation'),$.getDataAddress('generalInformation'));

	if ($.address.value().indexOf('#') != -1) {
		jQuery.fn.selectNavBar();
	}
});

function updateGardenList(){
	$('#gardens').loadTableTemplate({
		templateAddress : "/home/gardenLink.mustache.html",  
		dataAddress : "/garden/user/"+userId,  
		callbackFnk : function(){
			//we add the delete event on every garden
			$.each($('.delete-garden-cross'), function(index, value) {
				  $(value).click(function() {
					  $.ajax({
							type : "DELETE",
							url : "/garden/" + $(value).attr('data-garden'),
							success : function(data, textStatus, jqXHR) {
								$.displaySuccess('Update successfull');
								$('li[data-garden="'+$(value).attr('data-garden')+'"]').remove();
							},
							error : function(jqXHR, textStatus, errorThrown) {
								$.displaySuccess('Update failed');
							}
						});
					  
					});
			});
		},
		header : '<li class="nav-header">Garden</li>',
		footer : '<li id="addGarden" class=""><a href="#createGarden"><i class="icon-plus"/>Add a garden</a></li>'
	});
}

updateGardenList();