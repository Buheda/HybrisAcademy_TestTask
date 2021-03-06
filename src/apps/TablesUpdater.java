package apps;

import java.util.Scanner;

import core.commands.Command;
import core.commands.listedArguments.CreateOrderCommand;
import core.commands.mappedArguments.CreateProductCommand;
import core.commands.mappedArguments.UpdateOrderCommand;
import core.commands.noArguments.ShowAllOrdersCommand;
import core.commands.noArguments.ShowAllProductsCommand;
import core.db.DBConnection;
import core.db.entity.Products;
import dbApp.tablesManager.Factory;
import java.io.PrintStream;
import java.io.IOException;
import java.io.OutputStream;


public class TablesUpdater {
	private static Scanner scanner;
	private final static int COUNT_RECORD = 50;
	private final static PrintStream oldOut = System.out;
	private final static PrintStream oldErr = System.err;

	
	private static void switchOffConsole() {
		System.setOut(new PrintStream(new OutputStream() {
		      @Override public void write(int b) throws IOException {}
		    }));
		
		System.setErr(new PrintStream(new OutputStream() {
		      @Override public void write(int b) throws IOException {}
		    }));
	}
	
	private static void switchOnConsole() {
		System.setOut(oldOut);
		System.setErr(oldErr);
	}
	
	private static boolean askYN(String question) {
		System.out.println(question+" y/n");
		String commandLine = scanner.nextLine();
		while (!commandLine.equalsIgnoreCase("y") && !commandLine.equalsIgnoreCase("n")) {
			System.out.println("Incorrect answer");
			System.out.println("Do you need to drop old tables? y/n");
			commandLine = scanner.nextLine();
		}
		return commandLine.equalsIgnoreCase("y");
	}
	
	private static void dropTables() throws Exception {		
		System.out.println("removing tables...");
		Factory.getDBManager().dropTables();
		System.out.println("Done!");
	}
	
	private static void createTables() throws Exception {
		System.out.println("Creating tables..."); 
		Factory.getDBManager().createTables();
		System.out.println("Done!");
	}
	
	private static void generateProducts() throws Exception {
		System.out.println("Generating products...");
		switchOffConsole();
		
		Command command = new CreateProductCommand();
		int countPossibleStatuses = 3;
		int maxPrice = 1500;
		int minPrice = 100;
		for (int i = 0; i < COUNT_RECORD; ++i) {			
			int status = (int)(Math.random() * countPossibleStatuses);
			int price = minPrice + (int)(Math.random() * (maxPrice-minPrice));
			command.execute("--name Product"+i+" --status "+Products.getStatusTypes().get(status)+" --price "+price);
		}
		switchOnConsole();
		
		command = new ShowAllProductsCommand();
		command.execute();
		
		System.out.println("Done!");
	}
	
	private static void generateOrders() throws Exception {
		System.out.println("Generating orders...");
		switchOffConsole();

		Command command = new CreateOrderCommand();
		StringBuffer params = new StringBuffer();
		int maxProductsCountInOrder = 10;
		for (int i = 0; i < COUNT_RECORD; ++i) {
			int productsCountInOrder = 1 + (int)(Math.random() * maxProductsCountInOrder);  
			int productid = 0;
			params.setLength(0);
			for (int j = 0; j < productsCountInOrder; ++j ) {
				productid = (int)(Math.random() * COUNT_RECORD);
				params.append(" "+productid);
			}
			command.execute(params.toString());
		}

		command = new UpdateOrderCommand();
		int maxProductQuantityInOrder = 10;
		for (int i = 0; i < 10; ++i) {
			int orderId = (int)(Math.random() * COUNT_RECORD);
			int quantity = (int)(Math.random() * maxProductQuantityInOrder);
			for (int productid=0; productid< COUNT_RECORD; ++productid)
			command.execute("--orderid "+orderId+" --productid "+productid+" --quantity "+quantity);
		}
	    switchOnConsole();
		
		command = new ShowAllOrdersCommand();
		command.execute();
		
		System.out.println("Done!");
	}
		
	
	public static void main(String[] args) throws Exception {

		scanner = new Scanner(System.in);
		if (askYN("Do you need to drop old tables?"))
			dropTables();
		if (askYN("Do you need to create new tables?"))
			createTables();
		if (askYN("Do you need to generate products example data?")) {
			generateProducts();
		}
		if (askYN("Do you need to generate orders example data?")) {
			generateOrders();
		}
		
		
		DBConnection.getConnection().close();
		scanner.close();
	}

}
