package academy.learnprogramming;

public class Numerical {
    private static String[] alfabeti = {"a","b","c","d","e","f","g","h","i","j","k","l","m",
            "n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public static int prt(String s)
    {
        int q=0;
        char t = s.charAt(0);
        q = Character.getNumericValue(t)-9;
        return q;
    }

    public static String enkodimi(String s) throws Exception
    {
        String q ="";
        String[] c = s.split("\\s");
        for (int i=0;i<c.length;i++){
            String tmp =c[i];
            if (!tmp.equals("")){
                String[] c2 = tmp.split("(?!^)");
                for (int j=0;j<c2.length;j++){
                    if (j == c2.length-1) q+= prt(c2[j]);
                    else q+= prt(c2[j]) + "-";
                }
            }
            if (i != c.length-1) q+= " ";
        }
        return q;
    }
    public static String dekodimi(String s) throws Exception
    {
        String q ="";
        String[] l = s.split("\\s");
        for (int i=0;i<l.length;i++){
            String tmp =l[i];
            if (!tmp.equals("")){
                String[] l2 = tmp.split("-");
                for (int j=0;j<l2.length;j++){
                    String tmp2 = l2[j];
                    int t = Integer.parseInt(tmp2);
                    t = t-1;
                    q += alfabeti[t];
                }
            }

            if (i != l.length-1) q+= " ";
        }
        return q;
    }

}
