package com.t2k.javastockapp.service;

import com.t2k.javastockapp.model.*;

public class PortfolioManager {
	private Portfolio myPortfolio;
	public enum ALGO_RECOMMENDATION {
		BUY,
		SELL,
		REMOVE,
		HOLD
	}
	
	//Getters
	public Portfolio getPortfolio() {
		myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 7 Portfolio");
		myPortfolio.setBalance(10000);
		
		long date = 1418594400000L;
		String pihSymbol = "PIH";
		float pihAsk = 10.0f;
		float pihBid = 8.5f;
		Stock pihStock = new Stock(pihSymbol, pihAsk, pihBid, date);
		myPortfolio.buyStock(pihStock, 20);
		
		String aalSymbol = "AAL";
		float aalAsk = 30.0f;
		float aalBid = 25.5f;
		Stock aalStock = new Stock(aalSymbol, aalAsk, aalBid, date);
		myPortfolio.buyStock(aalStock, 30);
		
		String caasSymbol = "CAAS";
		float caasAsk = 20.0f;
		float caasBid = 15.5f;
		Stock caasStock = new Stock(caasSymbol, caasAsk, caasBid, date);		
		myPortfolio.buyStock(caasStock, 40);
		
		myPortfolio.sellStock(aalSymbol, -1);
		myPortfolio.removeStock(caasSymbol);
		
		return myPortfolio;
	}
	
	/**
	 * A method that returns a new instance of Portfolio copied from another instance.
	 * @param portfolio		Portfolio to copy.
	 * @return a new Portfolio object with the same values as the one given.
	 */
	public Portfolio duplicatePortfolio(Portfolio portfolio) {
		Portfolio copyPortfolio = new Portfolio(portfolio);
		return copyPortfolio;
	}
}
