import GUI.View.GUIForm;
import Services.Service;


import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Service s = new Service("POLAND");
        s.getNBPRadio();
        s.getRatioFor("EUR");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUIForm gui = new GUIForm();
                gui.setVisible(true);
                gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            }
        });

    }
}
