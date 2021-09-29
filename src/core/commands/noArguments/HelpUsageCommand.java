package core.commands.noArguments;

import core.commands.Command;

public class HelpUsageCommand implements Command {
	
	public static void showUsageInfo() {
		System.out.println("This application supports next commands:");
		System.out.println("	Help - info about application usage");
		System.out.println("	Create Product");
		System.out.println("	Create Order with a list of the products specified by id.");
		System.out.println("	Update Order Entries quantities.");
		System.out.println("	Show list of all products.");
		System.out.println("	Show list of all orders.");
		System.out.println("	Remove product by ID.");
		System.out.println("	Remove all products.");
		System.out.println("	exit - exit from application");
		
		System.out.println("Usage: ");
		System.out.println("create_product --name <arg1> --price <arg2> --status <arg3>");
		System.out.println("	<arg1>  product name");
		System.out.println("	<arg2>  product price, integer");
		System.out.println("	<arg3>  0,1,2 (0 - out of stock, 1 - in stock, 2 - running low)");	
		System.out.println("create_order <arg1> <arg2> <arg3> <argN>");
		System.out.println("	<argN>  ids of products");		
	//UpdateOrderCommand.showUsageInfo();
		System.out.println("remove_product <arg1> <arg2> <arg3> <argN>");
		System.out.println("  	<argN>  ids of products which should be removed");
		System.out.println("remove_all_products <password>");
		System.out.println("	<password>   password for removing all products");
		

	}
	
	@Override
	public boolean execute(String argsString) throws Exception {
		showUsageInfo();
		return true;	
	}

}