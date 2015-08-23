(function(){
	console.log("Got into jquery");
	$('#fromCurrencyField').change(function(){
		var selectedCurrency = $('#fromCurrencyField').val();
		var dropdownHtml = "";
		console.log('From currency field Changed to ' + selectedCurrency );
		$.ajax({
			type: "POST",
			url: "/getToCurrencyList?fromCurrency=" + selectedCurrency,
			success: function(response){
				console.log('Ajax success. Response : ' + response)
				var toCurrencyElement = $('#toCurrencyField');
				for(i =0; i<response.length; i++){
					dropdownHtml += "<option value = '" + response[i] + "'>" + response[i] + "</option>";
				}
				console.log(dropdownHtml)
				toCurrencyElement.append(dropdownHtml);
			},
			error: function(){
				console.log('An error occured.')
			}
		});
	});
})();