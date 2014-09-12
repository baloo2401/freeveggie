//List of template already load
//it avoid loading twice a template 
var templateList = {};

/**
 * This method is made to call the submit form  and displaying error or success message.
 * 
 * @param {String} [action=$(this).attr("action"))] - A string containing the URL to which the request is sent.
 * @param {String} [method=$(this).attr("method"))] - The type of request to make ("POST" or "GET"), default is "GET". Note: Other HTTP request methods, such as PUT and DELETE, can also be used here, but they are not supported by all browsers.
 * @param {Function} [success(jqXHR)] - A function to call in succed submition.
 * @param {Function} [failed(jqXHR)] - A function to call in failed submition.
 * @param {String} [successAddress] - The location to go to when call success
 * @parap {String} [failedAdress] - The location to go to when the call failed
 * 
 * @returns False
 * 
 * @author Mickael Dubois
 */
jQuery.fn.form = function(param) {
	this.submit(function() {
		$('.error').removeClass('error');
		$('.error-msg').text('');
		//If the action is not given
		if(param == null || param.action == null){
			//we read it form the action attribute
			action = $(this).attr("action");
		} else {
			//Otherwise we set this as action
			action = param.action;
		}
		//If the method is not given
		if(param == null || param.method == null){
			//We read it form the method attribute
			method = $(this).attr("method");
		}  else {
			//Otherwise we set this as methods
			method = param.method;
		}
		$.ajax({
			type : method,
			url : action,
			data : $(this).serialize(),
			success : function(data, textStatus, jqXHR) {
				//if a call back is define then we call it with the data
				if(param != null && param.success != null){
					param.success.call(this, jqXHR);
				}
				//And we display a succefull message
				$.displaySuccess('Succefull update');
				if(param != null && param.successAddress != null){
					window.location.href = param.successAddress;
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if(jqXHR.status == 500){
					//an internal server error happend
					$.displayError("We encounter significant problems please again in a few minutes")
				}
				//if a call back is define then we call it with the data
				if(param != null && param.failed != null){
					param.failed.call(this, jqXHR);
				}
				//If we have an error 
				var data = jQuery.parseJSON(jqXHR.responseText);
				//If the error is an input error
				if(typeof data.errorCode === 'undefined'){
					$.displayError('Please correct errors')
					$.each(data, function(index, value) {
						$('#' + index + 'Field').addClass('error');
						$('#' + index + 'ErrorMessage').text(value);
					});;
				 } else {
					//If the error is an back end error
					$.displayError(data.errorMessage)
				 };
				if(param != null && param.failedAdress != null){
					window.location.href = param.failedAdress;
				}
			}
		});
		return false;	
	});
};

/**
 * Load a template as a list. We call the data address and expect a list of object.  When we get the result of the data address
 * we loop throw it and append as many as need the template apply to the row.
 * 
 * @param {String} templateAddress - The template to use load data
 * @param {String} dataAddress - The data address to use to get the data
 * @param {Function} [callbackFnk] - The call back method to call after loading the template
 * @param {String} [header] : A string to append before looping throw the list
 * @param {String} [footer] : A string to append after looping throw the list
 */
jQuery.fn.loadTableTemplate = function(param){
	var tempalteLocation =  "/public/template";
	//The model
	var model;
	//the view
	var template;
	//technical value that check we have both  (the view and the model)
	var cpt = 0;
	//just saving the location for later
	var dest = $(this);

	//We get the data using async
	$.get(param.dataAddress, function(pModel){
			model = pModel;
			load(dest);
		}
	);
	
	//We load the template from the template list
	template = templateList[param.templateAddress];
	//If the template never have been loaded
	if(template == null){
		//We get the temlate
		$.get(tempalteLocation+param.templateAddress, function(pTemplate){
				//We save the template to the list of template
				templateList[param.templateAddress] = pTemplate;
				//We set the template
				template = pTemplate;
				//We try to load the template
				load(dest);
			}
		);
	} else {
		//If the template has already loaded once
		//We try to load the template
		load(dest);
	}
	
	function load(here){
		//The load is called twice but only when the template and data are 
		//load that we actually change toe content
		cpt++;
		if(cpt == 2){
			//clear the location
			here.html("");
			//Adding the header
			if(param.header != null){
				here.append(param.header);
			}
			//if the result is not empty
			if(model != null && model != ""){
				//getting the data out of the response
				start = model.indexOf('"',2) + 2;
				stop = model.length - 1;
				dataString = model.substring(start, stop)
				data = jQuery.parseJSON(dataString);
				if(Object.prototype.toString.call( data ) == "[object Array]"){
					for (i=0;i<data.length;i++){
						var output = Mustache.render(template, data[i]);
						here.append(output);
					}
				}else{
					var output = Mustache.render(template, data);
					here.append(output);
				}
			}

			//Adding the footer
			if(param.footer != null){
				here.append(param.footer);
			}
			if(param.callbackFnk != null){
				param.callbackFnk.call(this);
			}
		}
	}
};

/**
 * Change the data format to the define format. The format have to be define on the html object adding
 * data-date-format attribute to the html tag. The formating will be apply an all html tag having the date class set.
 */
jQuery.fn.makeDateReadable = function(){
	$(".date").each(
		function(){
			var format = jQuery(this).attr("data-date-format");
			this.firstChild.nodeValue = $.datepicker.formatDate(format, new Date(parseInt(this.firstChild.nodeValue))); 
		}
	);
}

/**
 * Select the nav bar item y
 */
jQuery.fn.selectNavBar = function(	) {
	var hrefTarget = $.address.value();
	$('.sidebar-nav a').each(function() {
		if (hrefTarget.indexOf($(this).attr('href')) != -1) {
			$(this).parent().addClass('active').focus();
		} else {
			$(this).parent().removeClass('active');
		}
	});
}

/**
 * This method is to load a template and using data (using mustache framework)
 * 
 * @param {String} templateAddress - The template to use load data
 * @param {String} dataAddress - The data address to use to get the data
 * @param {Function} [callbackFnk] - The call back method to call after loading the template
 * 
 * @return void
 */
jQuery.fn.loadTemplate = function(templateAddress, dataAddress, callbackFnk ){
	var tempalteLocation = "/public/template";
	//The model
	var model;
	//the view
	var template;
	//technical value that check we have both  (the view and the model)
	var cpt = 0;
	//just saving the location for later
	var dest = $(this);

	//We get the data using async
	$.getJSON(dataAddress, function(pModel){
			model = pModel;
			load(dest);
		}
	).fail(function(jqXHR, textStatus, errorThrown) {
		window.location.href = "/login";
	});
	
	//We load the template from the template list
	template = templateList[templateAddress];
	//If the template never have been loaded
	if(template == null){
		//We get the temlate
		$.get(tempalteLocation+templateAddress, function(pTemplate){
				//We save the template to the list of template
				templateList[templateAddress] = pTemplate;
				//We set the template
				template = pTemplate;
				//We try to load the template
				load(dest);
			}
		);
	} else {
		//If the template has already loaded once
		//We try to load the template
		load(dest);
	}
	
	function load(here){
		//The load is called twice but only when the template and data are 
		//load that we actually change toe content
		cpt++;
		if(cpt == 2){
			//clear the location
			here.html("");
			//if the result is not empty
			if(model != null){
				//getting the data out of the response
				data = model;
				if(Object.prototype.toString.call( data ) == "[object Array]"){
					for (i=0;i<data.length;i++){
						var output = Mustache.render(template, data[i]);
						here.append(output);
					}
				}else{
					var output = Mustache.render(template, data);
					here.append(output);
				}
				$(".date").each(
					function(){
						var format = jQuery(this).attr("data-date-format");
						this.firstChild.nodeValue = $.datepicker.formatDate(format, new Date(parseInt(this.firstChild.nodeValue))); 
					}
				);

				if(callbackFnk != null){
					callbackFnk.call(this);
				}
			} else {
				here.append(Mustache.render(template, ""));
			}
		}
	}
}



jQuery.fn.createProductCalendar = function(){
	var months = new Array();
	months[0] = "JANUARY";
	months[1] = "FEBRUARY";
	months[2] = "MARCH";
	months[3] = "APRIL";
	months[4] = "MAY";
	months[5] = "JUNE";
	months[6] = "JULY";
	months[7] = "AUGUST";
	months[8] = "SEPTEMBER";
	months[9] = "OCTOBER";
	months[10] = "NOVEMBER";
	months[11] = "DECEMBER";
	
	var table = "";
	var reapMonth = this.attr("data-reap").split(",");
	var seedMonth = this.attr("data-seed").split(",");
	table += "<table class='table table-striped table-bordered table-condensed .table-striped'>";
	table += "	<thead><tr><th colspan='3'>Product Calendar</th></tr></thead>";
	table += "	<thead>";
	for ( var i = 0; i < months.length; i++) {
		var month = months[i];

		if(i%3==0){
			table += "		<tr>";
		}
		if($.inArray(months[i], reapMonth ) >= 0){
			table += "			<td id='calMonth"+i+"' class='alert-success' style='text-align: center;' data-toggle='tooltip' data-placement='top' title='' data-original-title='Picking period'>"+months[i]+"</th>";
		} else if ($.inArray(months[i], seedMonth ) >= 0){
			table += "			<td id='calMonth"+i+"' class='alert-info' style='text-align: center;' data-toggle='tooltip' data-placement='top' title='' data-original-title='Planting period'>"+months[i]+"</th>";
		} else {
			table += "			<td id='calMonth"+i+"' class='alert' style='text-align: center;' data-toggle='tooltip' data-placement='top' title='' data-original-title='Waiting period'>"+months[i]+"</th>";
		}
		if((i+1)%3==0){
			table += "		</tr>";
		}

		$('#calMonth'+i).tooltip();
	}
	table += "		</tr>";
	table += "	</thead>";
	table += "</table>";
	this.append(table);
	$('#calMonth0').tooltip();
	$('#calMonth1').tooltip();
	$('#calMonth2').tooltip();
	$('#calMonth3').tooltip();
	$('#calMonth4').tooltip();
	$('#calMonth5').tooltip();
	$('#calMonth6').tooltip();
	$('#calMonth7').tooltip();
	$('#calMonth8').tooltip();
	$('#calMonth9').tooltip();
	$('#calMonth10').tooltip();
	$('#calMonth11').tooltip();
	
}


jQuery.fn.manageRequest = function(productId){

	var status = this.attr("data-status");
	var answer = this.attr("data-answer");
	var contenu = "";
	if(status == "PENDING"){
		contenu += "<div style='padding-top:10px'>";
		contenu += "	<a href='#' id='accept' class='btn btn-primary'>Accept</a>";
		contenu += "	<a href='#' id='decline'	class='btn btn-danger'>Decline</a>";
		contenu += "</div>";
		this.append(contenu);
		$('#accept').click(function(){
			if($('#answerForm').length == 0){
				contenu = displayAnswerBlock();
				$(this.parentElement).append(contenu);
				param = {failedAdress : 'productrequest/"+productId+"/answer',
						successAddress : ''};
				$('#answerForm').form(param);
				
			}
			$('#accept').attr('disabled','disabled');
			$('#decline').removeAttr('disabled');
			$('#status').attr('value','accept');
			
			return false;
		});
		$('#decline').click(function(){
			if($('#answerForm').length == 0){
				contenu = displayAnswerBlock();
				$(this.parentElement).append(contenu);
				$('#answerForm').form();
			}
			$('#decline').attr('disabled','disabled');
			$('#accept').removeAttr('disabled');
			$('#status').attr('value','refuse');
			return false;
		});
	} else {
		contenu += "<div style='padding-top:10px'>";
		contenu += "	Your answer :";
		contenu += "	<textarea class='input-xlarge' id='textarea' rows='5' style='width: 100%; height: 150px;' disabled>"+answer+"</textarea>";
		contenu += "</div>";
		this.append(contenu);
	}
	
	function displayAnswerBlock(){
		var contenu = "";
		contenu += "<form id='answerForm' method='POST' action='productrequest/"+productId+"/answer'>";
		contenu += "<div style='padding-top:10px'>";
		contenu += "	<input type='hidden' name='status' id='status'>";
		contenu += "	Your answer :";
		contenu += "	<textarea name='message' id='message' class='input-xlarge' id='textarea' rows='5' style='width: 100%; height: 150px;'></textarea>";
		contenu += "</div>";
		contenu += "<div style='padding-top:10px'>";
		contenu += "	<button type='submit' class='btn btn-primary'>Send request</a>";
		contenu += "</div>";
		contenu += "</form>";
		return contenu;
	}
}