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
		return null;
	}

	

	@Override
	public Admin findAdminById(int adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAllAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Admin updateAdmin(Integer adminId, String adminName, String adminAdress, String adminPassword,
//			String adminMobileNumber2) {
//	Optional<Admin> admin=adminRepo.findById(adminId);
//	if(admin.isPresent()) {
//	    admin.get().setAdminName(adminName);
//	    admin.get().setAdminAddress(adminAdress);;
//	    admin.get().setAdminMobileNumber(adminMobileNumber2);
//	    admin.get().setAdminPassword(adminPassword);
//	    return adminRepo.save(admin.get());
//	}
//	else {
//		throw new AdminException("Admin not exist");
//	}
//	
//	}

}
