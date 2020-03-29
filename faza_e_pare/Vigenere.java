package academy.learnprogramming;

public class Vigenere {

    public static String message;
    public static String finalKey;

    public static void cipherDecryption(String message, String finalKey) {
        int[][] table = createVigenereTable();
        String decryptedText = "";

        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && finalKey.charAt(i) == (char)32){
                decryptedText += " ";
            } else {
                decryptedText += (char)(65 + itrCount((int)finalKey.charAt(i), (int)message.charAt(i)));
            }
        }

        System.out.println("Teksti i Dekriptuar: " + decryptedText);
    }

    public static int itrCount(int key, int msg) {
        // kthen numerimin e e shkronjave te celesit per ta arritur numrin e chipher tekstit
        // ky funksion kalkulon dekriptimin e tekstit te enkriptuar
        int counter = 0;
        String result = "";
        for (int i = 0; i < 26; i++) {
            if(key+i > 90){
                //90 being ASCII of Z
                result += (char)(key+(i-26));
            } else {
                result += (char)(key+i);
            }
        }

        for (int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == msg){
                break;
            } else {
                counter++;
            }
        } return counter;
    }

    public static void cipherEncryption(String message, String finalKey) {
        int[][] table = createVigenereTable();
        String encryptedText = "";
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && finalKey.charAt(i) == (char)32){
                encryptedText += " ";
            } else {
                encryptedText += (char)table[(int)message.charAt(i)-65][(int)finalKey.charAt(i)-65];
                //qasja e elemntin table[i][j] per ta eknkriptu mesazhin
            }
        }
        System.out.println("Teksti i Enkriptuar: " + encryptedText);
    }

    public static int[][] createVigenereTable() {
        // tabela e Vigeneres 26x26 me shrkonja alfbeti
        int[][] tableArr = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int temp;
                if((i+65)+j > 90){
                    temp = ((i+65)+j) -26;
                    tableArr[i][j] = temp;
                } else {
                    temp = (i+65)+j;
                    tableArr[i][j] = temp;
                }
            }
        }
        return tableArr;
    }

    public static void msgAndKey(String msg, String key) {
        msg = msg.toUpperCase();
        key = key.toUpperCase();

        String keyMap = "";
        //qasja e qelsit ne mesazh
        for (int i = 0, j = 0; i < msg.length(); i++) {
            if(msg.charAt(i) == (char)32){ //injorimi i hapsirave
                keyMap += (char)32;
            } else {
                if(j < key.length()){
                    keyMap += key.charAt(j);
                    j++;
                } else {//rifillimi i celsit pasi te ket perfundu nje here
                    j = 0;
                    keyMap += key.charAt(j);
                    j++;
                }
            }
        }
        message = msg;
        finalKey = keyMap;
    }
}
