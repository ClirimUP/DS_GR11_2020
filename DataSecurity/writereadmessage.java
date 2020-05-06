package academy.learnprogramming;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class writereadmessage {
    //https://www.codota.com/code/java/classes/java.security.spec.RSAPublicKeySpec?fbclid=IwAR0HKWftjcUujwAUDbkJEipBmx9CzcKmrOmbOEcI1CAIIfuNZ4Ab-eV3dUU
    private static Cipher enkriptimiCipher;

    public static void Encrypt(String celsi, String msg, String path) throws ParserConfigurationException,
            SAXException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException,
            org.xml.sax.SAXException {

        Base64.Encoder encoder = Base64.getEncoder();
        //na eshte kerku ti krijojme
        //https://stackoverflow.com/questions/21039602/creating-8-byte-iv-in-java
        SecureRandom sr = new SecureRandom();
        byte[] IV = new byte[8];
        sr.nextBytes(IV);
        IvParameterSpec iv = new IvParameterSpec(IV);
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();

        File keys = new File("c://keys");//gjetja e file(pathi)
        keys.mkdir();
        File fromfile = new File(keys.getPath()+"//"+celsi+".pub.xml");

        if(!fromfile.exists()) {//kushti per mos ekzistimit te celsit
            System.out.println("Gabim: Celesi publik "+celsi+" nuk ekziston.");
            System.exit(1);
        }

        //base64
        String Msgenkriptuar = encoder.encodeToString(DESencrypt(msg, iv, key));
        RSAPublicKeySpec PubKey = getPubKey(fromfile);
        String celsiEnkriptuar = encoder.encodeToString(RSAencrypt(PubKey, key));
        String part1 = encoder.encodeToString(celsi.getBytes("UTF8"));
        String part2 = encoder.encodeToString(IV);

        if(path.contains(".txt")) {

            File file = new File(path);
            PrintWriter input = new PrintWriter(file);
            input.println(part1);	//celsi
            input.println(part2);		//iv tek DES
            input.println(celsiEnkriptuar);	//celesi i DES
            input.println(Msgenkriptuar);	//msg
            input.close();
            System.out.println("Mesazhi i enkriptuar eshte ruajtur ne file-n "+"'"+path+ "'"+".");
        }
        else {
            System.out.println(part1+"."+part2+"."+celsiEnkriptuar+"."+Msgenkriptuar);
        }
    }

    private static byte[] DESencrypt(String msg, AlgorithmParameterSpec iv, SecretKey key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, ParserConfigurationException, SAXException, IOException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

        enkriptimiCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        enkriptimiCipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] input = msg.getBytes("UTF8");//i merr bytet e mesazhit
        byte[] cipherText = enkriptimiCipher.doFinal(input);//enkriptimi
        return cipherText;
    }

    private static byte[] RSAencrypt(RSAPublicKeySpec RSAPubKey, SecretKey DESKey) throws   InvalidKeyException,
            ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(RSAPubKey);//generateKey
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] input = DESKey.getEncoded();//Celsi desit(bytes)
        cipher.update(input);
        byte[] cipherText = cipher.doFinal();

        return cipherText;
    }

    private static RSAPublicKeySpec getPubKey(File fromfile) throws ParserConfigurationException,
            SAXException, IOException, org.xml.sax.SAXException {

        //https://stackoverflow.com/questions/19593661/how-documentbuilderfactorynewinstance-is-an-example-of-abstract-factory-patte
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document fromdoc = dBuilder.parse(fromfile);

        String modulus = fromdoc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponent = fromdoc.getElementsByTagName("Exponent").item(0).getTextContent();

        byte[] decodedString = Base64.getDecoder().decode(new String(modulus).getBytes("UTF-8"));
        byte[] decodedStringu = Base64.getDecoder().decode(new String(exponent).getBytes("UTF-8"));

        BigInteger Modulus = new BigInteger(decodedString);
        BigInteger Exponent = new BigInteger(decodedStringu);

        return new RSAPublicKeySpec(Modulus, Exponent);
    }

    //------------------------------------------------------------------------------------------------------------------------

    //https://www.codota.com/code/query/java.security.spec@RSAPublicKeySpec+java.security.spec@RSAPrivateKeySpec
    private static Cipher dekriptimiCipher;

    public static void Decrypt(String msg) throws ParserConfigurationException, SAXException, IOException,
            InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

        byte[] celsidekoduar;
        String celsi;
        byte[] decodediv;
        byte[] celsidekoduarencrypt;
        byte[] msgdekoduarencrypt;

        if(msg.contains(".txt")) {
            File file = new File(msg);

            if(!file.exists()) {
                System.out.println("Gabim! Nuk ekziston fajllu i dhene");
                System.exit(1);
            }

            Scanner input = new Scanner(file);
            ArrayList<String> sa = new ArrayList<String>();

            while(input.hasNextLine()) {
                sa.add(input.nextLine());
            }

            celsidekoduar = Base64.getDecoder().decode(sa.get(0));
            celsi = new String(celsidekoduar);
            decodediv = Base64.getDecoder().decode(sa.get(1));
            celsidekoduarencrypt = Base64.getDecoder().decode(sa.get(2));
            msgdekoduarencrypt = Base64.getDecoder().decode(sa.get(3));
        }

        else {
            String[] a = msg.split("\\.");
            celsidekoduar = Base64.getDecoder().decode(a[0]);
            celsi = new String(celsidekoduar);
            decodediv = Base64.getDecoder().decode(a[1]);
            celsidekoduarencrypt = Base64.getDecoder().decode(a[2]);
            msgdekoduarencrypt = Base64.getDecoder().decode(a[3]);

        }

        RSAPrivateKeySpec PriKeyRSA = getPrivKey(celsi);
        byte[] DesKey = RSAdecrypt(PriKeyRSA, celsidekoduarencrypt); //celsidekoduarencrypt
        byte[] decryptedmesazh = DESencrypt(msgdekoduarencrypt, decodediv, DesKey);

        String P = new String(decryptedmesazh, StandardCharsets.UTF_8);
        System.out.println("Marresi: "+"'"+celsi+"'");
        System.out.print("Mesazhi: "+"'"+P+"'");
    }

    private static byte[] DESencrypt(byte[] msg, byte[] IV, byte[] key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, ParserConfigurationException, SAXException, IOException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException {

        AlgorithmParameterSpec iv = new IvParameterSpec(IV);
        Key desKey = new SecretKeySpec(key, "DES");

        dekriptimiCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        dekriptimiCipher.init(Cipher.DECRYPT_MODE, desKey, iv);
        byte[] decipherText = dekriptimiCipher.doFinal(msg);

        return decipherText;
    }

    private static byte[] RSAdecrypt(RSAPrivateKeySpec PriKeyRSA, byte[] DESKey) throws  InvalidKeyException,
            ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

        Cipher cipher = Cipher.getInstance("RSA");
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(PriKeyRSA);//key generator
        cipher.init(Cipher.DECRYPT_MODE, key );
        byte[] cipherText = cipher.doFinal(DESKey);

        return cipherText;
    }

    private static RSAPrivateKeySpec getPrivKey(String celsi) throws ParserConfigurationException,
            SAXException, IOException {

        //gjeje file-n
        File keys = new File("c://keys");
        keys.mkdir();
        File fromfile = new File(keys.getPath()+"//"+celsi+".xml");

        if(!fromfile.exists()) {
            System.out.println("Gabim! Celesi privat "+"'"+fromfile.getPath()+"'"+" nuk ekziston");
            System.exit(1);
        }
        //https://stackoverflow.com/questions/19593661/how-documentbuilderfactorynewinstance-is-an-example-of-abstract-factory-patte
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document fromdoc = dBuilder.parse(fromfile);

        String modulus = fromdoc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponent = fromdoc.getElementsByTagName("D").item(0).getTextContent();

        byte[] decodedString = Base64.getDecoder().decode(new String(modulus).getBytes("UTF-8"));
        byte[] decodedStringu = Base64.getDecoder().decode(new String(exponent).getBytes("UTF-8"));

        BigInteger Modulus = new BigInteger(decodedString);
        BigInteger D = new BigInteger(decodedStringu);

        return new RSAPrivateKeySpec(Modulus, D);
    }

}
