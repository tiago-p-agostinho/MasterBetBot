package masterbetbot;

import demo.handler.ExchangeAPI;
import demo.handler.GlobalAPI;
import demo.util.APIContext;
import demo.util.Display;
import generated.exchange.BFExchangeServiceStub;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application{
    private final APIContext apiContext;
    private String usernameResult;
    private String passwordResult;
    private static ExchangeAPI.Exchange exchange;
    
    public Login(APIContext apiContext, ExchangeAPI.Exchange exchange){
        this.apiContext=apiContext;
        this.exchange = exchange; 
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
         Response showMessageDialog = showConfirmDialog(primaryStage, "Login" );
        
    }

public enum Response { OK, CANCEL };
private static Response buttonSelected = Response.CANCEL;

static class Dialog extends Stage {
    public Dialog( String title, Stage owner, Scene scene) {
        setTitle( title );
        initStyle( StageStyle.UNDECORATED );
        initModality( Modality.APPLICATION_MODAL );
        initOwner( owner );
        setResizable( false );
        setScene( scene );
    }
    public void showDialog() {
        sizeToScene();
        centerOnScreen();
        showAndWait();
    }
}

public Response showConfirmDialog( Stage owner, String title) {
    HBox hb = new HBox();
    Scene scene = new Scene( hb, 300, 100 );
    final Dialog dial = new Dialog( title, owner, scene);
    
    BorderPane bp = new BorderPane();
    
    GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        TextField username = new TextField(); 
        username.setPromptText("Username");
        PasswordField password = new PasswordField(); 
        password.setPromptText("Password");
        
        Button OKButton = new Button("OK");
        OKButton.setOnAction((ActionEvent event)->{
            usernameResult= username.getText();
            try {
            getValidation(username.getText(), password.getText());
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
            dial.hide();
            owner.show();
        });
        
        Button CancelButton = new Button("Cancel");
        CancelButton.setOnAction((ActionEvent event)->{
           System.exit(0);
        });
        
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(OKButton, 0,2);
        grid.add(CancelButton, 1,2);

    bp.setCenter( grid );
    HBox msg = new HBox();
    hb.setStyle("-fx-base: rgb(50, 50, 50);\n" +
                "-fx-background: rgb(50, 50, 50);\n" +
                "-fx-control-inner-background:  rgb(50, 50, 50);");
    hb.getChildren().addAll( msg, bp );
    dial.showDialog();
    return buttonSelected;
    }

public void getValidation(String username, String password) throws Exception{
    			
    // Perform the login before anything else.
    try{
	GlobalAPI.login(apiContext, username, password);
        System.out.println("Welcome to the Betfair API Demo " + username);
	}
    catch (Exception e){
        // If we can't log in for any reason, just exit.
        System.out.println("Welcome to the Betfair API Demo ********" + username);
	Display.showException("*** Failed to log in", e);
	System.exit(1);
		}   
    
    }

public String getUserName(){
    return usernameResult;
}

}
