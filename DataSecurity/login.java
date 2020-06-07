package academy.learnprogramming;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

public class login {

    public static void usercheck(String celsi) {

        File K = new File("c://U");
        K.mkdir();
        File fromfile = new File(K.getPath()+"//"+celsi+".txt");

        if(!fromfile.exists()) {
            System.out.println("Nuk eshte ne rregull");
            System.exit(1);
        }
    }

    private static RSAPublicKeySpec getPubKey(String celsi) throws ParserConfigurationException, SAXException, IOException {

        File keys = new File("c://keys");
        keys.mkdir();
        File fromfile = new File(keys.getPath()+"//"+celsi+".pub.xml");

        if(!fromfile.exists()) {
            System.out.println("Gabim! Celesi publik "+fromfile.getPath()+" nuk ekziston");
            System.exit(1);
        }

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


    public static void Kontrollo(String token) throws SAXException, IOException, NoSuchAlgorithmException,
            InvalidKeySpecException, ParserConfigurationException {

        String[] parts = token.split("\\.");
        String pl = new String(Base64.getUrlDecoder().decode(parts[1]));

        JSONObject payload = new JSONObject(pl);
        String celsi = payload.getString("sub");
        System.out.println("User: "+celsi);
        usercheck(celsi);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");

        RSAPublicKeySpec SECRET = getPubKey(celsi);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey SECRET_KEY = keyFactory.generatePublic(SECRET);

        try {

            Date now = new Date();
            Date date = Jwts.parser()
                    .setSigningKey((PublicKey)SECRET_KEY)
                    .parseClaimsJws(token).getBody().getExpiration();

            if(now.after(date)) {
                System.out.println("Nuk eshte ne rregull");
                System.exit(1);
            }

            String date_f = formatter.format(date);
            System.out.println("Ne rregull eshte!");
            System.out.println("Expiration: "+date_f);

        } catch (SignatureException e) {

            System.out.println("Nuk eshte ne rregull");
            System.exit(1);

        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------

    private static RSAPrivateKeySpec getPrivKey(String celsi) throws ParserConfigurationException, SAXException, IOException {

        File keys = new File("c://keys");
        keys.mkdir();
        File fromfile = new File(keys.getPath()+"//"+celsi+".xml");

        if(!fromfile.exists()) {
            System.out.println("Gabim! Celsi privat "+fromfile.getPath()+" nuk ekziston");
            System.exit(1);
        }

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document fromdoc = dBuilder.parse(fromfile);

        String modulus = fromdoc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponent = fromdoc.getElementsByTagName("D").item(0).getTextContent();

        byte[] decodedString = Base64.getDecoder().decode(new String(modulus).getBytes("UTF-8"));
        byte[] decodedStringu = Base64.getDecoder().decode(new String(exponent).getBytes("UTF-8"));

        BigInteger Modulus = new BigInteger(decodedString);
        BigInteger D = new BigInteger(decodedStringu);

        RSAPrivateKeySpec Secretkey = new RSAPrivateKeySpec(Modulus, D);

        return Secretkey;
    }

    public static String CJWT(String subject, PrivateKey SECRET_KEY) throws UnsupportedEncodingException {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
        Instant now = Instant.now();

        String jwt = Jwts.builder()
                .setIssuedAt(Date.from(now))
                .setSubject(subject)
                .setExpiration(Date.from(now.plus(20, ChronoUnit.MINUTES)))
                .signWith(signatureAlgorithm, SECRET_KEY)
                .compact();
        return jwt;
    }


    public static String HashPass(String celsi) throws FileNotFoundException {

        File K = new File("c://U");
        K.mkdir();
        File fromfile = new File(K.getPath()+"//"+celsi+".txt");

        if(!fromfile.exists()) {
            System.out.println("Gabim! Useri ose passwordi eshte gabim.");
            System.exit(1);
        }

        String hash=null;
        Scanner input = new Scanner(fromfile);

        while (input.hasNextLine()) {
            hash = input.nextLine();
        }
        input.close();

        return hash;
    }

    public static boolean Kotrollo(String hash) throws FileNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {

        String[] b = hash.split("\\.");
        String salt = b[0];
        String HashPw = b[1];

        Scanner input = new Scanner(System.in);
        System.out.print("Shtyp passwordin: ");
        String Pw = input.next();
        input.close();

        int iterations = 65536;
        char[] chars = Pw.toCharArray();
        byte[] saltB = Base64.getDecoder().decode(salt);

        PBEKeySpec spec = new PBEKeySpec(chars, saltB, iterations, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] newHashPwB = skf.generateSecret(spec).getEncoded();

        Base64.Encoder encoder = Base64.getEncoder();
        String newHashPw = encoder.encodeToString(newHashPwB);

        if(HashPw.equals(newHashPw)) {
            return true;
        }
        else {
            return false;
        }

    }

    //call this function
    public static void token(String celsi) throws NoSuchAlgorithmException, InvalidKeySpecException, ParserConfigurationException, SAXException, IOException {

        String SaltAndHash = HashPass(celsi);
        boolean same = Kotrollo(SaltAndHash);

        RSAPrivateKeySpec SECRET = getPrivKey(celsi);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey SECRET_KEY = keyFactory.generatePrivate(SECRET);

        if(same) {
            String token = CJWT(celsi, SECRET_KEY);
            System.out.print("Token: "+token);
        }
        else {
            System.out.println("Gabim! Useri ose passwordi eshte gabim.");
        }
    }
}