package com.example.chatcryptofx;

import com.example.chatcryptofx.networking.MessageListener;
import com.example.chatcryptofx.networking.MessageTransmitter;
import com.example.chatcryptofx.networking.WritableGUI;
import com.example.chatcryptofx.security.AES;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller implements WritableGUI {

    @FXML private TextField hostName;
    @FXML private TextField sourcePort;
    @FXML private TextField targetPort;
    @FXML private TextArea chatBox;
    @FXML private TextField messageTextBox;

    private static final String key = "1qew38jba0x4sdez";
    AES aes = new AES(key);
    //metodo per inviare messaggio
    public void sendMessage() {
        MessageTransmitter transmitter = new MessageTransmitter(messageTextBox.getText(), hostName.getText(), Integer.parseInt(targetPort.getText()));
        transmitter.start();
    }
    //metodo per ascoltare
    public void listenPort() {
        try {
            MessageListener listener = new MessageListener(this, Integer.parseInt(sourcePort.getText()));
            listener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String str) {
        chatBox.appendText("ENCRYPTED MESSAGE\n" + str + "\n");  //stampa messaggio cryptato
        try {
            chatBox.appendText("DECRYPTED MESSAGE\n" + aes.decrypt(str) + "\n\n");  //metodo classe aes decript
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
