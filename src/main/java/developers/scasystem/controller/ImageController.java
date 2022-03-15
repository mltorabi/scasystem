package developers.scasystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import developers.scasystem.model.Image;
import developers.scasystem.model.ImageRepository;

@RestController
@RequestMapping("/api")
public class ImageController {

	@Autowired
	ImageRepository imageRepository;

	@GetMapping("/image")
	public ResponseEntity<List<Image>> findAllImage() {
		try {
			return new ResponseEntity(imageRepository.findAll(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<Image> findById(@PathVariable("id") long id) {
		Optional<Image> imageData = imageRepository.findById(id);
		if (imageData.isPresent()) {
			return new ResponseEntity(imageData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/image")
	public ResponseEntity<Image> createImage(@RequestBody Image image){
		try {
			return new ResponseEntity(imageRepository.save(image),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/image/{id}")
	public ResponseEntity<Image> updateImage(@PathVariable("id") long id,@RequestBody Image image){
		Optional<Image> imageData=imageRepository.findById(id);
		if(imageData.isPresent()) {
			Image _image=imageData.get();
			_image.setAltText(image.getAltText());
			_image.setPictureAddress(image.getPictureAddress());
			_image.setPictureCategory(image.getPictureCategory());
			_image.setStaff(image.getStaff());
				
			return new ResponseEntity(imageRepository.save(_image),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	

	@DeleteMapping("/image/{id}")
	public ResponseEntity<HttpStatus> deleteStaffById(@PathVariable("id") long id)	{
		try {
			imageRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/staff")
	public ResponseEntity<HttpStatus> deleteAllStaff(){

		try {
			imageRepository.deleteAll();
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}
	
}