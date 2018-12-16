package twitter_bot1;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.beans.util.Cache;

import beans.UseTwitter;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterTest {

	public static void main(String[] args) throws IOException {
		try {

			Document document = Jsoup.connect("http://news.livedoor.com/straight_news/").get();

			// Elements elements = document.select("a[href]");
			Elements elements = document.getElementsByClass("hasImg").select("a[href]");
			for (Element element : elements) {
				String url = element.toString();
				StringBuilder sb = new StringBuilder();
				sb.append(url);
				url = sb.substring(sb.indexOf("<a href=\""), sb.indexOf("\" "));
				url = url.replace("<a href=\"", "");
				System.out.println(url);
				UseTwitter ut = new UseTwitter();
				ut.tweet(url);
				Thread.sleep(600000);
			}

			// 個人的によく使うメソッド集約
			// UseTwitter ut = new UseTwitter();
			// ut.tweet("");

		} catch (TwitterException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

}
