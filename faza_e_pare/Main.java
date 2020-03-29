package academy.learnprogramming;

public class Main {
    @SuppressWarnings("static-access")

    public static void main(String[] args) {
        Vigenere vig = new Vigenere();
        Numerical numerical = new Numerical();
        PlayFair pf = new PlayFair();
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

        else{
            System.out.println("Keni nje gabim ne sintakse. Shkrimi adekuat i argumenteve eshte : ");
            System.out.println("Kodet: Vigenere, Numerical,PlayFair");
            System.out.println("Metodat : encrypt, decrypt");
            System.out.println("Celesi: eshte me shkronja te alfabetit pervec se celes nuk kemi te meotda Numerical");
            System.out.println("Teksti: Tekst i cfaredoeshem");
            System.exit(1);

        }
    }


}
