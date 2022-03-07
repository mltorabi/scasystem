package developers.scasystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import javax.persistence.GenerationType;

@Entity
@Table(name = "blog")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "category")
	private int Category;
	
	@Column(name = "pb_date")
	private Date PublishDate;
	
	@Column(name = "pb_time")
	private String PublishTime;
	
	@Column(name = "post_title")
	private String PostTitle;
	
	@Column(name = "post_content")
	private String PostContent;
	
	@ManyToMany(mappedBy = "blog",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Staff> writers = new HashSet<>();
	
	public Blog() {
		
	}
	
	public Blog(int Category, Date PublishDate, String PublishTime, String PostTitle, String PostContent) {
		this.Category = Category;
		this.PostTitle = PostTitle;
		this.PublishDate = PublishDate;
		this.PublishTime = PublishTime;
		this.PostContent = PostContent;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCategory() {
		return Category;
	}

	public void setCategory(int category) {
		Category = category;
	}

	public Date getPublishDate() {
		return PublishDate;
	}

	public void setPublishDate(Date publishDate) {
		PublishDate = publishDate;
	}

	public String getPublishTime() {
		return PublishTime;
	}

	public void setPublishTime(String publishTime) {
		PublishTime = publishTime;
	}

	public String getPostTitle() {
		return PostTitle;
	}

	public void setPostTitle(String postTitle) {
		PostTitle = postTitle;
	}

	public String getPostContent() {
		return PostContent;
	}

	public void setPostContent(String postContent) {
		PostContent = postContent;
	}

	public Set<Staff> getWriters() {
		return writers;
	}

	public void setWriters(Set<Staff> writers) {
		this.writers = writers;
	}

	public void AddWriter(Staff writer) {
		this.writers.add(writer);
		writer.getBlogs().add(this);
	}
	
	public void RemoveWriter(Staff writer) {
		this.writers.remove(writer);
		writer.getBlogs().remove(this);
	}
}
