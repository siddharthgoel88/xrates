(function(){
	
	/*
	 * Listen for a change in fromCurrency dropdown and get relevant toCurrency options available using
	 * an AJAX call.
	 */
	$('#fromCurrencyField').change(function(){
		var selectedCurrency = $('#fromCurrencyField').val();
		var dropdownHtml = "";
		console.log('From currency field Changed to ' + selectedCurrency );
		$.ajax({
			type: "POST",
			url: "/getToCurrencyList?fromCurrency=" + selectedCurrency,
			success: function(response){
				console.log('Ajax success. Response : ' + response);
				var toCurrencyElement = $('#toCurrencyField');
				
				for(i =0; i<response.length; i++){
					dropdownHtml += "<option value = '" + response[i] + "'>" + response[i] + "</option>";
				}
				toCurrencyElement.append(dropdownHtml);
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
	$('#toCurrencyField').change(function(){
		var fromCurrency = $('#fromCurrencyField').val();
		var toCurrency = $('#toCurrencyField').val();
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