package kiphir;

import javax.crypto.Cipher;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * Прога для шифрования файлов. Нужна для шифрования списка паролей.
 *
 * @author Тиилл
 */
public class Kiphir {

    static final String nameprogramm = "Kiphir";
    static final double version = 0.1;
    
    static final String algorithm = "AES";
    static Cipher cipher = null;
    static MainFrame mainFrame = null;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "Проблема с Look and Feel.", "Ошибка!", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainFrame = new MainFrame();
            }
        });
    }

    public static String getNameprogramm() {
        return nameprogramm;
    }

//    static void setUp(String stringKey) throws Exception {
//
//        String keyString = "PasswordPasword";
//        byte[] keyStrict = keyString.getBytes();
//        keyStrict = Arrays.copyOf(keyStrict, 16);
//        key = new SecretKeySpec(keyStrict, "AES");
//
//        cipher = Cipher.getInstance(algorithm);
//
//    }
//
//    private static byte[] encrypt(byte[] inputBytes)
//            throws InvalidKeyException,
//            BadPaddingException,
//            IllegalBlockSizeException {
//
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//
//        return cipher.doFinal(inputBytes);
//
//    }
//
//    private static byte[] decrypt(byte[] encryptionBytes)
//            throws InvalidKeyException,
//            BadPaddingException,
//            IllegalBlockSizeException {
//
//        cipher.init(Cipher.DECRYPT_MODE, key);
//
//        byte[] recoveredBytes
//                = cipher.doFinal(encryptionBytes);
//
//        return recoveredBytes;
//
//    }
//
//    static byte[] decryptFile() {
//        FileInputStream in = null;
//        try {
//
//            in = new FileInputStream(File);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int posi;
//            while ((posi = in.read()) != -1) {
//                baos.write(posi);
//            }
//            byte[] p = baos.toByteArray();
//
//            return decrypt(p);
//        } catch (FileNotFoundException ex) {
//            JOptionPane.showMessageDialog(null, ex, "Нет такого файла!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
//        } catch (IOException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BadPaddingException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalBlockSizeException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                in.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return null;
//    }
//
//    static void encryptFile(byte[] text) {
//        FileOutputStream out2 = null;
//        try {
//            out2 = new FileOutputStream(File);
//            out2.write(encrypt(text));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BadPaddingException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalBlockSizeException ex) {
//            Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                out2.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Kiphir.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    public static double getVersion() {
        return version;
    }
}
