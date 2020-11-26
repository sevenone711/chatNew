package EchoClient;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {

    @FXML
    public ListView<String> userList;

    @FXML
    public Button sendMessageButton;

    @FXML
    public TextField textField;

    @FXML
    public TextArea chatField;

    private Network network;
    Main main;

    @FXML
    public void initialize(){
        userList.setItems(FXCollections.observableArrayList(Main.USER_TEST));


        
    }
    public void sendMassege(){
        String message = textField.getText();
        appentMassege(message);
        textField.clear();

        try {
            network.getDataOutputStream().writeUTF(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setEchoClient (Main main) {
        this.main = main;
    }

    public void appentMassege (String messega) {
        chatField.appendText(messega); //Добавили текст в поле чата1.
        chatField.appendText(System.lineSeparator());  //сепаратор для того что бы с новой строки начиналось следующее сообщение!
    }

    public void setNetwork (Network network) {
        this.network = network;
    }
    public void close(){
        network.close();
        System.exit(0);
    }
}
