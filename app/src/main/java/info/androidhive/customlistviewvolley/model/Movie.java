package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

public class Movie {
	private String title, thumbnailUrl;
	private int year;
	private double rating;
	private ArrayList<String> genre;
	private String description;
	private int product_id;
	private int category_id;
	public Movie() {
	}

	public Movie(String name,String description, String thumbnailUrl, int price, int product_id,int category_id,
			ArrayList<String> genre) {
		this.title = name;
		this.description=description;
		this.thumbnailUrl = thumbnailUrl;
		this.product_id = product_id;
		this.category_id = category_id;
		this.year = price;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}
	public String getdescription() {
		return description;
	}

	public void setdescription(String name) {
		this.description = name;
	}
	public int getYear() {
		return year;
	}

	public void setYear(int price) {
		this.year = price;
	}
	public int getproduct_id() {
		return product_id;
	}

	public void setproduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getcategory_id() {
		return category_id;
	}

	public void setcategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


	public double getRating() {
		return product_id;
	}

	public void setRating(int product_id) {
		this.product_id = product_id;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

	public String get(String title2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void put(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	
}
