package masterbetbot;

import demo.handler.GlobalAPI;
import demo.util.APIContext;
import generated.global.BFGlobalServiceStub;
import generated.global.BFGlobalServiceStub.BFEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

public class TreeMarkets {
    private MasterBetBot master;
    private BFGlobalServiceStub.EventType[] types;
    private APIContext apiContext;
    private TreeItem<String> rootNode = new TreeItem<>();
    private TreeView<String> treeView = new TreeView<>(rootNode);
    private BFGlobalServiceStub.GetEventsResp resp;  
    private BFGlobalServiceStub.GetEventsResp respHandler;  
    private TreeItem<String> typeSport;
    private TreeItem<String> initialization = new TreeItem<String>(" "); 
   
    private Hashtable<String, Integer> listEventNames = new Hashtable<String, Integer>();
    
    public TreeMarkets(MasterBetBot mbb, APIContext apiContext){
        this.master=mbb;
        this.apiContext = apiContext;
    }
    
    public void start() throws Exception {       
        
        rootNode.setExpanded(true);
        treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);  
 
        TreeItem<String> depNode = new TreeItem<>();
        BFGlobalServiceStub.GetEventsResp resp;
       
        types = GlobalAPI.getActiveEventTypes(apiContext);
        for(BFGlobalServiceStub.EventType type : types){
            listEventNames.put(type.getName(), type.getId());
            typeSport = new TreeItem<>(type.getName());
            rootNode.getChildren().add(typeSport);
            typeSport.getChildren().add(initialization);
            
            typeSport.expandedProperty().addListener((ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) -> {
                preencheNodes(typeSport);
            });
            
           
          }
        
      treeView.setShowRoot(false);
      treeView.setOnMouseClicked((MouseEvent mouseEvent) -> {
         int eventID=0;
          TreeItem<String> itemLeaf;
          TreeItem<String> item; 
          
          
          if(mouseEvent.getClickCount() == 1){
            item = (TreeItem<String>) treeView.getSelectionModel().
                      getSelectedItem();
            
            preencheNodes(item);
          
         /*   if(item.getChildren().contains(initialization)){
                item.getChildren().remove(initialization);
                System.out.println("initialization removido");
            }
            
           if(listEventNames.containsKey(item.getValue())){
               eventID = listEventNames.get(item.getValue());
           }
               
            try{
            respHandler = GlobalAPI.getEvents(apiContext, eventID);
            }
            catch(Exception e){
                e.getMessage();
            }
            BFEvent[] events = respHandler.getEventItems().getBFEvent();
            if (events == null) {
                System.out.println("chegamos aos mercados!");
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
            
            for(BFEvent eventIteration : events){
                itemLeaf = new TreeItem<>(eventIteration.getEventName());
                listEventNames.put( eventIteration.getEventName(),eventIteration.getEventId());
                item.getChildren().add(itemLeaf);
                itemLeaf.getChildren().add(initialization);
                
                
               
                System.out.println(itemLeaf.getValue());
               }
             
              // Create New Tab
              Tab tabData = new Tab();
              TabPane tabPane = master.getTabPane();
              Label tabALabel = new Label(item.getValue());
              tabData.setGraphic(tabALabel);
              
             tabPane.getTabs().add(tabData); */
          }
      });
     } 

    
    public TreeView getTreeView(){
      return treeView;
    }

    public void preencheNodes(TreeItem<String> item){
        int eventID = 0;
        TreeItem<String> itemLeaf;
        
        if(item.getChildren().contains(initialization)){
                item.getChildren().remove(initialization);
                System.out.println("initialization removido");
            }
            
           if(listEventNames.containsKey(item.getValue())){
               eventID = listEventNames.get(item.getValue());
           }
               
            try{
            respHandler = GlobalAPI.getEvents(apiContext, eventID);
            }
            catch(Exception e){
                e.getMessage();
            }
            BFEvent[] events = respHandler.getEventItems().getBFEvent();
            if (events == null) {
                System.out.println("chegamos aos mercados!");
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
            
            for(BFEvent eventIteration : events){
                itemLeaf = new TreeItem<>(eventIteration.getEventName());
                listEventNames.put( eventIteration.getEventName(),eventIteration.getEventId());
                item.getChildren().add(itemLeaf);
                itemLeaf.getChildren().add(initialization);
                
                
               
                System.out.println(itemLeaf.getValue());
               }
             
              // Create New Tab
              Tab tabData = new Tab();
              TabPane tabPane = master.getTabPane();
              Label tabALabel = new Label(item.getValue());
              tabData.setGraphic(tabALabel);
              
             tabPane.getTabs().add(tabData); 
        
    }

}