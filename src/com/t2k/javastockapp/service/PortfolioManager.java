package com.t2k.javastockapp.service;

import com.t2k.javastockapp.model.*;

public class PortfolioManager {
	private Portfolio portfolio;
	
	//Getters
	public Portfolio getPortfolio() {
		portfolio = new Portfolio();
		
		long date = 1416009600000L;
		String pihSymbol = "PIH";
		float pihAsk = 13.1f;
		float pihBid = 12.4f;
		Stock pihStock = new Stock(pihSymbol, pihAsk, pihBid, date);
		portfolio.addStock(pihStock);
		
		String aalSymbol = "AAL";
		float aalAsk = 5.78f;
		float aalBid = 5.5f;
		Stock aalStock = new Stock(aalSymbol, aalAsk, aalBid, date);
		portfolio.addStock(aalStock);
		
		String caasSymbol = "CAAS";
		float caasAsk = 32.2f;
		float caasBid = 31.5f;
		Stock caasStock = new Stock(caasSymbol, caasAsk, caasBid, date);		
		portfolio.addStock(caasStock);
		
		portfolio.setTitle("My Portfolio");
		
		return portfolio;
	}
}
