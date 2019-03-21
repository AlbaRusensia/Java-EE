package service;

import model.Product;

public interface OrderService {
	
	public void order(Product product);
	
	public void removeFromOrder(Product product);
}
