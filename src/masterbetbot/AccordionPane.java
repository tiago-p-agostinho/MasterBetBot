package masterbetbot;

/**
 *
 * @author tiagoagostinho
 */

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

public class AccordionPane {
    final TitledPane[] tps = new TitledPane[3];
    final Accordion accordion = new Accordion ();  
    
    public void start() {                       
        Button button1 = new Button("stream TV");
        Button button2 = new Button("Bet Pane");
        Button button3 = new Button("MU Bets");
        
        tps[0]=new TitledPane("stream",button1);
        tps[1]=new TitledPane("bet",button2);
        tps[2]=new TitledPane("MU",button3);
        
        accordion.prefWidth(10);
        accordion.getPanes().addAll(tps);
    }

    public Accordion getAccordion(){
        return accordion;
    }
}
