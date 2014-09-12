(function($) {

	function orderProductBy(order, way) {
		var restriction = $.getUrlVar('restriction');
		var base = $.address.hash().split('?')[0];
		if (restriction == undefined) {
			restriction = 'none';
		}
		if(base == ''){
			base = 'productsRequestReceived';
		}
		var url = base + '?restriction=' + restriction
				+ '&way=' + way + '&order=' + order;
		location.hash = url;
	}
	var methods = {
		header : function() {

			var addOnclickEventOnHeaderColumn = function(element) {
				var order = $.getUrlVar('order');
				var way = $.getUrlVar('way');
				var columnId = element.id;
				var isClickable =  $('#'+columnId).hasClass('query-table-header');
				if(isClickable){
					$('#' + columnId).click(function() {
						if (order != columnId || way != 'up') {
							orderProductBy(columnId, 'up');
						} else {
							orderProductBy(columnId, 'down');
						}
					});
	
					if (order == columnId && way == 'up') {
						$('#' + columnId).prepend('<i class="icon-custom-arrow-up"></i>');
					} else if (order == columnId && way == 'down') {
						$('#' + columnId).prepend('<i class="icon-custom-arrow-down"></i>');
					} else {
						$('#' + columnId).prepend('<i class="icon-custom-arrows"></i>');
					}
				}
			}
			
			var tableId = this.attr('id');
			
			$('#' + tableId + ' thead th').each(function(index, value){
				addOnclickEventOnHeaderColumn(value);
			});
		},
		
		product : function(modalId, pTemplateAddress) {
			var data;
			var template;
			
			var displayModal = function(){
				if(data != null && template !=null){
					var output = Mustache.render(template, data);
					$('#'+ modalId +' #modalContent').html(output);
					var format = $('#creationDate').attr('data-date-format');
					$('#creationDate').text($.datepicker.formatDate(format, new Date(parseInt($('#creationDate').text()))));
					$('.' + data.status).show();
					
					$('#'+ modalId).modal();
				}
			}
			
			
			var addOnclickEventOnProduct = function(element) {
				var columnId = element.id;
				$('#' + columnId).click(function() {
					var dataAddress = 'productrequest/' + columnId
					$.getJSON(dataAddress, function(pView){
							data = pView;
							displayModal();
						}
					);
					if(template == null){
						var templateAddress = pTemplateAddress;
						$.get(templateAddress, function(pTemplate){
							template = pTemplate;
							displayModal();
						});
					}
					
				});
			}
			
			var tableId = this.attr('id');
			
			$('#' + tableId + ' tbody tr').each(function(index, value){
				addOnclickEventOnProduct(value);
			});
		},
		
		formatJavaDate : function(){
			$(".date").each(
					function(){
						var format = jQuery(this).attr("data-date-format");
						this.firstChild.nodeValue = $.datepicker.formatDate(format, new Date(parseInt(this.firstChild.nodeValue))); 
					});
		},
		
		pagination : function(pageNb, page) {
			var address = $.address.value();
			if (address == '' || address == undefined || address == "/requests") {
				address = '#productsRequestReceived?restriction=none';
			}
			var reg = new RegExp("(&page=[0-9]*)", "g");
			address = address.replace(reg, "")
			this.children().remove();
			if (page == 1) {
				var line = '<ul><li class="disabled"><a href="#">&laquo;</a></li>';
			} else {
				var line = '<ul><li><a href="#">&laquo;</a></li>';
			}
			for (i = 1; i <= pageNb; i++) {
				if (page == i) {
					line += '<li class="active"><a href="#">' + i + '</a></li>';
				} else {
					line += '<li><a href="' + address + '&page=' + i + '">' + i
							+ '</a></li>';
				}
			}
			if (page == pageNb) {
				line += '<li class="disabled"><a href="#">&raquo;</a></li></ul>';
			} else {
				line += '<li><a href="#">&raquo;</a></li></ul>';
			}
			this.append(line);
		},
		navbar : function(base, defaultAddress) {
			var hrefTarget = $.address.value();
			var value = $.address.value().replace('#', '/');
			if (value == '' || value == undefined || value == base) {
				hrefTarget = defaultAddress;
			}
			this.each(function() {
				if (hrefTarget.indexOf($(this).attr('href')) != -1) {
					$(this).parent().addClass('active').focus();
				} else {
					$(this).parent().removeClass('active');
				}
			});
		}
	};

	$.fn.producttable = function(method) {

		// Method calling logic
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(
					arguments, 1));
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.tooltip');
		}

	};

})(jQuery);