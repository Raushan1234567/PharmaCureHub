package com.med.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.exception.CartException;
import com.med.exception.CartItemException;
import com.med.exception.MedicineException;
import com.med.model.Cart;
import com.med.model.CartItem;
import com.med.model.Medicine;
import com.med.repository.CartItemRepository;
import com.med.repository.CartRepository;
import com.med.repository.MedicineRepository;
import com.med.serviceinetrface.CartItemInterface;

@Service
public class CartItemServiceImpl implements CartItemInterface{
	
	@Autowired
	private CartItemRepository cartItemRepo;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	public void addMedicineToCartItem(Integer cartId, Integer medicineId) {
        // Retrieve the cart and medicine from the repositories
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        Optional<Medicine> medicineOptional = medicineRepository.findById(medicineId);
        
        cartOptional.ifPresent(cart -> {
            medicineOptional.ifPresent(medicine -> {
                // Check if the medicine is already in the cart
                CartItem existingCartItem = findCartItemByMedicineId(cart, medicineId);

                if (existingCartItem != null) {
                   throw new MedicineException("Medicine already in cart");
                } else {
                    // Create a new cart item with quantity 1
                    CartItem cartItem = new CartItem();
                    cartItem.setCart(cart);
                    cartItem.setMedicine(medicine);
                    cartItem.setQuantity(1);

                    // Add the cart item to the cart
                    cart.getCartItems().add(cartItem);
                }

                cartRepository.save(cart);
            });
        });
       
	}
	private CartItem findCartItemByMedicineId(Cart cart, Integer medicineId) {
        return cart.getCartItems().stream()
        		.filter(cartItem -> cartItem.getMedicine().getMedicineId() == (medicineId))
                .findFirst()
                .orElse(null);
    }
	
	
	
	@Override
	public void incrementCartItemQuantity(Integer cartItemId) {
		Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);

        cartItemOptional.ifPresent(cartItem -> {
            // Increment the quantity
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartRepository.save(cartItem.getCart());
        });
		
	}
	@Override
	public void decrementCartItemQuantity(Integer cartItemId) {
		Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);

        cartItemOptional.ifPresent(cartItem -> {
            // Decrement the quantity, making sure it doesn't go below 1
            int newQuantity = Math.max(cartItem.getQuantity() - 1, 1);
            cartItem.setQuantity(newQuantity);
            cartRepository.save(cartItem.getCart());
        });
		
	}
	@Override
	public List<Medicine> getMedicineByCartId(Integer cartId) {
		Optional<Cart> opCart = cartRepository.findById(cartId);
		List<Medicine> listItems = null;
		if(opCart.isPresent()) {
			listItems = cartItemRepo.findMedicinesByCartId(cartId);
			if(listItems.isEmpty()) throw new CartException("No items available");
		}else throw new CartException("Cart_id is not valid");
		
		return listItems;
	}
	@Override
	public Integer findCartItemIdBycartIdMedicineId(Integer cartId, Integer medicineId) {
		Integer cartItemId = cartItemRepo.findCartItemIdBycartIdMedicineId(cartId, medicineId);
		Optional<CartItem> opcartItem = cartItemRepo.findById(cartItemId);
		if(opcartItem.isEmpty()) throw new MedicineException("Please check cartId and medicineId");
		else
		return cartItemId;
	}
	@Override
	public Integer findCartItemQuantity(Integer cartItemId) {
		Integer quantity = cartItemRepo.findCartItemQuantity(cartItemId);
		if(quantity < 1) throw new CartItemException("Invalid cartItemId");
		return quantity;
	}
	
	@Transactional
	@Override
	public void deleteCartItem(Integer cartId, Integer medicineId) {
		Optional<Cart> opCart = cartRepository.findById(cartId);
		Optional<Medicine> opmed = medicineRepository.findById(medicineId);
		if(opCart.isPresent() && opmed.isPresent())
		cartItemRepo.deleteMedicine(cartId, medicineId);
		else throw new CartItemException("Id is not valid");
//		return "Item deleted";
	}
	
	
}
