package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class UseTwitter {

	public void tweet(String str) throws TwitterException {

		// ツイート機能
		String tweetString = str; // ツイートしたい文字列
		/* インスタンスの生成 */
		Twitter twitter = new TwitterFactory().getInstance();
		/* updateの生成 これに内容を登録していく */
		/* 引数でtwitterStringを登録している */
		StatusUpdate update = new StatusUpdate(tweetString);
		/* ツイートの投稿 */
		Status status = twitter.updateStatus(update);

	}

	public List<String> topixUrlList(String targetUrl, String className) throws IOException, TwitterException {

		List<String> list = new ArrayList<>();

		// 対象サイトの情報取得
		Document document = Jsoup.connect(targetUrl).get();
		// サイトの取得する情報を抜粋
		Elements elements = document.getElementsByClass(className).select("a[href]");
		for (Element element : elements) {
			String url = element.toString();
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			url = sb.substring(sb.indexOf("<a href=\""), sb.indexOf("\" "));
			url = url.replace("<a href=\"", "");
			list.add(url);
		}
		return list;
	}

}
