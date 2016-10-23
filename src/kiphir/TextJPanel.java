package kiphir;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Тиилл
 */
public class TextJPanel extends JPanel{
    JTextArea mainText;

    public TextJPanel() {
        setLayout(new BorderLayout());
        mainText = new JTextArea();
        JScrollPane scrP = new JScrollPane(mainText);
        add(scrP, BorderLayout.CENTER);
    }

    public JTextArea getMainText() {
        return mainText;
    }
    
    public JTextArea getJTextArea() {
        return mainText;
    }
    
}
