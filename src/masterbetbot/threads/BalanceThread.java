package masterbetbot.threads;

import demo.handler.ExchangeAPI;
import demo.util.APIContext;
import generated.exchange.BFExchangeServiceStub;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

public class BalanceThread implements Runnable{
    final private APIContext apiContext;
    private static ExchangeAPI.Exchange exchange;
    private BFExchangeServiceStub.GetAccountFundsResp funds;
    private Label moneyField;        

    public BalanceThread(APIContext apiContext, Label textField)throws Exception {
        this.apiContext = apiContext;
        this.exchange = ExchangeAPI.Exchange.UK;
        this.funds = ExchangeAPI.getAccountFunds(exchange, apiContext);
        this.moneyField = textField;
    }
    
    @Override
    public void  run(){
        while(true){
         System.out.println("ola1");   
        double money = funds.getAvailBalance();
        String moneyString = Double.toString(money);
        System.out.println(Double.toString(money));
        System.out.println("ola2");
        moneyField.setText(moneyString);
        
        System.out.println("ola3");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BalanceThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
