package com.med.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.med.model.CartItem;
import com.med.model.Medicine;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
	
	@Query("SELECT ci.medicine FROM CartItem ci WHERE ci.cart.cartId = :cartId")
    public List<Medicine> findMedicinesByCartId(@Param("cartId") Integer cartId);
	
	@Query("SELECT ci.cartItemId FROM CartItem ci WHERE ci.cart.cartId = :cartId and ci.medicine.medicineId = :medicineId")
	public Integer findCartItemIdBycartIdMedicineId(@Param("cartId") Integer cartId, @Param("medicineId") Integer medicineId);
	
	@Query("SELECT ci.quantity FROM CartItem ci WHERE ci.cartItemId = :cartItemId")
	public Integer findCartItemQuantity(@Param("cartItemId") Integer cartItemId);
	
//	delete from cart_item where cart_id = 1 and medicine_id = 1;
	
	@Modifying
	@Query("DELETE FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.medicine.medicineId = :medicineId")
	public void deleteMedicine(@Param("cartId") Integer cartId, @Param("medicineId") Integer medicineId);

}
