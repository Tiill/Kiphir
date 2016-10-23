package kiphir;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Тиилл
 */
public class TextJFrame extends JFrame{
    private final Key currentkey;
    private final File currentfile;
    private final TextJPanel textpanel;

    public TextJFrame(Key key, File file) throws HeadlessException {
        currentkey = key;
        currentfile = file;
        
        setTitle("Кифир. Файл: " + currentfile.getName());
        setIconImage(new ImageIcon(this.getClass().getResource("/рес/KiphirIcon.png")).getImage());
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int answer = JOptionPane.showConfirmDialog(textpanel, "Сохранить?", "Закрытие файла", JOptionPane.YES_NO_OPTION);
                if(answer == JOptionPane.YES_OPTION){
                //encrypt
                OutputStream out = null;
                try {
                    out = new FileOutputStream(currentfile);
                } catch (FileNotFoundException ex) {
                }

                out = new BufferedOutputStream(out);
                try {
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, currentkey);
                    out = new CipherOutputStream(out, cipher);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                JTextArea area = textpanel.getJTextArea();
                String text = area.getText();
                try {
                    out.write(text.getBytes("Cp1251"));
                } catch (IOException ex) {
                    Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                setVisible(false);
                Kiphir.mainFrame.setVisible(true);
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        
        textpanel = new TextJPanel();
        add(textpanel);
        
        {
            InputStream in = null;
            Cipher cipher = null;
            try {
                
                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, currentkey);
                in = new BufferedInputStream(new FileInputStream(currentfile));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            byte[] buf = null;
            try {
                buf = new byte[in.available()];
            } catch (IOException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTextArea area = textpanel.getJTextArea();
            try {
                while(in.available() > 0)
                {
                    in.read(buf);
                    byte[] buf2 = cipher.doFinal(buf);
                    area.append(new String(buf2,"Cp1251"));
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(in != null)in.close();
            } catch (IOException ex) {
                Logger.getLogger(TextJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        setVisible(true);
    }
    
    
    
}
