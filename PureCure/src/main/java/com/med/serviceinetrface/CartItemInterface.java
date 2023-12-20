package com.med.serviceinetrface;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.med.model.Cart;
import com.med.model.CartItem;
import com.med.model.Medicine;

public interface CartItemInterface {
	
	public void addMedicineToCartItem(Integer CartId, Integer medicineId);
	
	public void incrementCartItemQuantity(Integer cartItemId);

	public void decrementCartItemQuantity(Integer cartItemId);
	
	public List<Medicine> getMedicineByCartId(Integer cartId);
	
	public Integer findCartItemIdBycartIdMedicineId(Integer cartId, Integer medicineId);
	
	 public Integer findCartItemQuantity(Integer cartItemId);
	 
	 public void deleteCartItem(Integer cartId, Integer medicineId);

}
