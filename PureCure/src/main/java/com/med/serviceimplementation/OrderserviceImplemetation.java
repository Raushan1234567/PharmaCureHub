//package com.med.serviceimplementation;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.med.exception.CartException;
//import com.med.exception.CustomerException;
//import com.med.model.Cart;
//import com.med.model.Customer;
//import com.med.model.Medicine;
//import com.med.model.Orders;
//import com.med.repository.CartRepository;
//import com.med.repository.CustomerRepository;
//import com.med.repository.MedicineRepository;
//import com.med.repository.OrderRepository;
//import com.med.serviceinetrface.OrderInterface;
//
//@Service
//public class OrderserviceImplemetation implements OrderInterface{
//
//	@Autowired
//	private CustomerRepository customerRepository;
//	
//	@Autowired
//	private CartRepository cartRepository;
//	
//	@Autowired
//	private MedicineRepository medicineRepository;
//	
//	@Autowired
//	private OrderRepository orderRepository;
//	
//	@Override
//	public Orders placeOrderForCustomer(int customerId) {
//	    // Step 1: Retrieve the Customer by ID
//	    Optional<Customer> customerOptional = customerRepository.findById(customerId);
//
//	    if (customerOptional.isPresent()) {
//	        Customer customer = customerOptional.get();
//
//	        // Step 2: Retrieve the Cart associated with the Customer
//	        Cart cart = customer.getCart();
//
//	        if (cart != null) {
//	            // Step 3: Retrieve the list of Medicines in the Cart
//	            List<Medicine> medicinesInCart = cart.getMedicines();
//
//	            if (!medicinesInCart.isEmpty()) {
//	                // Step 4: Create an Orders entity
//	                Orders order = new Orders();
//	                order.setOrderDate(new Date()); // Set the order date (adjust as needed)
//	             // Implement a method to calculate total price
//
//	                // Step 5: Associate the order with the customer
//	                order.setCustomer(customer);
//
//	                // Save the order
//	                Orders savedOrder = orderRepository.save(order);
//
//	                // Optionally, you might want to clear the cart or perform other actions
//
//	                // Return the saved order
//	                return savedOrder;
//	            } else {
//	                // Handle the case where the cart is empty
//	                throw new CartException("Cart is empty");
//	            }
//	        } else {
//	            // Handle the case where the customer doesn't have a cart
//	            throw new CartException("Cart not found for the customer");
//	        }
//	    } else {
//	        // Handle the case where the customer is not found
//	        throw new CustomerException("Customer not found");
//	    }
//	}
//
//	
//	
//
//}
