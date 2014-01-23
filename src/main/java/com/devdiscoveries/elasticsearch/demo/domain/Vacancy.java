package com.devdiscoveries.elasticsearch.demo.domain;

public class Vacancy {

	private String title;
	private String description;
	private String sector;
	private Tenure tenure;
//	private LocalDate startDate;
//	private LocalDate endDate;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Tenure getTenure() {
		return tenure;
	}

	public void setTenure(Tenure tenure) {
		this.tenure = tenure;
	}

	
	public static enum Tenure {
		PARTTIME,
		FULLTIME;
	}
	
}
