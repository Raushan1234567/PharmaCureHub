package com.med.serviceimplementation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.exception.CartException;
import com.med.exception.CustomerException;
import com.med.exception.MedicineException;
import com.med.model.Cart;
import com.med.model.Customer;
import com.med.model.Medicine;
import com.med.repository.CartRepository;
import com.med.repository.CustomerRepository;
import com.med.repository.MedicineRepository;
import com.med.serviceinetrface.CartServiceInterface;

@Service

public class CartServiceImplementaion implements CartServiceInterface{
	
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private MedicineRepository medicineRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Cart createCart(Cart cart, Integer customerId) {
    	
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            throw new CustomerException("Customer not found");
        }

        Customer customer = customerOptional.get();
        cart.setCustomer(customer); 

        return cartRepository.save(cart);
    }

    @Override
    public void addMedicineToCart(Integer cartId, Integer medicineId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Optional<Medicine> medicineOp = medicineRepository.findById(medicineId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            
           
            if (!cart.getMedicines().contains(medicineOp.get())) {
                cart.getMedicines().add(medicineOp.get());
                medicineOp.get().getCarts().add(cart);
                cartRepository.save(cart);
            }else {
            	throw new MedicineException("Medicine already exist");
            }            
           
        }else {
        	throw new CartException("Cart doesn't exist");
        }
    }


    @Override
    public List<Medicine> getMedicine(Integer cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            

            if (cart != null) {
                List<Medicine> medicines = cart.getMedicines();
                return medicines;
            } else {
               
                return Collections.emptyList();
            }
        } else {
            throw new CustomerException("Customer not found");
        }
    }



   


}
