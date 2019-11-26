package com.jindanupajit.springfx.fx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FxApplication extends Application implements Runnable{

    private String[] args;
    private Integer serverPort = 8080;
    private static Thread fx;
    private static FxApplication app;

    public FxApplication() {

    }

    public FxApplication(Integer serverPort, String[] args) {

        this.args = args;
        this.serverPort = serverPort;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Console");

        WebView webView = new WebView();
        webView.getEngine().setJavaScriptEnabled(true);
        webView.getEngine().load("http://localhost:"+serverPort+"/start");

        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/mc.jpg")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Hook!");
        }));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Close Button Clicked!");
                we.consume();
                webView.getEngine().load("http://localhost:"+serverPort+"/shutdown");
            }
        });

    }

    @Override
    public void stop(){
        System.out.println("stop(): Stage is closing");
        System.exit(0);

    }

    // Run a thread
    public static void run(Integer port, String... args)  {
        FxApplication.fx = new Thread(FxApplication.app = new FxApplication(port, args));
        FxApplication.fx.setName("FX-Thread");
        FxApplication.fx.start();
    }

    // Cli entry point
    public static void main(String[] args) {
        launch(args);
    }

    // Thread entry point
    @Override
    public void run() {

        launch(null);
    }
}
