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
	private float balance;
	private String title;
	private Stock[] stocks; 
	
	//C'tors
	/**
	 * C'tor
	 * @return Portfolio object with default values. 
	 */
	public Portfolio() {
		this("undefined", 0, 0, new Stock[MAX_PORTFOLIO_SIZE]);
	}
	
	/**
	 * Copy c'tor
	 * @param portfolio		Portfolio object to copy.
	 * @return a new Portfolio object that contains the same values as the given param.
	 */
	public Portfolio(Portfolio portfolio) {
		String title = new String(portfolio.getTitle());
		int portfolioSize = portfolio.getPortfolioSize();
		float balace = portfolio.getBalance();
		Stock[] stocks = new Stock[MAX_PORTFOLIO_SIZE];
		Stock[] stocksToCopy = portfolio.getStocks();
		
		for (int i = 0; i < portfolioSize; i++) {
			stocks[i] = new Stock(stocksToCopy[i]);
		}
		
		setTitle(title);
		setPortfolioSize(portfolioSize);
		setBalance(balace);
		setStocks(stocks);
	}
	
	/**
	 * C'tor
	 * @param title			string that represents the portfolio name.
	 * @param portfolioSize	int that represents the number of stocks in the portfolio.
	 * @param balance		float that represents the amount of money available for investment.
	 * @param stocks		Stock object array that the portfolio contains.
	 * @return Portfolio object with the above values. 
	 */
	public Portfolio(String title, int portfolioSize, float balance, Stock[] stocks) {
		setTitle(title);
		setPortfolioSize(portfolioSize);
		setBalance(balance);
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
	
	public float getBalance() {
		return balance;
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
	
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	//Functions
	/**
	 * A method that adds a stock to the portfolio.
	 * Takes care of incrementing the portfolio size accordingly.
	 * @param stock		stock object to add to the portfolio.
	 * @return true if succeeded, false if you already reached the maximum size of the portfolio.
	 * 		   (represented by MAX_PORTFOLIO_SIZE).
	 */
	public Boolean addStock(Stock stock, int quantity) {
		Stock foundStock = findStock(stock.getSymbol());
		if (foundStock == null) {
			if (portfolioSize < MAX_PORTFOLIO_SIZE) {
				if (quantity > 0) {
					stock.setStockQuantity(quantity);
				} else {
					stock.setStockQuantity(0);
				}
				stocks[portfolioSize] = stock;
				setPortfolioSize(portfolioSize + 1);
				return true;
			}
			System.out.println("Can't add new stock, portfolio can only have " + MAX_PORTFOLIO_SIZE + " stocks.");
		}
		return false;
	}
	
	/**
	 * A method that removes a stock from the portfolio.
	 * Takes care of decrementing the portfolio size accordingly.
	 * @param index		int that represents the position of the stock in the portfolio.
	 */
	private void removeStock(int index) {
		for (int i = index; i < portfolioSize; i++) {
			if (i != portfolioSize - 1) {
				stocks[i] = stocks[i + 1];
			} else {
				stocks[i] = null;
			}
		}
		setPortfolioSize(portfolioSize - 1); 
	}
	
	/**
	 * A method that removes a stock from the portfolio.
	 * Sells the stock that was provided. 
	 * @param symbol		string that represents the symbol of the stock to remove from the portfolio.
	 */
	public Boolean removeStock(String symbol) {
		boolean removed = false;
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				sellStock(symbol, -1);
				removeStock(i);
				removed = true;
				break;
			}
		}
		return removed;
	}
	
	/**
	 * A method that sells a stock from the portfolio.
	 * @param symbol		string that represents the symbol of the stock to sell.
	 * @param quantity		int that represents the amount of stocks to sell. If quantity is -1, 
	 * 						all of the stock quantity will be sold.
	 * @return true if succeeded, false if not.
	 */
	public Boolean sellStock(String symbol, int quantity) {
		boolean sold = false;
		Stock stock = findStock(symbol);
		if (quantity >= -1 && stock != null) {
			int currentStockQuantity = stock.getStockQuantity();
			int stockQuantityLeft = currentStockQuantity - quantity;
			if (quantity == -1) {
				stock.setStockQuantity(0);
				updateBalance(currentStockQuantity * stock.getBid());
				sold = true;
			} else if (stockQuantityLeft >= 0) {
				stock.setStockQuantity(stockQuantityLeft);
				updateBalance(quantity * stock.getBid());
				sold = true;
			} else {
				System.out.println("Not enough stocks to sell.");
			}
		}
		return sold;
	}
	
	/**
	 * A method that buys a stock to the portfolio.
	 * @param symbol		string that represents the symbol of the stock to buy.
	 * @param quantity		int that represents the amount of stocks to buy. If quantity is -1, 
	 * 						all the remaining balance will be used to buy the stock.
	 * @return true if succeeded, false if not.
	 */
	public Boolean buyStock(Stock stock, int quantity) {
		boolean bought = false;
		float ask = stock.getAsk();
		if (quantity == -1) {
			quantity = (int)(balance / ask);
			if (quantity == 0) {
				System.out.println("Not enough balance to complete purchase.");
			} else {
				bought = completeStockPurchase(stock, quantity);
			}
		} else if (ask * quantity > balance) {
			System.out.println("Not enough balance to complete purchase.");
		} else {
			bought = completeStockPurchase(stock, quantity);
		}
		return bought;
	}
	
	/**
	 * A method that completes the purchasing process of a stock.
	 * Decreases the balance and takes care of adding a new stock to the Stocks array or updating an existing stock.
	 * @param stock			Stock the stock to purchase.
	 * @param quantity		int that represents the amount of stocks to buy. 
	 * 						all the remaining balance will be used to buy the stock.
	 * @return true if succeeded, false if not.
	 */
	private Boolean completeStockPurchase(Stock stock, int quantity) {
		boolean bought = false;
		int currentStockQuantity = stock.getStockQuantity();
		float ask = stock.getAsk();
		String symbol = stock.getSymbol();
		if (findStock(symbol) != null) {
			stock.setStockQuantity(currentStockQuantity + quantity);
			updateBalance(-(ask * quantity));
			bought = true;
		} else if (addStock(stock, quantity)) {
			updateBalance(-(ask * quantity));
			bought = true;
		}
		return bought;
	}
	
	/**
	 * A method that returns a stock from the Stock array matching its symbol.
	 * @param symbol		string that represents the symbol of the stock to find.
	 * @return Stock instance if found, null if stock symbol isn't found in Stock[].
	 */
	private Stock findStock(String symbol) {
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				return stocks[i];
			}
		}
		return null;
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
		portfolioHtmlString += "<br>Total portfolio value: " + getTotalValue() + 
								"$.<br>Total stocks value: " + getStocksValue() + 
								"$. Balance: " + getBalance() + "$";
		return portfolioHtmlString;
	}
	
	/**
	 * A method to increase (or decrease, via a negative param) balance value.
	 * @param amount		float that represents how much you want to add/subtract to/from balance.
	 * @return true if succeeded, false if not.
	 */
	public Boolean updateBalance(float amount) {
		boolean updated = false;
		if (balance + amount >= 0) {
			balance += amount;
			updated = true;
		}
		return updated;
	}
	
	/**
	 * A method that returns the total value of all stocks in the portfolio.
	 * @return a float that represents the value.
	 */
	public float getStocksValue() {
		float value = 0;
		for (int i = 0; i < portfolioSize; i++) {
			value += stocks[i].getBid() * stocks[i].getStockQuantity();
		}
		return value;
	}
	
	/**
	 * A method that returns the total value the portfolio (the balance + the stocks value).
	 * @return a float that represents the value.
	 */
	public float getTotalValue() {
		return getStocksValue() + getBalance();
	}
}
