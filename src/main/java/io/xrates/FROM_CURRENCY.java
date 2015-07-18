package io.xrates;

public enum FROM_CURRENCY {
	Brunei_Dollar("Brunei Dollar"),
	South_African_Rand("South African Rand"),
	Chinese_Renminbi("Chinese Renminbi"),
	Indian_Rupee("Indian Rupee"),
	Korean_Won("Korean Won"),
	Sri_Lanka_Rupee("Sri Lanka Rupee"),
	Philippine_Peso("Philippine Peso"),
	Saudi_Rial("Saudi Rial"),
	New_Taiwan_Dollar("New Taiwan Dollar");
	
	private String name;
	FROM_CURRENCY(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name();
	}
}
