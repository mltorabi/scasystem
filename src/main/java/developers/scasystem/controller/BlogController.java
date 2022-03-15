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

import developers.scasystem.model.Blog;
import developers.scasystem.model.BlogRepository;

@RestController
@RequestMapping("/api")
public class BlogController {

	@Autowired
	BlogRepository blogRepository;

	@GetMapping("/blog")
	public ResponseEntity<List<Blog>> findAllBlog() {
		try {
			return new ResponseEntity(blogRepository.findAll(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> findById(@PathVariable("id") long id) {
		Optional<Blog> blogData = blogRepository.findById(id);
		if (blogData.isPresent()) {
			return new ResponseEntity(blogData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/blog")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		try {
			return new ResponseEntity(blogRepository.save(blog),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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
			return new ResponseEntity(blogRepository.save(_blog),HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	

	@DeleteMapping("/blog/{id}")
	public ResponseEntity<HttpStatus> deleteStaffById(@PathVariable("id") long id)	{
		try {
			blogRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/staff")
	public ResponseEntity<HttpStatus> deleteAllStaff(){

		try {
			blogRepository.deleteAll();
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}
	
	
	
	
	
	
	
	
	
}
