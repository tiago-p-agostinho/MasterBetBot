package masterbetbot;

import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author tiagoagostinho
 */
public class ScrollPaneForAccordionPane{
    final ScrollPane sp = new ScrollPane();
    
    public void start() {
        AccordionPane ap = new AccordionPane();
        ap.start();
        Accordion accordion = ap.getAccordion();
       
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setPrefSize(200, 600);
        sp.setContent(accordion);
    }
 
    public ScrollPane getScrollPane(){
       return sp;
    }
}
