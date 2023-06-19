package dominando.restful.model.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class BookRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String author;
	private Date launchDate;
	private Double price;
	private String title;
	
	public BookRequestDTO() {}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public int hashCode() {
		return Objects.hash(author, launchDate, price, title);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookRequestDTO other = (BookRequestDTO) obj;
		return Objects.equals(author, other.author) && Objects.equals(launchDate, other.launchDate)
				&& Objects.equals(price, other.price) && Objects.equals(title, other.title);
	}

}