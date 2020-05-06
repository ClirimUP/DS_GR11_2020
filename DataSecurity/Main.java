package academy.learnprogramming;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    @SuppressWarnings("static-access")

    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
        Vigenere vig = new Vigenere();
        Numerical numerical = new Numerical();
        PlayFair pf = new PlayFair();
        export_key ek = new export_key();
        Import_key ik = new Import_key();
        writereadmessage write_message = new writereadmessage();
        createdeleteuser creatdeleteeuser =new createdeleteuser();

        String metoda = args[0];

        if (metoda.equals("vigenere") && args[1].equals("enkriptimi")) {
            try {
                System.out.println("---Ju keni zgjedhur Enkriptimin---");
                String msg = args[2];
                String key = args[3];
                Vigenere.msgAndKey(msg, key);
                Vigenere.cipherEncryption(Vigenere.message, Vigenere.finalKey);
            } catch (Exception e) {
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("Pozita1: Kodi, Pozita2: Metoda, Pozita3: meshazhi, Pozita4: celesi");
            }
        } else if(metoda.equals("vigenere")&& args[1].equals("dekriptimi")) {
                try {
                    System.out.println("---Ju keni zgjedhur Dekriptimin---");
                    String msg = args[2];
                    String key = args[3];
                    Vigenere.msgAndKey(msg, key);
                    Vigenere.cipherDecryption(Vigenere.message, Vigenere.finalKey);
                }catch (Exception e) {
                    System.out.println("Nuk keni dhene argumente te mjaftueshme");
                    System.out.println("Pozita1: Kodi, Pozita2: Metoda, Pozita3: meshazhi, Pozita4: celesi");
                }
        } else if (args[0].equals("numerical")&& args[1].equals("enkodimi")) {
            try {
                System.out.println(
                        "Enkriptimi i kodit sipas metodes Numerical: " + numerical.enkodimi(args[2]));
            } catch(Exception e) {
                System.out.println(
                        "Nuk keni dhene argumente te mjaftueshme per te realizuar ndonje kod. Shkrimi i sakte i argumenteve duhet te jete si ne vijim:");
                System.out.println("Gabim keni dhene mesazhin. Korrektim: 1.numerical 'meotoda' 'meshazhi'. ");

            }
        } else if (args[0].equals("numerical") && args[1].equals("dekodimi")) {
            try {
                System.out.println(
                        "Dekodimi sipas Kodit Numerical:" + numerical.dekodimi(args[2] ));
            } catch (Exception e) {
                System.out.println(
                        "Nuk keni dhene argumente te mjaftueshme per te realizuar ndonje kod. Shkrimi i sakte i argumenteve duhet te jete si ne vijim:");
                System.out.println("Gabim keni dhene mesazhin. Korrektim: 1.numerical 2.'meotoda' 3.'kodi'. ");
            }
        }
        else if (args[0].equals("PlayFair") && args[1].equals("enkriptimi")) {
            try {
                System.out.println( "Enkriptimi sipas Kodit PlayFair:");
                String b = args[2];
                String Source = args[3];
                pf.setKey(b);
                pf.KeyGen();
                System.out.println( "Mesazhi i enkriptuar eshte:" +pf.enkriptimi(Source));
            }catch (Exception e){
                System.out.println(
                        "Nuk keni dhene argumente te mjaftueshme per te realizuar ndonje kod. Shkrimi i sakte i argumenteve duhet te jete si ne vijim:");
                System.out.println("Gabim keni dhene mesazhin. Korrektim: 1.playfair 2.'meotoda' 3.'celsin' 3.'tekstin'. ");
            }
        }
        else if (args[0].equals("PlayFair") && args[1].equals("dekriptimi")) {
            try {
                System.out.println( "dekriptimi sipas Kodit PlayFair:");
                String keyword = args[2];
                String Source = args[3];
                pf.setKey(keyword);
                pf.KeyGen();
                System.out.println( "Mesazhi i dekriptuar eshte:" +pf.dekriptimi(Source));
            }catch (Exception e){
                System.out.println(
                        "Nuk keni dhene argumente te mjaftueshme per te realizuar ndonje kod. Shkrimi i sakte i argumenteve duhet te jete si ne vijim:");
                System.out.println("Gabim keni dhene mesazhin. Korrektim: 1.playfair 2.'meotoda' 3.'celesi' 3.'kodi'. ");
            }
        }
         else if (args[0].equals("createdeleteuser")&&args[1].equals("createuser")) {
            try {
                creatdeleteeuser.Fillfiles(args[2]);
            } catch (Exception e) {
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("Pozita1: createdeleteuser, Pozita2: createuser, Pozita3: celsi");
            }
        }else if(args[0].equals("createdeleteuser")&&args[1].equals("delete")){
            try {
                createdeleteuser.delete(args[2]);
            }catch (Exception e){
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("Pozita1: createdeleteuser, Pozita2: delete, Pozita3: celsi");

            }
        }
        else if(args[0].equals("writereadmessage")&&args[1].equals("Encrypt")){
            try{
                if(args.length==5) {
                    write_message.Encrypt(args[2], args[3], args[4]);
                } else {
                    write_message.Encrypt(args[1], args[2], "random");
                }
            }catch (Exception e){
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("Pozita1: writereadmessage, Pozita2:Encrypt");
                System.out.println("Pozita3:celsi, Pozita4: mesazhi, Poztia5:path(ose vetem a)");
            }
        }else if(args[0].equals("writereadmessage")&&args[1].equals("Decrypt")){
            try {
                write_message.Decrypt(args[2]);
            }catch (Exception e){
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("Pozita1: writereadmessage, Pozita2:Decrypt, Pozita3:text i enkriptuar");
            }
        }else if (args[0].equals("export_key") && args[1].equals("publik")) {
            try {
                System.out.println("--Qelsi i zgjedhur eshte publik---");
                String from = args[2];
                String to = args[3];
                export_key.publik(from,to);
            } catch (Exception e) {
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("File nuk egziston ose ka ndonje gabim ");
            }
        }
        else if (metoda.equals("export_key") && args[1].equals("privat")) {
            try {
                System.out.println("--Qelsi i zgjedhur eshte privat---");
                String from = args[2];
                String to = args[3];
                export_key.privat(from,to);
            } catch (Exception e) {
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("File nuk egziston ose ka ndonje gabim ");
            }
        }
        else if (metoda.equals("Import_key") && args[1].equals("importo")) {
            try {
                System.out.println("--importi--");
                String from = args[2];
                Import_key.importo(from);
            } catch (Exception e) {
                System.out.println("Nuk keni dhene argumente te mjaftueshme");
                System.out.println("File nuk egziston ose ka ndonje gabim ");
            }
        }

        else{
            System.out.println("Keni nje gabim ne sintakse. Shkrimi i argumenteve nuk eshte ne rregull! ");
            System.out.println("Rishikoni edhe njeher argumentet!");
            System.exit(1);
        }
    }
       
}

