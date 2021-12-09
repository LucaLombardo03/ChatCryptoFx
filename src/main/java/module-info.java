module com.example.chatcryptofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatcryptofx to javafx.fxml;
    exports com.example.chatcryptofx;
}