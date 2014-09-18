package demo;
import java.util.*;
import java.text.*;
import java.lang.*;
import java.math.*;


import demo.handler.ExchangeAPI;
import demo.handler.GlobalAPI;
import demo.handler.ExchangeAPI.Exchange;
import demo.util.APIContext;
import demo.util.Display;
import demo.util.InflatedCompleteMarketPrices;
import demo.util.InflatedMarketPrices;
import generated.exchange.BFExchangeServiceStub.BetCategoryTypeEnum;
import generated.exchange.BFExchangeServiceStub.BetPersistenceTypeEnum;
import generated.exchange.BFExchangeServiceStub.BetTypeEnum;
import generated.exchange.BFExchangeServiceStub.CancelBets;
import generated.exchange.BFExchangeServiceStub.CancelBetsResult;
import generated.exchange.BFExchangeServiceStub.GetAccountFundsResp;
import generated.exchange.BFExchangeServiceStub.MUBet;
import generated.exchange.BFExchangeServiceStub.Market;
import generated.exchange.BFExchangeServiceStub.PlaceBets;
import generated.exchange.BFExchangeServiceStub.PlaceBetsResult;
import generated.exchange.BFExchangeServiceStub.Runner;
import generated.exchange.BFExchangeServiceStub.UpdateBets;
import generated.exchange.BFExchangeServiceStub.UpdateBetsResult;

import generated.global.BFGlobalServiceStub.BFEvent;
import generated.global.BFGlobalServiceStub.EventType;
import generated.global.BFGlobalServiceStub.GetEventsResp;
import generated.global.BFGlobalServiceStub.MarketSummary;
import org.apache.log4j.*;

import demo.util.InflatedCompleteMarketPrices.InflatedCompletePrice;
import demo.util.InflatedCompleteMarketPrices.InflatedCompleteRunner;
import demo.util.InflatedMarketPrices.InflatedPrice;
import demo.util.InflatedMarketPrices.InflatedRunner;

public class APIDemo {
   
	// The session token
	private static APIContext apiContext = new APIContext();
	
	// the current chosen market and Exchange for that market
	private static Market selectedMarket=null;
	private static ArrayList<Market> selectedMarkets= new ArrayList<Market>();
	private static ArrayList<Integer> coleccaoIDs = new ArrayList<Integer>();
	
	// odd inferior
	private static double minprice = 1.25;
	private static double priceapostar = 1.02;
	// stake inicial
	private static double size = 2;
	private static double actualmoney = 0;
	private static double valorAposta = size;
	
	// tempo em milisegundos de um dia
	private static long umDia = 24*60*60*1000;
	
	// esta igualdade serve para quando o programa arrancar ir buscar os mercados disponiveis
	private static long miliseg = umDia;
	
	// quinze minutos em milisegundos (intervalo de tempo para mostar o saldo)
	private static long quinzemin = 15*60*1000;
	private static long quiliseg = quinzemin;
	
	// exchange
	private static Exchange selectedExchange=Exchange.UK;

	
	private static String horaSistema1 = "01:25";
	private static String horaSistema2 = "01:26";
	private static String horaSistema3 = "01:27";
	
	// Fire up the API demo
	public static void main(String[] args)  throws Exception {
	
		// Initialise logging and turn logging off. Change OFF to DEBUG for detailed output.
		Logger rootLog = LogManager.getRootLogger();
		Level lev = Level.toLevel("OFF");
		rootLog.setLevel(lev);
		
		String username="";
		String password="";
			
		// Perform the login before anything else.
		try
		{
			GlobalAPI.login(apiContext, username, password);
			System.out.println("Welcome to the Betfair API Demo " + username);
		}
		catch (Exception e)
		{
			// If we can't log in for any reason, just exit.
			Display.showException("*** Failed to log in", e);
			System.exit(1);
		}
		
		boolean finished = false;
		while (!finished) {
			try	{
					// mostra o saldo a quinze minutos
					if((System.currentTimeMillis() - quiliseg)>= quinzemin){
						quiliseg = System.currentTimeMillis();
						showAccountFunds(selectedExchange);
					}
					
					Date dateSystem = new Date();
					SimpleDateFormat formato = new SimpleDateFormat("HH:mm");  
					String formattedDate = formato.format(dateSystem);
				
				
					//horaSistema1.equals(dateSystem)||horaSistema2.equals(dateSystem)||horaSistema3.equals(dateSystem)
					if((System.currentTimeMillis() - miliseg)>=umDia){
						miliseg = System.currentTimeMillis();
						selectedMarkets= new ArrayList<Market>();
						coleccaoIDs = new ArrayList<Integer>();
						selectMarkests();
					}
						
					viewMarketAlterado();
					}
			 catch (Exception e) {
				// Print out the exception and carry on.
				Display.showException("*** Failed to call API", e);
			}
		}
	
		
		// Logout before shutting down.
		try
		{
			GlobalAPI.logout(apiContext);
		}
		catch (Exception e)
		{
			// If we can't log out for any reason, there's not a lot to do.
		}
		Display.println("Logout successful");
	}		Display.showException("Failed to log out", e);
	

