package com.med.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.Cart;
import com.med.model.Medicine;
import com.med.serviceinetrface.CartServiceInterface;
@RequestMapping("/cart")
@RestController
public class CartController {
	
	@Autowired
    private CartServiceInterface cartServiceInterface;

 
	@PostMapping("/createCart/{customerId}")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart,@PathVariable Integer customerId){
		
		return new ResponseEntity<Cart>(cartServiceInterface.createCart(cart,customerId),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/addMedicine/{cartId}/{medicineId}")
    public ResponseEntity<String> addMedicineToCart(@PathVariable Integer cartId, @PathVariable Integer medicineId) {
//        try {
//            cartServiceInterface.addMedicineToCart(cartId, medicineId);
//            return new ResponseEntity<>("Medicine added to the cart successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Medicine already exist", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
		cartServiceInterface.addMedicineToCart(cartId, medicineId);
      return new ResponseEntity<>("Medicine added to the cart successfully", HttpStatus.OK);
    }
	
	
	@GetMapping("/getcartItems/{cartId}")
	public ResponseEntity<List<Medicine>> getMedcine(@PathVariable Integer cartId){
		return new ResponseEntity<>(cartServiceInterface.getMedicine(cartId),HttpStatus.CREATED);
	}

}
