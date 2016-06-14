import java.net.*;
import java.io.*;

public class SiteParser{
	private static final String LWN_URL = "http://lwn.net/";
	
	public static void main(String[] args){
		try{
			System.out.println(parseFullPage(LWN_URL));
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	
	
	private static String parseFullPage(String url) throws IOException{
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
}