		// Get Balance
		public static void showAccountFunds(Exchange exch) throws Exception {
			GetAccountFundsResp funds = ExchangeAPI.getAccountFunds(exch, apiContext);
			double dez = 10;
			double auxprice = funds.getBalance()/dez;
			if(auxprice>=size){
				BigDecimal bd = new BigDecimal(auxprice);
				BigDecimal rounded = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				valorAposta=rounded.doubleValue();
			}
			else{
				valorAposta = size;
			}
			System.out.println("***************************************************");
			System.out.println("*************   Balance               : " + funds.getBalance() + " *****");
			
			actualmoney = funds.getAvailBalance();
			System.out.println("*************   Balance  Available    : " + actualmoney + "  *****");
			System.out.println("*************   Actual size  	      : " + valorAposta + "   *****");
			System.out.println("***************************************************");
			
				
		}
		
		
		// return all markets
		public static void selectMarkests() throws Exception{
			EventType[] types = GlobalAPI.getActiveEventTypes(apiContext);
			int eventId=0;
			String futebol = new String("Futebol- Fixtures");
			
			// Get Id of football Events
			for(int i=0; i<types.length; i++){
				if(futebol.equals(types[i].getName()))
					eventId = types[i].getId();
			}
	
			//create Date object
			Date date = new Date();
			Date date1 = new Date();
			Date date2 = new Date();
			
			//get the day and month of date
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM");
			String dateToday = sdf.format(date); 
   
			//get the day and month of date
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMMM   ");
			String dateToday1 = sdf1.format(date1); 
   
			//get the day and month of date
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM      ");
			String dateToday2 = sdf2.format(date1); 
   
			
			GetEventsResp resp = GlobalAPI.getEvents(apiContext, eventId);
			BFEvent[] events = resp.getEventItems().getBFEvent();
			if (events == null) {
				events = new BFEvent[] {};
			} else {
			ArrayList<BFEvent> nonCouponEvents = new ArrayList<BFEvent>(events.length);
			for(BFEvent e: events) {
				if(!e.getEventName().equals("Coupons")) {
					nonCouponEvents.add(e);
				}
			}
			events = (BFEvent[]) nonCouponEvents.toArray(new BFEvent[]{});
		}
		
			//Get the actual date
			for (int i = 0; i < events.length; i++){
				if(dateToday.equals(events[i].getEventName())){
				eventId = events[i].getEventId();
				}
			}
		
			//Get the actual date
			for (int i = 0; i < events.length; i++){
				if(dateToday1.equals(events[i].getEventName())){
				eventId = events[i].getEventId();
				}
			}
			
			//Get the actual date
			for (int i = 0; i < events.length; i++){
				if(dateToday2.equals(events[i].getEventName())){
				eventId = events[i].getEventId();
				}
			}
			
			// get all games of the day
			resp = GlobalAPI.getEvents(apiContext, eventId);
			events = resp.getEventItems().getBFEvent();
			if (events == null) {
				events = new BFEvent[] {};
			} else {
				ArrayList<BFEvent> nonCouponEvents = new ArrayList<BFEvent>(events.length);
				for(BFEvent e: events) {
					if(!e.getEventName().equals("Coupons")) {
						nonCouponEvents.add(e);
					}
				}
				events = (BFEvent[]) nonCouponEvents.toArray(new BFEvent[]{});
			}

			//adquire o id de todos os jogos do dia
			int eventIds[] = new int[events.length];
			for (int i = 0; i < events.length; i++){
				eventIds[i] = events[i].getEventId();
				System.out.println(events[i].getEventName());	
			}
	
			MarketSummary[] markets = null;
			String prob = new String("Probabilidades");

			//Para todos os jogos de futebol do dia selecciona o id do mercado de probabilidades
			for(int i=0; i<eventIds.length; i++){
				resp = GlobalAPI.getEvents(apiContext, eventIds[i]);
				markets = resp.getMarketItems().getMarketSummary(); 
				if(markets==null){
					System.out.println("markets nulo!");
				}
				
				for(int j=0; j<markets.length; j++){
					if(prob.equals(markets[j].getMarketName())){
						selectedMarket = ExchangeAPI.getMarket(selectedExchange, apiContext, markets[j].getMarketId());
						selectedMarkets.add(selectedMarket);
						System.out.println(selectedMarket.getMarketId());
			
						//temporizador - a fun��o getMarket s� pode ser chamada 5 vezes por minuto
						Thread.sleep( 12 * 1100 );
					}
				}
            }
        }
	

