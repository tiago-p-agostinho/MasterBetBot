package masterbetbot;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SearchBar {
   private final ObservableList<String> entries = FXCollections.observableArrayList();    
   private final ListView list = new ListView();
   private final TextField txt = new TextField();
   private final MasterBetBot master;
   
   public SearchBar(MasterBetBot master){
       this.master=master;
   }
    public void start() {  
        txt.setPromptText("Search");
        txt.textProperty().addListener(
            new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, 
                                    Object oldVal, Object newVal) {
                    handleSearchByKey((String)oldVal, (String)newVal);
                }
            });
        txt.setFocusTraversable(false);
        txt.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue){
                if (newPropertyValue){
            (master.getGridPane()).getChildren().remove(master.getTreeView());
            (master.getGridPane()).getChildren().add(master.getListView());
                }
                else{
            (master.getGridPane()).getChildren().remove(master.getListView());
            (master.getGridPane()).getChildren().add(master.getTreeView());
                }
         }
         });
        // Populate the list's entries
        for ( int i = 0; i < 100; i++ ) {
            entries.add("Item " + i);
        }
        entries.add("Eric J. Bruno");
        entries.add("Joseph Bruno");
        entries.add("Ashley Bruno");
        entries.add("Brandon Bruno");
        list.setItems( entries );
    }
     
    public void handleSearchByKey(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            list.setItems( entries );
        }
         
        // Break out all of the parts of the search text 
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
 
        // Filter out the entries that don't contain the entered text
        ObservableList<String> subentries = FXCollections.observableArrayList();
        for ( Object entry: list.getItems() ) {
            boolean match = true;
            String entryText = (String)entry;
            for ( String part: parts ) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
                 if ( match ) {
                subentries.add(entryText);
            }
        }
        list.setItems(subentries);
    }
    
    public ListView getList(){
        return list;
    }
    
    public TextField getTextField(){
        return txt;
    }
}