package com.t2k.javastockapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stock {
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	
	//C'tor
	public Stock(String symbol, float ask, float bid, Date date) {
		setSymbol(symbol);
		setAsk(ask);
		setBid(bid);
		long milliseconds = date.getTime(); 
		setDate(new Date(milliseconds));
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
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getHtmlDescription() {
		String stockHtmlDetailsString = "<b>Stock symbol:</b> " + getSymbol() + 
										" <b>ask:</b> " + getAsk() + 
										" <b>bid:</b> " + getBid() + 
										" <b>date:</b> " + sdf.format(getDate());
		return stockHtmlDetailsString;
	}
}
