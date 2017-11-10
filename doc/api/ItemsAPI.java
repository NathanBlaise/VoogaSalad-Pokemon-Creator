public interface ItemsAPI {
	
	    //Used to set the type of Item, string most likely retrieved from a properties file containing all of the items
		void setType(String itemType);
		//Used in the battle component or in main game depending on item, different for each item (maybe Lambda?) but executes its specific ability
		void executeAction();
		//Used to get the price of the item for use when purchasing new items
		int getPrice();
		
}
