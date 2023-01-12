package western.covid.project.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * A networking tool to retrieve data from internet and parse it.
 * @author Tianci Du
 * @version 1.0
 */
public class JsonTools {

	/**
	 * Retrieve data from the server and parse into object for easily handling.
	 * @param country
	 * @return
	 */
	public static double retrieveData(String country) {
		String urlString = String.format("https://api.covid19api.com/total/dayone/country/%s/status/confi rmed",
				country);
		System.out.println(urlString);
		int cases = 0;

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				cases = jsonArray.get(size - 1).getAsJsonObject().get("Cases").getAsInt();
				System.out.println("Cases: " + cases);
				return cases;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
