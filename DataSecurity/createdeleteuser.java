package academy.learnprogramming;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class createdeleteuser {


    public static void delete(String celsi) {

        userD(celsi);

        File keys = new File("c://keys");
        keys.mkdir();

        File filePub = new File(keys.getPath()+"//"+celsi+".pub.xml");//celsit pub
        File file = new File(keys.getPath()+"//"+celsi+".xml");//celsi

        if(file.exists() && filePub.exists()) { //vlen per te dy llojet e celsave

            if(filePub.delete() && file.delete()) {
                System.out.println("Celsi privat eshte larguar "+"'"+file.getPath()+"'");
                System.out.println("Celsi publik eshte larguar "+"'"+filePub.getPath()+"'");
            }
            else {
                System.out.println("Largimi i celsit nuk mund te ndodh");
            }
        }
        else if(filePub.exists()) {

            if(filePub.delete()) {
                System.out.println("Celsi publik eshte larguar "+"'"+filePub.getPath()+"'");
            }
            else {
                System.out.println("Largimi i celsit nuk munde te ndodh");
            }
        }

        else if(file.exists()) {
            if(file.delete()) {
                System.out.println("Celsi privat eshte larguar "+"'"+file.getPath()+"'");
            }
            else {
                System.out.println("Largimi i celsit nuk munde te ndodh");
            }
        }
        else {
            System.out.println("Gabim! Celesi: " + "'"+celsi+ "'" + " - nuk ekziston.");
        }
    }
    public static void userD(String celsi) {

        File K = new File("c://U");
        K.mkdir();

        File file = new File(K.getPath()+"//"+celsi+".txt");

        if(!file.exists()) {
            System.out.println("Useri "+celsi+" nuk ekziston");
            System.exit(1);
        }

        if(file.delete()) {
            System.out.println("Useri "+celsi+" eshte fshire");
        }

    }

//-----------------------------------------------------------------------------------------------------------------------

    private static RSAPublicKeySpec PubKeyRSA = null;
    private static RSAPrivateKeySpec PriKeyRSA = null;
    private static RSAPrivateCrtKey PrivRsaKeyCt = null;

    private static byte[] getSalt() throws NoSuchAlgorithmException {

        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String StrongPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        int iteration = 65536;
        char[] karakteret = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(karakteret, salt, iteration, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(salt)+"."+encoder.encodeToString(hash);
    }

    public static void user(String celsi) throws FileNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {


        File K = new File("c://U");
        K.mkdir();

        Scanner input = new Scanner(System.in);

        System.out.print("Shenoni passwordin: ");
        String passwordi = input.next();

        String simbolet = "0123456789!@#$%^&*()_+[];',./?><|:}{";
        int k=0;

        if(passwordi.length()<6) {
            System.out.println("Gabim! Passwordi duhet ti ket se paku 6 karaktere.");
            System.exit(1);
        }

        for(int i=0; i<passwordi.toCharArray().length; i++) {
            for(int j=0; j<simbolet.toCharArray().length; j++) {
                if(((passwordi.toCharArray())[i] == simbolet.toCharArray()[j])) {

                    k++;
                }
            }
        }


        System.out.print("Ju lutem shenoni perseri passwordin: ");
        String passwordiRi = input.next();

        input.close();

        if(!passwordi.equals(passwordiRi)) {
            System.out.println("Gabim! Passwordi nuk eshte i njejte.");
            System.exit(1);
        }

        if(k==0) {
            System.out.println("Gabim! Passwordi duhet te pket se paku nje numer ose simbol.");
            System.exit(1);
        }

        String hash = StrongPassword(passwordi);
        File file = new File(K.getPath()+"//"+celsi+".txt");
        PrintWriter pw = new PrintWriter(file);
        pw.println(hash);

        System.out.println("Shfrytzuesi i krijuar: "+celsi);

        pw.close();
    }

    public createdeleteuser() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KEY();      //gjeneremi i key pair RSA
    }

    //metoda per gjenerimin e Key pair RSA
    public void KEY() throws NoSuchAlgorithmException, InvalidKeySpecException {

        //http://tutorials.jenkov.com/java-cryptography/keypairgenerator.html
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PubKeyRSA = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        PriKeyRSA = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
        PrivRsaKeyCt = createCtKey(PubKeyRSA, PriKeyRSA);
    }

    //Chinese remainder theorem
    //http://staff.cc/java/entrust/javadocs/java/security/interfaces/RSAPrivateCrtKey.html
    //e huazur http://www.cs.utsa.edu/~wagner/laws/ARSAFast.html
    private static BigInteger findFactor(BigInteger e, BigInteger d, BigInteger n) {

        BigInteger edMinus1 = e.multiply(d).subtract(BigInteger.ONE);
        int s = edMinus1.getLowestSetBit();
        BigInteger t = edMinus1.shiftRight(s);

        for (int aInt = 2; true; aInt++) {
            BigInteger aPow = BigInteger.valueOf(aInt).modPow(t, n);
            for (int i = 1; i <= s; i++) {
                if (aPow.equals(BigInteger.ONE)) {
                    break;
                }
                if (aPow.equals(n.subtract(BigInteger.ONE))) {
                    break;
                }
                BigInteger aPowSquared = aPow.multiply(aPow).mod(n);

                if (aPowSquared.equals(BigInteger.ONE)) {
                    return aPow.subtract(BigInteger.ONE).gcd(n);
                }
                aPow = aPowSquared;
            }
        }

    }

    //https://www.codota.com/code/query/java.security.spec@RSAPublicKeySpec+java.math@BigInteger
    //Metoda per krijimin e Celesit privat(Chinese remainder theorem)
    private static RSAPrivateCrtKey createCtKey(RSAPublicKeySpec rsaPubSpec, RSAPrivateKeySpec rsaPrivSpec) throws NoSuchAlgorithmException, InvalidKeySpecException {

        BigInteger e = rsaPubSpec.getPublicExponent();
        BigInteger d = rsaPrivSpec.getPrivateExponent();
        BigInteger n = rsaPubSpec.getModulus();
        BigInteger p = findFactor(e, d, n);
        BigInteger q = n.divide(p);

        if (p.compareTo(q) > 0) {
            BigInteger t = p;
            p = q;
            q = t;
        }

        BigInteger exp1 = d.mod(p.subtract(BigInteger.ONE));
        BigInteger exp2 = d.mod(q.subtract(BigInteger.ONE));
        BigInteger coeff = q.modInverse(p);
        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(n, e, d, p, q, exp1, exp2, coeff);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        return (RSAPrivateCrtKey) kf.generatePrivate(keySpec);

    }

    //Kjo metod ruan celsat ne xml files
    public static void Fillfiles(String celsi) throws ParserConfigurationException, TransformerException, FileNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {

       user(celsi);

        Base64.Encoder encoder = Base64.getEncoder();
        //https://developers.google.com/j2objc/javadoc/jre/reference/java/security/spec/RSAPublicKeySpec
        //https://stackoverflow.com/questions/19593661/how-documentbuilderfactorynewinstance-is-an-example-of-abstract-factory-patte
        DocumentBuilderFactory docFactoryPub = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilderPub = docFactoryPub.newDocumentBuilder();

        Document docPub = docBuilderPub.newDocument();
        Element rootElementPub = docPub.createElement("RSAKeyValue");
        docPub.appendChild(rootElementPub);
        //elementet mbrenda celsit te ruajtur ne format xml
        Element ModulusPub = docPub.createElement("Modulus");
        ModulusPub.appendChild(docPub.createTextNode(encoder.encodeToString(PubKeyRSA.getModulus().toByteArray())));
        rootElementPub.appendChild(ModulusPub);
        //Modulus dhe Exponenti
        Element ExponentPub = docPub.createElement("Exponent");
        ExponentPub.appendChild(docPub.createTextNode(encoder.encodeToString(PubKeyRSA.getPublicExponent().toByteArray())));
        rootElementPub.appendChild(ExponentPub);

        TransformerFactory transformerFactoryPub = TransformerFactory.newInstance();
        Transformer transformerPub = transformerFactoryPub.newTransformer();
        transformerPub.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource sourcePub = new DOMSource(docPub);

        //celsi
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("RSAKeyValue");
        doc.appendChild(rootElement);
        Element Modulus = doc.createElement("Modulus");
        Modulus.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getModulus().toByteArray())));
        rootElement.appendChild(Modulus);
        Element Exponent = doc.createElement("Exponent");
        Exponent.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getPublicExponent().toByteArray())));
        rootElement.appendChild(Exponent);

        //krijimi i elementeve brenda celsave
        Element P = doc.createElement("P");
        P.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getPrimeP().toByteArray())));
        rootElement.appendChild(P);
        Element Q = doc.createElement("Q");
        Q.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getPrimeQ().toByteArray())));
        rootElement.appendChild(Q);
        Element DP = doc.createElement("DP");
        DP.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getPrimeExponentP().toByteArray())));
        rootElement.appendChild(DP);
        Element DQ = doc.createElement("DQ");
        DQ.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getPrimeExponentQ().toByteArray())));
        rootElement.appendChild(DQ);
        Element InverseQ = doc.createElement("InverseQ");
        InverseQ.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getCrtCoefficient().toByteArray())));
        rootElement.appendChild(InverseQ);
        Element D = doc.createElement("D");
        D.appendChild(doc.createTextNode(encoder.encodeToString(PrivRsaKeyCt.getPrivateExponent().toByteArray())));
        rootElement.appendChild(D);

        //transformimi prej .txt ne .xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);

        File keys = new File("c://keys"); //krijon nje folder me emer keys
        keys.mkdir();
        File filePub = new File(keys.getPath() + "//" + celsi + ".pub.xml");
        File file = new File(keys.getPath() + "//" + celsi + ".xml");

        if (file.exists() || filePub.exists()) {
            System.out.println("Gabim! Celesi i dhene " + celsi + " ekziston paraprakisht.");
            System.exit(1);
        }

        StreamResult result = new StreamResult(file);
        System.out.println("Celesi privat eshte krijuar: " + file.getPath());
        transformer.transform(source, result);

        StreamResult resultPub = new StreamResult(filePub);
        System.out.println("Celesi publik eshte krijuar: " + filePub.getPath());
        transformerPub.transform(sourcePub, resultPub);
    }

}
