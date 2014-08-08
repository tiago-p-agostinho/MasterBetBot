package masterbetbot;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class DoubleTextField extends TextField {
   
 public DoubleTextField(String euro) {
      super(euro);
      this.setAlignment(Pos.CENTER_RIGHT);
 
      addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
         @Override
         public void handle(KeyEvent event) {
            if (!isValid(getText())) {
               event.consume();
            }
         }
      });
 
      textProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(ObservableValue<? extends String> observableValue,
                             String oldValue, String newValue) {
            if(!isValid(newValue)) {
               setText(oldValue);
            }
            
         }
      });
   }
 
   private boolean isValid(String value) {
      if (value.length() == 0) {
          return true;
      }
 
      if ((value.length() - value.replace(".", "").length()) > 1) {
         return false;
      } 
      
       if (value.indexOf(".")==0) {
         return false;
      }
       
        if (value.length() > 8) {
         return false;
      }
     
        if (((value.length() - (value.indexOf(".") + 1)) > 2) && 
                value.contains(".") ) {
         return false;
      }
        
      try {
         Double.parseDouble(value);
         return true;
      } catch (NumberFormatException ex) {
         return false;
      }
   }
 
   public double getDouble() {
      try {
         return Double.parseDouble(getText());
      }
      catch (NumberFormatException e) {
         e.printStackTrace();
         return 0;
      }
   }
}
