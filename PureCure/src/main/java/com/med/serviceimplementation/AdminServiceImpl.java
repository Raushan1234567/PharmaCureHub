package com.med.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.med.exception.AdminAlreadyExistsException;
import com.med.exception.AdminException;
import com.med.model.Admin;
import com.med.repository.AdminRepository;
import com.med.serviceinetrface.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public Admin addNewAdmin(Admin admin) throws AdminAlreadyExistsException {
		// TODO Auto-generated method stub
		Optional<Admin> op =  adminRepo.findByAdminEmail(admin.getAdminEmail());
		if (!op.isPresent()) {
	        adminRepo.save(admin);
	    } else {
	        throw new AdminAlreadyExistsException("Admin with email " + admin.getAdminEmail() + " already exists.");
	    }
		return admin;
	}

	@Override
	public Admin deleteAdminById(int adminId) {
		// TODO Auto-generated method stub
		Admin admin = null;
		Optional<Admin> op = adminRepo.findById(adminId);
		if(op.isPresent()) {
			admin = op.get();
			adminRepo.delete(admin);
		}else {
			throw new AdminException("Admin is not exist with this adminId "+adminId);
		}
		adminRepo.deleteById(adminId);
		return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Admin existAdmin = null;
		Optional<Admin> op = adminRepo.findById(admin.getAdminId());
		if(op.isPresent()) {
			existAdmin = op.get();
			existAdmin.setAdminName(admin.getAdminName());
			existAdmin.setAdminAddress(admin.getAdminAddress());
			existAdmin.setAdminPassword(admin.getAdminPassword());
			existAdmin.setAdminMobileNumber(admin.getAdminMobileNumber());
			adminRepo.save(existAdmin);
		}else {
			throw new AdminException("Admin is not Exist withthe provided adminId "+admin.getAdminId());
		}
		return existAdmin;
	}

	@Override
	public Admin findAdminById(int adminId) {
		
		Admin admin = null;
		Optional<Admin> op = adminRepo.findById(adminId);
		if(op.isPresent()) {
			admin = op.get();
		}else {
			throw new AdminException("Admin is not Exist withthe provided adminId "+adminId);
		}
		return admin;
	}

	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		List<Admin> adminList = adminRepo.findAll();
		if(adminList.isEmpty()) {
			throw new AdminException("No Admin Exist at this moment");
		}
		return adminList;
	}

}
