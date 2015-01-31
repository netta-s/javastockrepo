package com.t2k.javastockapp.model;

/**
 * Portfolio Model object.
 * <p>
 * Represents a range of investments by an array of stocks. 
 * 
 * @author Netta Sulema
 */

public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private int portfolioSize;
	private String title;
	private Stock[] stocks; 
	
	//C'tors
	/**
	 * C'tor
	 * @return Portfolio object with default values. 
	 */
	public Portfolio() {
		this("undefined", 0, new Stock[MAX_PORTFOLIO_SIZE]);
	}
	
	/**
	 * Copy c'tor
	 * @param portfolio		Portfolio object to copy
	 * @return a new Portfolio object that contains the same values as the given param.
	 */
	public Portfolio(Portfolio portfolio) {
		String title = new String(portfolio.getTitle());
		int portfolioSize = portfolio.getPortfolioSize();
		Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
		Stock[] stocksToCopy = portfolio.getStocks();
		
		for (int i = 0; i < portfolioSize; i++) {
			stocks[i] = new Stock(stocksToCopy[i]);
		}
		
		setTitle(title);
		setPortfolioSize(portfolioSize);
		setStocks(stocks);
	}
	
	/**
	 * C'tor
	 * @param title			string that represents the portfolio name
	 * @param portfolioSize	int that represents the number of stocks in the portfolio
	 * @param stocks		Stock object array that the portfolio contains
	 * @return Portfolio object with the above values. 
	 */
	public Portfolio(String title, int portfolioSize, Stock[] stocks) {
		setTitle(title);
		setPortfolioSize(portfolioSize);
		setStocks(stocks);
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
	
	private void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	
	//Functions
	/**
	 * A method that adds a stock to the portfolio.
	 * Takes care of incrementing the portfolio size accordingly.
	 * @param stock		stock object to add to the portfolio.
	 * @return true if succeeded, false if you already reached the maximum size of the portfolio
	 * 		   (represented by MAX_PORTFOLIO_SIZE).
	 */
	public Boolean addStock(Stock stock) {
		if (portfolioSize < MAX_PORTFOLIO_SIZE) {
			stocks[portfolioSize] = stock;
			portfolioSize++;
			return true;
		}
		return false;
	}
	
	/**
	 * A method that removes a stock from the portfolio.
	 * Takes care of decrementing the portfolio size accordingly.
	 * @param index		int that represents the position of the stock in the portfolio.
	 */
	public void removeStock(int index) {
		for (int i = index; i < portfolioSize; i++) {
			if (i != portfolioSize) {
				stocks[i] = stocks[i + 1];
			} else {
				stocks[i] = null;
			}
		}
		setPortfolioSize(portfolioSize - 1); 
	}
	
	/**
	 * A method to return the portfolio data in an HTML string form.
	 * @return an HTML string with the stock's details: title and the data for each stock.
	 */
	public String getHtmlString() {
		String portfolioHtmlString = "<h1>" + getTitle() + "</h1>";
		for(int i = 0; i < portfolioSize; i++) {
			portfolioHtmlString += getStocks()[i].getHtmlDescription() + "<br>";
		}
		return portfolioHtmlString;
	}
}
