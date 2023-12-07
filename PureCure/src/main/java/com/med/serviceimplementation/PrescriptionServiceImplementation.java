package com.med.serviceimplementation;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.med.model.Prescription;
import com.med.repository.PrescriptionRepository;
import com.med.util.ImageUtils;





@Service
public class PrescriptionServiceImplementation {

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	public String uploadImage(MultipartFile file) throws IOException {
		
	Prescription imagedata=prescriptionRepository.save(Prescription.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
		
		if(imagedata!=null) {
			return "File uploded successfully"+" "+file.getOriginalFilename();
		}
		
		
		return null;
		
	}
	
	
	public byte[] downloadImage(String fileName) {
		
	
		Optional<Prescription> dbImageData=prescriptionRepository.findByName(fileName);
		
		byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
		return images;
		
	}
	
}
