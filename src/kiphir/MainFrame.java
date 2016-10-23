package kiphir;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import update.updatebutton.UpdateButton;

/**
 *
 * @author Тиилл
 */
public class MainFrame extends JFrame{
    
    JTextField nameFile;
    JButton openButton;
    JTextField passWord;

    public MainFrame() throws HeadlessException {
        super("Кифир");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setIconImage(new ImageIcon(this.getClass().getResource("/рес/KiphirIcon.png")).getImage());
        
        
        
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 0;
        gbc.weighty = 1;
        
        JLabel label1 = new JLabel("Имя файла:");
        label1.setFont(new Font("Default", 0, 13));
        gbc.insets = new Insets(10, 30, 0, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.addLayoutComponent(label1, gbc);
        
        nameFile = new JTextField();
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 20);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 350;
        gbl.addLayoutComponent(nameFile, gbc);
        
        JLabel label2 = new JLabel("Пароль:");
        label2.setFont(new Font("Default", 0, 13));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 0, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.addLayoutComponent(label2, gbc);
        
        passWord = new JTextField();
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 20);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 350;
        gbl.addLayoutComponent(passWord, gbc);
        
        openButton = new JButton("Открыть");
        this.getRootPane().setDefaultButton(openButton);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.addLayoutComponent(openButton, gbc);
        
        UpdateButton upButton = new UpdateButton(this);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.addLayoutComponent(upButton, gbc);
        
        add(label1);
        add(nameFile);
        add(label2);
        add(passWord);
        add(openButton);
        add(upButton);
        
        initComponents();
        pack();
        setVisible(true);
    }

    private void initComponents() {
        
        nameFile.setTransferHandler(new TransferHandler(null) {

            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
//                return super.canImport(support);

                return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)
                        || support.isDataFlavorSupported(DataFlavor.stringFlavor);
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
//                return super.importData(support); //To change body of generated methods, choose Tools | Templates.

                Transferable t = support.getTransferable();
                try {
                    if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {

                        Object o = t.getTransferData(DataFlavor.javaFileListFlavor);

                        @SuppressWarnings("unchecked")
                        List<File> files = (List<File>) o;
                        if(files.size() > 1) nameFile.setText("Слишком много файлов");
                        else nameFile.setText(files.get(0).getAbsolutePath());
                    }
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                return true;
            }
        });
        
        openButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File openfile = new File(nameFile.getText());
        if(!openfile.exists())
        {
            nameFile.setText("Не существует такого файла");
            return;
        }
        if(openfile.isDirectory()){
            nameFile.setText("Не поддерживается расшифровка папок");
            return;
        }
        
        byte[] keymass = passWord.getText().getBytes();
        keymass = Arrays.copyOf(keymass, 16);
        Key key = new SecretKeySpec(keymass, "AES");
        
        Kiphir.mainFrame.setVisible(false);
        new TextJFrame(key, openfile);
        passWord.setText("");
            }
        });
    }
    
    
    
}
