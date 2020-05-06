package CipherPart2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class export_key {

	public static void publik(String f, String t) throws Exception {

		String sta = "C:\\Users\\besia\\Desktop\\keys\\publik\\";

		File from = new File(sta + f);
		File to = new File(sta + t);
		FileReader fr = new FileReader(from);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(to);
		String line = "";

		if (from.exists() == true) {
			while ((line = br.readLine()) != null) {

				fw.write(line);
				fw.append(System.lineSeparator());
				fw.flush(); // flush osht me kontrollu buferin ms ka met najsen mbrenda

			}

			System.out.println("U esportua me sukses ne Filen:" + to);

			br.close(); // Dohesh mi mshel se mesin null edhe munen met shti infinite loop
			fw.close();

		}

	}

	public static void privat(String f, String t) throws Exception {

		String sta = "C:\\Users\\besia\\Desktop\\keys\\privat\\";

		File from = new File(sta + f);
		File to = new File(sta + t);
		FileReader fr = new FileReader(from);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(to);
		String line;

		boolean egziston = from.exists();
		if (egziston) {
			while ((line = br.readLine()) != null) {
				fw.write(line);
				fw.append(System.lineSeparator());
				fw.flush(); // flush osht me kontrollu buferin ms ka met najsen mbrenda
			}
			System.out.println("U esportua me sukses ne Filen:" + to);

			br.close(); // Dohesh mi mshel se mesin null edhe munen met shti infinite loop
			fw.close();

		}

	}
