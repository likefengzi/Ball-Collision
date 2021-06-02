package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    @FXML
    private Pane p;

    @FXML
    private Pane b;
    //自定义线程池
    private Mypoll poll = new Mypoll(200, 200, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                poll.execute(new Thread(new Ball(b)));
            }
        });
    }

    //强制停止所有线程
    public void stop() {
        poll.shutdownNow();
    }
}
