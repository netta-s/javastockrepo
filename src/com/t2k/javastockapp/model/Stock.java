package com.t2k.javastockapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.t2k.javastockapp.service.PortfolioManager.ALGO_RECOMMENDATION;

/**
 * Stock Model object.
 * <p>
 * Represents a specific share of a company.
 * 
 * @author Netta Sulema
 */
public class Stock {
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private String symbol;
	private float ask;
	private float bid;
	private int stockQuantity;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	
	//C'tors	
	/**
	 * C'tor
	 * @return Stock object with default values. 
	 */
	public Stock() {
		this("undefined", -1, -1, new Date().getTime());
	}
	
	/**
	 * C'tor
	 * @param symbol	string that represents the company symbol
	 * @param ask		int that represents offer price
	 * @param bid		int that represents the bid price
	 * @param date		long that represents a date in epoch time
	 * @return Stock object with the above values. date returns as Date object. 
	 */
	public Stock(String symbol, float ask, float bid, long date) {
		this(symbol, ask, bid, ALGO_RECOMMENDATION.HOLD, -1, date);
	}
	
	/**
	 * Copy c'tor
	 * @param stock			Stock object to copy
	 * @return a new Stock object that contains the same values as the given param.
	 */
	
	public Stock(Stock stock) {
		this(new String(stock.getSymbol()), stock.getAsk(), stock.getBid(), stock.getRecommendation(), stock.getStockQuantity(), stock.getDate().getTime());
	}
	
	/**
	 * C'tor
	 * @param symbol			string that represents the company symbol
	 * @param ask				int that represents offer price
	 * @param bid				int that represents the bid price
	 * @param recommendation	ALGO_RECOMMENDATION that represents a suggested action for the given stock.
	 * @param stockQuantity		int that represents the number of stocks.
	 * @param date				long that represents a date in epoch time
	 * @return Stock object with the above values. date returns as Date object. 
	 */
	public Stock(String symbol, float ask, float bid, ALGO_RECOMMENDATION recommendation, int stockQuantity, long date) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		setRecommendation(recommendation);
		setStockQuantity(stockQuantity);
		setDate(date);
	}
	
	//Getters
	public String getSymbol() {
		return symbol;
	}

	public float getAsk() {
		return ask;
	}

	public float getBid() {
		return bid;
	}
	
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}

	public Date getDate() {
		return date;
	}

	//Setters
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setAsk(float ask) {
		this.ask = ask;
	}
	
	public void setBid(float bid) {
		this.bid = bid;
	}
	
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	public void setDate(long date) {
		this.date = new Date(date);
	}
	
	//Functions
	/**
	 * A method to return the Stock values in an HTML string form.
	 * @return an HTML string with the stock's details: symbol, ask, bid and date.
	 */
	public String getHtmlDescription() {
		String stockHtmlDetailsString = "<b>Stock symbol:</b> " + getSymbol() + 
										" <b>ask:</b> " + getAsk() + 
										" <b>bid:</b> " + getBid() + 
										" <b>date:</b> " + sdf.format(getDate());
		return stockHtmlDetailsString;
	}
}
