package GUI.View;

import Controllers.GUIController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class GUIForm extends JFrame{
    private JTextField textField1;
    private JButton getInfoButton;
    private JTextArea textArea1;
    private JPanel rootPanel;
    private JTextField wpiszPaństwoTextField;
    private JTextField wpiszWaluteTextField;
    private JButton pobierzInfoOWalucieButton;
    private GUIController controller = new GUIController();
    public GUIForm(){
        add(rootPanel, BorderLayout.NORTH);
        setTitle("Wyszukiwarka");
        setSize(600,600);
        JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.SOUTH);
        getInfoButton.addActionListener(e->{
                String city = textField1.getText();
                try {
                    controller.handleCityClick(city);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                textArea1.setText(controller.handleTextChange());
                Platform.runLater(()->{
                WebView webView = new WebView();
                webView.getEngine().load("https://en.wikipedia.org/wiki/"+city);

                jfxPanel.setScene(new Scene(webView));
            });

        });
        pobierzInfoOWalucieButton.addActionListener(e-> {
                String country = wpiszPaństwoTextField.getText();
                String currency = wpiszWaluteTextField.getText();
            try {
                controller.handleCountryClick(country,currency);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            textArea1.setText(controller.handleTextChange());
        });
    }
    private static void initAndshowGUI(){
//        WebView webView = new WebView();
//        WebEngine engine = new WebEngine("https://en.wikipedia.org/wiki/Warsaw");
//        JFXPanel panel = new JFXPanel();
//        Scene scene = new Scene(webView);
//        panel.setScene(scene);

    }
}
