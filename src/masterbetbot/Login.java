package masterbetbot;

import javafx.application.Application;
import static javafx.application.Application.launch;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application{

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

public static Response showConfirmDialog( Stage owner, String title) {
    VBox vb = new VBox();
    Scene scene = new Scene( vb, 300, 100 );
     //scene.getStylesheets().add(MasterBetBot.class.getResource("projectcss.css").toExternalForm());
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
           final String usernameResult = username.getText();
           final String passwordResult = password.getText();
        });
        
        Button CancelButton = new Button("Cancel");
        CancelButton.setOnAction((ActionEvent event)->{
           final String usernameResult = username.getText();
           final String passwordResult = password.getText();
           //System.exit(0);
        });
        
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(OKButton, 0,2);
        grid.add(CancelButton, 1,2);

    bp.setCenter( grid );
    HBox msg = new HBox();
    vb.getChildren().addAll( msg, bp );
    dial.showDialog();
    return buttonSelected;
}

public static void main(String[] args) {
        launch(args);
    }
}
