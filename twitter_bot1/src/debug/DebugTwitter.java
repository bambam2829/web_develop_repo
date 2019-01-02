package debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;

import beans.UseTwitter;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class DebugTwitter {

	public static void main(String[] args) {

		try {

			List<String> toplist;
			UseTwitter ut = new UseTwitter();
			//ロケットニュース
			String url4 = "https://rocketnews24.com/";
			String className4 = "entry-title";
			toplist = ut.topixList("roket", url4, className4);
			toplist.forEach(tp -> System.out.println(tp));

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
