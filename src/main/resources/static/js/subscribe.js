(function(){
	
	/*
	 * Listen for a change in fromCurrency dropdown and get relevant toCurrency options available using
	 * an AJAX call.
	 */
	$('.from-currency-dropdown li').on('click', function(){
		console.log("Option Selected " + $(this).text());
		
		var selectedCurrency = $(this).text();
		var toCurrencyDropdownHtml = "";
		
		$(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
		$(this).parents(".dropdown").find('.btn').val($(this).data('value'));
		
		$.ajax({
			type: "POST",
			url: "/getToCurrencyList?fromCurrency=" + selectedCurrency,
			success: function(response){
				console.log('Ajax success. Response : ' + response);
				var toCurrencyElement = $('.to-currency-dropdown');
				for(i =0; i<response.length; i++){
					toCurrencyDropdownHtml += "<li>" + response[i] + "</li>"
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
					serviceOptions += "<input type='checkbox' name='services' value = '" + response[i] + "'>" + response[i] + "</input>"
				}
				servicesContainer.append(serviceOptions);
			}, 
			error: function(){
				console.log('An error occured while making AJAX call');
			}
		});
	});		
})();