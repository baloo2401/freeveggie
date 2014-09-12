$.address.state('/').change(function(event) {
	$('#content').loadTemplate($.getTemplateAddress('productsRequestReceived?restriction=PENDING'),$.getDataAddress('productsRequestReceived?restriction=PENDING'), $.fn.makeDateReadable);
	
	if ($.address.value().indexOf('#') != -1) {
		jQuery.fn.selectNavBar();
	}
});