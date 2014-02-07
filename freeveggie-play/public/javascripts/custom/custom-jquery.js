$.extend({
	getUrlVars : function() {
		var vars = [], hash;
		var hashes = window.location.href.slice(
				window.location.href.indexOf('?') + 1).split('&');
		for ( var i = 0; i < hashes.length; i++) {
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	},
	
	getUrlVar : function(name) {
		return $.getUrlVars()[name];
	},
	
	getTemplateAddress : function(defaultAddress, callbackFnk){
		var base = $.address.path();
		var value = $.address.value().replace('#', '/');
		if (value == '/' || value == '' || value == undefined || value == base) {
			value = base + "/" + defaultAddress;
		}
		value = value.split('?')[0] + '.mustache.html'
		
		return value;
	},
	
	getModalTemplateAddress : function(base, defaultAddress){
		var value = $.address.value().replace('#', '/');
		if (value == '' || value == undefined || value == base) {
			value = defaultAddress;
		}
		value = '/public/template' + value.split('?')[0] + '.modal.mustache.html'
		
		return value;
	},
	
	getDataAddress : function(defaultAddress){
		var base = $.address.path();
		var value = $.address.value().replace('#', '/');
		if (value == '/' || value == '' || value == undefined || value == base) {
			value = base + "/" + defaultAddress;
		}

		return value;
	}, 
	
	displaySuccess : function(message){
		//We remove the error class from the display pane
		$('#messagePanel').removeClass('alert-error');
		//We add the success class from the display pane
		$('#messagePanel').addClass('alert-success');
		//We add the message
		$('#message').html(message);
		//And made id fade out after 2500 ms
		$('#messagePanel').fadeIn().delay(2500).fadeOut(1000);
	},
	
	displayError : function(message){
		//We remove the success class from the display pane
		$('#messagePanel').removeClass('alert-success');
		//We add the error class from the display pane
		$('#messagePanel').addClass('alert-error');
		//We add the message
		$('#message').html(message);
		//And made id fade out after 2500 ms
		$('#messagePanel').fadeIn().delay(2500).fadeOut(1000);
	}
});