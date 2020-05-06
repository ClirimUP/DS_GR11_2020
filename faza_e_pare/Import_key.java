package CipherPart2;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Import_key {

	public static void importo(String from) throws IOException {

		File f = new File(from);
		String to = "C:\\Users\\besia\\Desktop\\keys\\publik\\";
		String leximi = readLineBy(from);

		if (leximi.length() > 10) {
			File newPublicFile = new File(to + f.getName());

			FileWriter fw = new FileWriter(newPublicFile);
			fw.write(leximi);
			fw.close();
		} else {
			System.out.print("Qelsi  nuk e plotsoi kushtin nuk u kopjua ne publik ");
		}

	}

	public static String readLineBy(String filePath)
	// motoda me lexu fajllin
	{
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	public static boolean eshteLink(String from) {

		if (from.startsWith("http://") || from.startsWith("https://")) {
			return true;
		}
		return false;

	}

	public static String callURL(String myURL) throws Exception {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());

				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();

				}

			}
			in.close();

		} catch (Exception e) {
			throw new Exception("Exception while calling URL : " + myURL, e);
		}
		return sb.toString();
	}
