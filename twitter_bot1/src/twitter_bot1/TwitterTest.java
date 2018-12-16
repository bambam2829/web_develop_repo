package twitter_bot1;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

			UseTwitter ut = new UseTwitter();
			List<String> newsList = new ArrayList<>();

			// 対象ライブドアニュース
			String url = "http://news.livedoor.com/straight_news/";
			String className = "hasImg";
			// ツイートするトピックスのURLを取得
			newsList = ut.topixUrlList(url, className);

			// 対象ヤフーニュース
			url = "https://news.yahoo.co.jp/ranking";
			className = "listFeedWrap";
			// ツイートするトピックスのURLを取得
			newsList = ut.topixUrlList(url, className);

			// リスト内シャッフル
			Collections.shuffle(newsList);

			int newsCnt = 0;
			// トピックスを一定間隔でツイート（MAX12）
			for (String list : newsList) {
				if (newsCnt <= 12) {
					ut.tweet(list);
					// Thread.sleep(3600000);
					newsCnt++;
				} else {
					break;
				}
			}


		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