		// Retrieve and view information about the selected market
		private static void viewMarketAlterado() throws Exception {
			
			for(Market m: selectedMarkets){
				InflatedMarketPrices prices = ExchangeAPI.getMarketPrices(selectedExchange, apiContext, m.getMarketId());
				
				for (InflatedRunner r: prices.getRunners()) {
					Runner marketRunner = null;
					for (Runner mr: m.getRunners().getRunner()) {
						if (mr.getSelectionId() == r.getSelectionId()) {
							marketRunner = mr;
						break;
					}
				}
					boolean testacoleccao = coleccaoIDs.contains(marketRunner.getSelectionId());
					double apostaback = r.getLastPriceMatched();
				    double price = 1.40;
				    int zero = 0;
				    if ((apostaback >minprice)&&(apostaback <=price)&&(!testacoleccao)&&(prices.getInPlayDelay()!=zero)) {
							System.out.println("Last Price Matched " + apostaback);
							System.out.println(marketRunner.getName());
							System.out.println("Market Information: " + prices.getMarketInformation());
							System.out.println("--------");
							
							
							 GetAccountFundsResp funds = ExchangeAPI.getAccountFunds(selectedExchange, apiContext);
							 double actualmoneynow = funds.getAvailBalance();
							//coleccaoIDs.add(r.getSelectionId());
							if(actualmoneynow>=size){
								System.out.println("Vou apostar no "+ marketRunner.getName());
								placeBetAlterado(m,marketRunner, r);
							}
							else{
								System.out.println("Tou sem dinheiro para apostar!");
							}
							Thread.sleep(4*1100);
						}
							Thread.sleep(1100);
					}
				}
			}
		
	
		// Place a bet on the specified market.
		private static void placeBetAlterado(Market m, Runner r, InflatedRunner infrun) throws Exception {
		
			double zerozero = 0.0;
			int cinco = 5;
			for (int tentativa = 0; tentativa<cinco; tentativa++){
				// Set up the individual bet to be placed
				PlaceBets bet = new PlaceBets();
				bet.setMarketId(m.getMarketId());
				bet.setSelectionId(r.getSelectionId());
				bet.setBetCategoryType(BetCategoryTypeEnum.E);
				bet.setBetPersistenceType(BetPersistenceTypeEnum.NONE);
			    bet.setBetType(BetTypeEnum.B);
				bet.setPrice(infrun.getLastPriceMatched());
				bet.setSize(valorAposta);
				
				
					// We can ignore the array here as we only sent in one bet.
					PlaceBetsResult betResult = ExchangeAPI.placeBets(selectedExchange, apiContext, new PlaceBets[] {bet})[0];
					
					if((betResult.getSuccess())&&(betResult.getAveragePriceMatched()!= zerozero)) {
						Display.println("Bet "+ betResult.getBetId() +" "+ r.getName() + " " + " placed. "+betResult.getSizeMatched() +" matched @ "+betResult.getAveragePriceMatched());
						coleccaoIDs.add(r.getSelectionId());
						break;
					} else {
						Display.println("A aposta vai ser removida");
						cancelBet(betResult);
					}
				}
		}
		
		// Place a bet on the specified market.
		private static void cancelBet(PlaceBetsResult bet) throws Exception {
		
					CancelBets canc = new CancelBets();
					canc.setBetId(bet.getBetId());
					
					// We can ignore the array here as we only sent in one bet.
					CancelBetsResult betResult = ExchangeAPI.cancelBets(selectedExchange, apiContext, new CancelBets[] {canc})[0];
					
					if (betResult.getSuccess()) {
						Display.println("Bet "+betResult.getBetId()+" cancelled.");
					} else {
						Display.println("Failed to cancel bet: Problem was: "+betResult.getResultCode());
					}
				}			
}