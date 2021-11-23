import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewShop extends Component {
    private JTextField TypeField;
    private JTextField BrandField;
    private JTextField PriceField;
    private JButton addClockButton;
    private JPanel MainPanel;
    private JPanel ContentPanel;
    //private JScrollPane ScrollPane;

    public ViewShop() {
        addClockButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //ContentPanel.removeAll();
                ViewClock aViewClock = new ViewClock();
                aViewClock.SetClock(new ClockHM("123", 100));
                aViewClock.setVisible(true);
                ContentPanel.add(aViewClock);
                //ScrollPane.revalidate();
                //ContentPanel.add(aViewClock);

                //TypeField.setText("Приф");
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Shop of clock");
        frame.setContentPane(new ViewShop().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setAlwaysOnTop(false);
        frame.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        //ScrollPane = new javax.swing.JScrollPane();
        ContentPanel = new javax.swing.JPanel();
        JLabel label = new JLabel("Enter username:");
        JTextField userName = new JTextField(20);

        ViewClock aViewClock = new ViewClock();
        aViewClock.SetClock(new ClockHM("123", 100));

        ContentPanel.add(label);
        ContentPanel.add(userName);
        ContentPanel.add(aViewClock);

        label.setVisible(true);
        userName.setVisible(true);
        aViewClock.setVisible(true);


        ContentPanel.setVisible(true);
        ContentPanel.repaint();


        //ScrollPane.setViewportView(ContentPanel);
    }
}
