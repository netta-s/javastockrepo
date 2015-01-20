package com.t2k.javastockapp.model;

public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private int portfolioSize;
	private String title;
	private Stock[] stocks; 
	
	//C'tors
	public Portfolio() {
		setTitle("undefined");
		portfolioSize = 0;
		setStocks(new Stock[MAX_PORTFOLIO_SIZE]);
	}
	
	//Getters
	public String getTitle() {
		return title;
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	public int getPortfolioSize() {
		return portfolioSize;
	}

	//Setters
	public void setTitle(String title) {
		this.title = title;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
	//Functions
	public Boolean addStock(Stock stock) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			stocks[portfolioSize] = stock;
			portfolioSize++;
			return true;
		}
		return false;
	}
	
	public String getHtmlString() {
		String portfolioHtmlString = "<h1>" + getTitle() + "</h1>";
		for(int i = 0; i < portfolioSize; i++) {
			portfolioHtmlString += getStocks()[i].getHtmlDescription() + "<br>";
		}
		return portfolioHtmlString;
	}
}
