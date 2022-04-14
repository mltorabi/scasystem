package developers.scasystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import developers.scasystem.model.Blog;
import developers.scasystem.model.BlogRepository;
import developers.scasystem.model.Staff;
import developers.scasystem.model.StaffRepository;
import developers.scasystem.response.MessageResponse;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BlogController {

	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	StaffRepository staffRep;

	@GetMapping("/blog")
	public ResponseEntity<List<Blog>> findAllBlog() {
		try {
			return new ResponseEntity<>(blogRepository.findAll(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> findById(@PathVariable("id") long id) {
		Optional<Blog> blogData = blogRepository.findById(id);
		if (blogData.isPresent()) {
			return new ResponseEntity<>(blogData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/{uid}/blog")
	public ResponseEntity<?> createBlog(@PathVariable("uid") Long uid, @RequestBody Blog blog){
		try {
			Optional<Staff> staffData = staffRep.findById(uid);
			if(staffData.isPresent()) {
				blog.AddWriter(staffData.get());
				return new ResponseEntity<>(blogRepository.save(blog),HttpStatus.OK);
			}
			MessageResponse msg = new MessageResponse("Invalid user");
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}catch (Exception e) {
			MessageResponse msg = new MessageResponse("Server Error");
			return new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") long id,@RequestBody Blog blog){
		Optional<Blog> blogData=blogRepository.findById(id);
		if(blogData.isPresent()) {
			Blog _blog=blogData.get();
			_blog.setCategory(blog.getCategory());
			_blog.setPostContent(blog.getPostContent());
			_blog.setPostTitle(blog.getPostTitle());
			_blog.setPublishDate(blog.getPublishDate());
			_blog.setPublishTime(blog.getPublishTime());
			_blog.setWriters(blog.getWriters());		
			return new ResponseEntity<>(blogRepository.save(_blog),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@DeleteMapping("/blog/{id}")
	public ResponseEntity<HttpStatus> deleteStaffById(@PathVariable("id") long id)	{
		try {
			blogRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/blog")
	public ResponseEntity<HttpStatus> deleteAllBlogs(){

		try {
			blogRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}
	
	
	
	
	
	
	
	
	
}
