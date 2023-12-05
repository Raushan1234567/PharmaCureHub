package com.med.serviceinetrface;

import java.util.List;

import com.med.exception.AdminAlreadyExistsException;
import com.med.model.Admin;
import com.med.model.Customer;

public interface AdminService {
	
	public Admin addNewAdmin(Admin admin)throws AdminAlreadyExistsException;
	public Admin deleteAdminById(int adminId);

	public Admin findAdminById(int adminId);
	public List<Admin> findAllAdmin();
	
	
	
	

}
