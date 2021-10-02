package core.commands.noArguments;

import java.sql.ResultSet;
import java.util.Collections;

import core.commands.Command;
import core.db.dao.OrderDAO;
import core.db.dao.ProductDAO;

public class ShowAllOrdersCommand implements Command {
	
	private void showOrdersTableLine(String id, String price, String name, String quantity, String created_at) {
		System.out.printf("|%s|%s|%s|%s|%s|\n", id, price, name, quantity, created_at);
	}
	
	private void showOrdersTable(ResultSet orderList) throws Exception {
		int nameFieldLength = ProductDAO.getAllProducts_MaxLength();
		
		final String idField = "Order ID";
		final int idFieldLength = idField.length();
		 
		final String priceField = "Products Total Price";
		final int priceFieldLength = priceField.length();
		
		final String nameField = "Product Name";
		final int minNameFieldLength = nameField.length();
			
		final String quantityField = "Products Quantity";
		final int quantityFieldLength = quantityField.length();

		final String dateField = "Order Created Date";
		final int dateFieldLength = dateField.length();
		
	    if (nameFieldLength < minNameFieldLength)
			nameFieldLength = minNameFieldLength;
	    
	    showOrdersTableLine(
				String.format("%-"+idFieldLength+"s", idField),
				String.format("%-"+priceFieldLength+"."+priceFieldLength+"s", priceField),
				String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", nameField),
				String.format("%-"+quantityFieldLength+"s", quantityField),
				String.format("%-"+dateFieldLength+"s", dateField)
				);
	
	    showOrdersTableLine(
				String.format("%-"+idFieldLength+"s", 
						String.join("", Collections.nCopies(idFieldLength, "-"))),
				String.format("%-"+priceFieldLength+"."+priceFieldLength+"s",
						String.join("", Collections.nCopies(priceFieldLength, "-"))),
				String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", 
						String.join("", Collections.nCopies(nameFieldLength, "-"))),
				String.format("%-"+quantityFieldLength+"s",
						String.join("", Collections.nCopies(quantityFieldLength, "-"))),
				String.format("%-"+dateFieldLength+"s",
						String.join("", Collections.nCopies(dateFieldLength, "-")))
				);
		
	    do {
			showOrdersTableLine(
					String.format("%-"+idFieldLength+"s", orderList.getInt("id")),
					String.format("%-"+priceFieldLength+"s", orderList.getInt("price")),
					String.format("%-"+nameFieldLength+"s", orderList.getString("name")),
					String.format("%-"+quantityFieldLength+"s", orderList.getInt("quantity")),
					String.format("%-"+dateFieldLength+"s", orderList.getString("created_at"))				
					);
		} while (orderList.next());
	}
	
	@Override
	public boolean execute(String argsString) throws Exception {
		boolean isQueryOK = false;
		ResultSet orderList = OrderDAO.getAllOrdersFullInfoList();
		isQueryOK = true;
		if (!orderList.next()) {
			System.out.println("Orders table is empty");
		} else {
			showOrdersTable(orderList);
		}
		return isQueryOK;
	}
	


}

