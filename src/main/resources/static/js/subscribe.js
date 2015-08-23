(function(){
	console.log("Got into jquery");
	$('#fromCurrencyField').change(function(){
		var selectedCurrency = $('#fromCurrencyField').val();
		console.log('From currency field Changed to ' + selectedCurrency );
	});
})();