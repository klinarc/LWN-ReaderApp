package klinar.kronlachner.binder.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.net.*;
import java.io.*;


public class SiteParser{
	private static final String LWN_URL = "http://lwn.net/";
	
	
	public static String parseFullPage(String url) throws IOException{
		String currentLine = "";
		String content = "";
		BufferedReader in = null;
		try{
			URL page = new URL(url);
			in = new BufferedReader(new InputStreamReader(page.openStream()));
			while ((currentLine = in.readLine()) != null) {
				content += currentLine;
			}
		}catch(MalformedURLException e){
			e.printStackTrace();
		}finally {
			if (in != null) {
				in.close();
			}
		}

		return content;
	}

	public static String testJSoup(String html){
		Document doc = Parser.parse(html, LWN_URL);
		return doc.title();
	}
}