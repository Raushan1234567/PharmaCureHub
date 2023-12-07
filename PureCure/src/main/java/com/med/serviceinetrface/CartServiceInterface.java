package com.med.serviceinetrface;

import java.util.List;

import com.med.model.Cart;
import com.med.model.Medicine;

public interface CartServiceInterface {
	

	public Cart createCart( Cart cart,Integer customerId);

	public List<Medicine> getMedicine(Integer cartId);

	public void addMedicineToCart(Integer cartId, Integer medicineId);
	
	

}
