package com.med.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.med.model.CartItem;
import com.med.model.Medicine;
import com.med.serviceinetrface.CartItemInterface;

@CrossOrigin(origins = "http://127.0.0.1:5500")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cartItem")
public class CartItemController {
	
	@Autowired
	private CartItemInterface cartItemInterface;
	
	@PostMapping("/addMedicineToCart/{cartId}/{medicineId}")
	public ResponseEntity<String> addMedicineToCartItem(@PathVariable Integer cartId, @PathVariable Integer medicineId) {
		cartItemInterface.addMedicineToCartItem(cartId, medicineId);
//		System.out.println("Medicine added");
		return new ResponseEntity<String>("Medicine added successfull", HttpStatus.OK);
	}
	
	@PostMapping("/increment/{cartItemId}")
    public ResponseEntity<String> incrementCartItemQuantity(@PathVariable Integer cartItemId) {
		cartItemInterface.incrementCartItemQuantity(cartItemId);
//        return ResponseEntity.noContent().build();
        return new ResponseEntity<String>("Incremented", HttpStatus.OK);
    }

    @PostMapping("/decrement/{cartItemId}")
    public ResponseEntity<String> decrementCartItemQuantity(@PathVariable Integer cartItemId) {
    	cartItemInterface.decrementCartItemQuantity(cartItemId);
//        return ResponseEntity.noContent().build();
        return new ResponseEntity<String>("Decremented", HttpStatus.OK);
    }
    
    @GetMapping("/getMedicinesByCartId/{cartId}")
    public ResponseEntity<List<Medicine>> findAllItems(@PathVariable Integer cartId) {
    	
        return new ResponseEntity<>(cartItemInterface.getMedicineByCartId(cartId) , HttpStatus.OK);
    }
    
    @GetMapping("/getCartItemId/{cartId}/{medicineId}")
    public ResponseEntity<Integer> findCartItemId(@PathVariable Integer cartId, @PathVariable Integer medicineId) {
    	
        return new ResponseEntity<>(cartItemInterface.findCartItemIdBycartIdMedicineId(cartId, medicineId) , HttpStatus.OK);
    }
    
    @GetMapping("/getQuantity/{cartItemId}")
    public ResponseEntity<Integer> findCartItemQuantity(@PathVariable Integer cartItemId) {
    	
        return new ResponseEntity<>(cartItemInterface.findCartItemQuantity(cartItemId) , HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteCartItem/{CartId}/{medicineId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Integer CartId, @PathVariable Integer medicineId) {
    	cartItemInterface.deleteCartItem(CartId, medicineId);
        return new ResponseEntity<>("Item Deleted" , HttpStatus.OK);
    }

}
