package com.med.serviceinetrface;

import java.util.List;

import com.med.model.Admin;

public interface AdminService {
	
	public Admin addNewAdmin(Admin admin);
	public Admin deleteAdminById(int adminId);
	public Admin updateAdmin(Admin admin);
	public Admin findAdminById(int adminId);
	public List<Admin> findAllAdmin();
	
	
	

}