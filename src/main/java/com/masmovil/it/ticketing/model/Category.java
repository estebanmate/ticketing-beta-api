package com.masmovil.it.ticketing.model;

public class Category {

	private String id_type;
	private String type_name;
	private String id_category;
	private String category_name;
	private String parent_id;

	public Category(String id_type, String type_name, String id_category, String category_name, String parent_id) {
		super();
		this.id_type = id_type;
		this.type_name = type_name;
		this.id_category = id_category;
		this.category_name = category_name;
		this.parent_id = parent_id;
	}

	protected Category() {
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getId_Category() {
		return id_category;
	}

	public void setId_Category(String id_Category) {
		this.id_category = id_Category;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}
