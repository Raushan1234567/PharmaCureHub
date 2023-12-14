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

//    @Override
//    public String removeMedicineFromCart(Integer cartId, Integer medicineId) {
//        Optional<Cart> optionalCart = cartRepository.findById(cartId);
//        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
//
//        if (optionalCart.isPresent() && optionalMedicine.isPresent()) {
//            Cart cart = optionalCart.get();
//            Medicine medicine = optionalMedicine.get();
//
//            if (cart.getMedicines().contains(medicine)) {
//                cart.getMedicines().remove(medicine);
////                medicine.getCarts().remove(cart);
//                cartRepository.save(cart);
//                return "Deleted Successfully";
//            } else {
//                throw new MedicineException("Medicine not found in the cart");
//            }
//        } else {
//            throw new CartException("Cart or Medicine not found");
//        }
//    }


    public void removeFromCart(int cartId, int medicineId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        
        if(optionalCart.isPresent()) {
        	Cart cart = optionalCart.get();
        	if(optionalMedicine.isPresent()) {
        		
        		Medicine medicine = optionalMedicine.get();
        		
        		cart.getMedicines().remove(medicine);
        		medicine.getCarts().remove(cart);
        		
        		cartRepository.save(cart);
        		medicineRepository.save(medicine);
        		
        	}else throw new MedicineException("Medicine is not found");
        	
        }else {
        	throw new CartException("Cart is not found");
        }

//        if (optionalCart.isPresent() && optionalMedicine.isPresent()) {
//            Cart cart = optionalCart.get();
//            Medicine medicine = optionalMedicine.get();
//
//            // Remove the medicine from the cart
//            cart.getMedicines().remove(medicine);
//            medicine.getCarts().remove(cart);
//
//            cartRepository.save(cart);
//            medicineRepository.save(medicine);
//        }
    }

    @Override
    public String removeCartById(Integer cartId) {
        // Check if the cart exists
        Optional<Cart> cartOptional = cartRepository.findById(cartId);

        if (cartOptional.isPresent()) {
            // If the cart exists, delete it
            cartRepository.deleteById(cartId);
            return "Cart deleted successfully";
        } else {
            // If the cart does not exist, throw an exception or handle accordingly
            throw new RuntimeException("Cart not found for this id: " + cartId);
        }
    }

	@Override
	public Integer findCartIdByCustomerId(Integer customerId) {
		// TODO Auto-generated method stub
		Integer cartId=0;
		Customer customer = customerRepository.findById(customerId).get();
		if(customer != null) {
			Optional<Integer> op = cartRepository.findCartIdByCustomerId(customerId);
			if(op.isPresent())
			cartId = op.get();
			else throw new CartException("Cart not found");
		}else throw new CustomerException("Customer not found");
		return cartId;
	}

	


}
