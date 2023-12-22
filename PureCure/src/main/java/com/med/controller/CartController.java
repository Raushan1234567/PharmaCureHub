package com.med.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
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

import com.med.model.Cart;
import com.med.model.Medicine;
import com.med.serviceinetrface.CartServiceInterface;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
@RestController
public class CartController {
	
	@Autowired
    private CartServiceInterface cartServiceInterface;

 
	@PostMapping("/createCart/{customerId}")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart,@PathVariable Integer customerId){
		
		return new ResponseEntity<Cart>(cartServiceInterface.createCart(cart,customerId),HttpStatus.CREATED);
	}
	
	@GetMapping("/getCartIdByCustomerId/{customerId}")
    public ResponseEntity<Integer> getCartIdByCustomerId(@PathVariable Integer customerId) {
        Integer cartId = cartServiceInterface.findCartIdByCustomerId(customerId);

        // Check if a cart id with the given customerId exists
        return new ResponseEntity<>(cartId, HttpStatus.OK);
    }

	@PostMapping("/payment/{amount}")
	public ResponseEntity<String> payment(@PathVariable String amount){
		int am=Integer.parseInt(amount);
		System.out.println("done ="+amount);
		Order o=null;
		try {
			
		var clint=new RazorpayClient("rzp_test_QHOBobX1qODW9l", "xROii3krqQkKRr4DwO8Ee8sB");
			JSONObject ob=new JSONObject();
			ob.put("amount", am*100);
			ob.put("currency", "INR");
			ob.put("receipt", "txn_123456");
			
			o=clint.orders.create(ob);
			
			System.out.println(o);
		} catch (RazorpayException e) {	
		System.out.println(e);
		}
		
		return new ResponseEntity<String>(o.toString(),HttpStatus.OK) ;
	}
	
//	@PostMapping("/addMedicine/{cartId}/{medicineId}")
//    public ResponseEntity<String> addMedicineToCart(@PathVariable Integer cartId, @PathVariable Integer medicineId) {
////        try {
////            cartServiceInterface.addMedicineToCart(cartId, medicineId);
////            return new ResponseEntity<>("Medicine added to the cart successfully", HttpStatus.OK);
////        } catch (Exception e) {
////            return new ResponseEntity<>("Medicine already exist", HttpStatus.INTERNAL_SERVER_ERROR);
////        }
//		cartServiceInterface.addMedicineToCart(cartId, medicineId);
//      return new ResponseEntity<>("Medicine added to the cart successfully", HttpStatus.OK);
//    }
//	
//	
//	@GetMapping("/getcartItems/{cartId}")
//	public ResponseEntity<List<Medicine>> getMedcine(@PathVariable Integer cartId){
//		return new ResponseEntity<>(cartServiceInterface.getMedicine(cartId),HttpStatus.CREATED);
//	}
//	
//
//	@DeleteMapping("/removeCartItems/{cartId}/{medicineId}")
//	public ResponseEntity<String> removeFromCart(@PathVariable Integer cartId, @PathVariable Integer medicineId){
//		cartServiceInterface.removeFromCart(cartId, medicineId);
//		return new ResponseEntity<String>("Items remove successfully", HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/removecart/{cartId}")
//	public ResponseEntity<String> removeCart(@PathVariable Integer cartId){
//		return new ResponseEntity<String>(cartServiceInterface.removeCartById(cartId),HttpStatus.OK);
//	}
//	
	

}
