package EchoClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private static final String PATH_OF_XML = "/EchoClient/chat.fxml";
    public static final List<String> USER_TEST = List.of("Chak","Alex","Nik");

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(PATH_OF_XML));
        Parent root = loader.load();

        primaryStage.setTitle("Chat");
        primaryStage.setScene( new Scene(root));
        primaryStage.show();

        Network network = new Network();
        if (!network.connect()){
            System.out.println("Error network");
        }
        Controller controller = loader.getController();
        controller.setNetwork(network);
        network.waitMessega(controller);
        primaryStage.setOnCloseRequest(windowEvent -> network.close());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
