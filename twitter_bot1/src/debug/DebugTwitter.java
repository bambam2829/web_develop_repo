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
		List<String> list = new ArrayList<>();

		try {

			Twitter twitter = TwitterFactory.getSingleton();
			Query query = new Query("相互");
			QueryResult result = twitter.search(query);
			int folowCnt = 1;
			for (Status status : result.getTweets()) {
				// twitter.createFriendship(status.get);
				System.out.println(status.getUser().getScreenName() + "をフォローしました。");
				folowCnt++;
			}
			while (folowCnt <= 50) {
				if (result.hasNext()) {
					query = result.nextQuery();
					result = twitter.search(query);
					for (Status status : result.getTweets()) {
						// twitter.createFriendship(status.get);
						System.out.println(status.getUser().getScreenName() + "をフォローしました。");
						folowCnt++;
					}
				}
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
