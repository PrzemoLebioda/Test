package com.comida.sia.intelligence.news.domain.model;

import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "Author")
@Table(name = "intelligence_news_authors",
		indexes = {
				@Index(name = "authors_name_idx", columnList = "name")
		})
public class Author implements ValueObject<Author>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7005270131075453902L;
	
	@Id
	@Column(name = "id", nullable = false)
	@Getter private UUID id;
	
	@ManyToOne
    @JoinColumn(name="news_feed_id", nullable=false)
	private NewsFeed newsFeed;
	
	@Column(name = "name", nullable = false)
	@Getter private String name;
	
	public Author() {
		
	}
	
	public Author(NewsFeed newsFeed, String name) {
		this.id = UUID.randomUUID();
		this.setNewsFeed(newsFeed);
		this.setName(name);
	}
	
	private void setName(String name) {
		//this.assertNotEmpty(name, "Name of the author must be provided");
		this.name = name;
	}
	
	private void setNewsFeed(NewsFeed newsFeed) {
		AssertionConcern.assertNotNull(newsFeed, "News feed must be provided");
		this.newsFeed = newsFeed;
	}

	@Override
	public boolean sameValueAs(Author other) {
		if(other != null) {
			return  ComparationConcern.sameTexts(this.name, other.name);
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "Author [name=" + name + "]";
	}
}
