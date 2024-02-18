package com.med.serviceimplementation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.exception.CartException;
import com.med.exception.CustomerException;
import com.med.model.Cart;
import com.med.model.CartItem;
import com.med.model.Customer;
import com.med.model.Orders;
import com.med.repository.CartItemRepository;
import com.med.repository.CartRepository;
import com.med.repository.CustomerRepository;
import com.med.repository.MedicineRepository;
import com.med.repository.OrderRepository;
import com.med.serviceinetrface.OrderInterface;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderserviceImplemetation implements OrderInterface{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	

@Override
public Orders placeOrderForCustomer(int customerId) {  

    // Step 1: Retrieve the Customer by ID
    Optional<Customer> customerOptional = customerRepository.findById(customerId);

    if (customerOptional.isPresent()) {
        Customer customer = customerOptional.get();

        // Step 2: Retrieve the Cart associated with the Customer
        Cart cart = customer.getCart();

        if (cart != null) {
            // Step 3: Retrieve the list of CartItems in the Cart
            List<CartItem> cartItems = cart.getCartItems();

            if (!cartItems.isEmpty()) {
                // Step 4: Create an Orders entity
                Orders order = new Orders();
//                order.setOrderDate(LocalDateTime.now()); // Use the java.time API for dates

                // Step 5: Associate the order with the customer
                order.setCustomer(customer);

                // Step 6: Set or copy CartItems to the order (adjust as needed)
                order.setCartItems(cartItems);

                // Save the order
                Orders savedOrder = orderRepository.save(order);

                // Optionally, clear the cart
//           cart.setCartItems(Collections.emptyList()); // or cart.clearCartItems();
              
                cart.clearCartItems();  // This line clears the cart items
                cartRepository.save(cart); 
               
                // Return the saved order
                return savedOrder;
            } else {
                // Handle the case where the cart is empty
                throw new CartException("Cart is empty");
            }
        } else {
            // Handle the case where the customer doesn't have a cart
            throw new CartException("Cart not found for the customer");
        }
    } else {
        // Handle the case where the customer is not found
        throw new CustomerException("Customer not found");
    }
}

	
	

}
