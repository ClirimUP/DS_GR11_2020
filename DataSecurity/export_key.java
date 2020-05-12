package CipherPart2;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
public class export_key {
	

	public static void publik(String f ,String t) throws Exception{
	
	
		String sta = "C:\\Users\\besia\\Desktop\\keys\\publik\\";

		File from = new File(sta+f);
		File to = new File(sta+t);
		FileReader fr = new FileReader(from);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(to);
		String line="";
		
			
		if(from.exists()==true) {
		while((line = br.readLine()) !=null) {
			
			fw.write(line);
			fw.append(System.lineSeparator());
			fw.flush();  //flush osht me kontrollu buferin ms ka met najsen mbrenda
			
		}
		
		
		
		
		
		
		System.out.println("U esportua me sukses ne Filen:" + to);
				
		br.close(); // Dohesh mi mshel se mesin null edhe munen met shti infinite loop
		fw.close();
		
		
		}	
		
		
	
	}
	
	
       
    
	
	public static  void privat(String f ,String t) throws Exception{
		
		
		String sta = "C:\\Users\\besia\\Desktop\\keys\\privat\\";

		File from = new File(sta+f);
		File to = new File(sta+t);
		FileReader fr = new FileReader(from);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(to);
		String line;
		
		boolean egziston = 	from.exists();
		if(egziston) {
		while((line = br.readLine()) !=null) {
			fw.write(line);
			fw.append(System.lineSeparator());
			fw.flush();  //flush osht me kontrollu buferin ms ka met najsen mbrenda
		}
		System.out.println("U esportua me sukses ne Filen:" + to);
		
		
		
		br.close(); // Dohesh mi mshel se mesin null edhe munen met shti infinite loop
		fw.close();
		
		
		
		}	
		

	
}
	
	
//	public static void importo(String from) throws IOException {
//		
//		File f = new File(from);
//		String to = "C:\\Users\\besia\\Desktop\\keys\\publik\\";
//		String leximi = readLineBy(from);
//		
//		
//		if(leximi.length() >= 10) {
//		File newPublicFile = new File(to+f.getName());
//
//          FileWriter fw = new FileWriter(newPublicFile);
//          fw.write(leximi);
//          fw.close();
//		}
//		else {
//			System.out.print("Qelsi  nuk e plotsoi kushtin nuk u kopjua ne publik ");
//		}
//
//	}
//	
//
//	public static String readLineBy(String filePath) 
	//motoda me lexu fajllin
////    {
//        StringBuilder contentBuilder = new StringBuilder();
// 
//        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
//        {
//            stream.forEach(s -> contentBuilder.append(s).append("\n"));
//        }
//        catch (IOException e) 
//        {
//            e.printStackTrace();
//        }
// 
//        return contentBuilder.toString();
//    }
//	

	
	
	
//	public static void importo(String from) throws IOException {
//		
//		
//		String to = "C:\\Users\\besia\\Desktop\\keys\\publik\\";
//		String ar = "C:\\Users\\besia\\Desktop\\keys\\privat\\";
//		File t = new File (to);
//		File f = new File(ar+from);
//		FileReader fr = new FileReader(f);
//		BufferedReader br = new BufferedReader(fr);
//		FileWriter fw = new FileWriter(to);
//		
//		
//		if(f.length() > 5 ) {
//			try {
//				copyFile(f,t);
//				System.out.println("File ishte privat dhe u kopju met njejtin emer ne folderin publik");
//			}
//			catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		
//		if(from.startsWith("http://") || from.startsWith("https://")){
//			
//			int shtatKarakteretFundit = from.length()-7;
//			
//			//  PATHI I PROFES ---- https://pastebin.com/raw/568vxV7i. (568vxV7i.) KA THON ME I BO QELS NESE FILLON ME HTTP 
//			String bodyOfpath = from.substring(shtatKarakteretFundit, from.length());  // meri 7 karakterat e fundit boni Qels
//			
//			
//			// me shkru qat body t pathit mrena pathit 
//			BufferedWriter bf = new BufferedWriter(new FileWriter(from));
//			fw.write(bodyOfpath);
//			
//			
//			bf.close();
//			fw.close();
//			
//			
//		}
		
		
		
	
		
	}


	
//	private static void copyFile(File from, File to) throws IOException {
//	    Files.copy(from.toPath(), to.toPath());
//	}
	
	
	
//}



