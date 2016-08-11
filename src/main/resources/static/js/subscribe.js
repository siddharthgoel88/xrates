(function(){
	
	$('select.from-currency-dropdown').select2();
	$('span.select2-container').css('width','200px');
	$('select.to-currency-dropdown').select2();
	$('span.select2-container').css('width','200px');
	
	/*
	 * Email Validation for Text field
	 */
	$('#email-input').focusout(function(){
		 var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		 var emailValid = regex.test($(this).val());
		 if(!emailValid){
			 console.log("Invalid Email");
			 $(this).parent('div').removeClass("has-success");
			 $(this).parent('div').addClass("has-error");
			 $('.subscribe-button').addClass('disabled');
		 }else{
			 console.log("Valid Email");
			 $(this).parent('div').removeClass("has-error");
			 $(this).parent('div').addClass("has-success");
			 $('.subscribe-button').removeClass('disabled');
		 }
	});
	
	/*
	 * Listen for a change in fromCurrency dropdown and get relevant toCurrency options available using
	 * an AJAX call.
	 */
//	$('.from-currency-dropdown li').on('click', function(){
	$('.from-currency-dropdown').on('change', function(){
		console.log("Option Selected " + $(this).val());
		
		var selectedCurrency = $(this).val();
		var toCurrencyDropdownHtml = "";
		var toCurrencyElement = $('.to-currency-dropdown');
		//emptying dropdown in case list already appended
		toCurrencyElement.find('option').remove();
		
		$(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
		$(this).parents(".dropdown").find('.btn').val($(this).data('value'));
		
		$.ajax({
			type: "POST",
			url: "/getToCurrencyList?fromCurrency=" + selectedCurrency,
			success: function(response){
				console.log('Ajax success. Response : ' + response);
				toCurrencyDropdownHtml = '<option selected="selected" value="none">To Currency</option>';
				for(i =0; i<response.length; i++){
					toCurrencyDropdownHtml += '<option value="' + response[i] + '">' + response[i] + '</option>'
				}
				toCurrencyElement.append(toCurrencyDropdownHtml);
			},
			error: function(){
				console.log('An error occured while making AJAX call');
			}
		});
	});
	
	/*
	 * Listen for a change in toCurrency dropdown and get relevant service options available using
	 * an AJAX call for that from-to currency combination
	 */
	$('.to-currency-dropdown').on('click','li',function(){
		console.log('To Currency dropdown clicked');
		$(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
		$(this).parents(".dropdown").find('.btn').val($(this).data('value'));
		
		var fromCurrency = $('.from-currency-dropdown li').text();
		var toCurrency = $('.to-currency-dropdown li').text();
		var serviceOptions = "";
		
		console.log("Populating services for " + fromCurrency + " to " + toCurrency);
		$.ajax({
			type: "POST",
			url : "/getServices?fromCurrency=" + fromCurrency + "&toCurrency=" + toCurrency,
			success: function(response){
				console.log('Ajax success. Response : ' + response);
				var servicesContainer = $('#relevantServices');
				
				for(i=0;i<response.length; i++){
					serviceOptions += "<div class='service-option'> " +
										"<input type='checkbox' name='services' value = '" + response[i] + "'>" + response[i] + "</input>" +
									   "</div>"
				}
				servicesContainer.append(serviceOptions);
			}, 
			error: function(){
				console.log('An error occured while making AJAX call');
			}
		});
	});
	
	
})();



