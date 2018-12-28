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


import beans.UseTwitter;
import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterTest {

	public static void main(String[] args) throws IOException {
		try {

			UseTwitter ut = new UseTwitter();
			List<String> toplist;

			// 対象ライブドアニュース
			String url1 = "http://news.livedoor.com/straight_news/";
			String className1 = "hasImg";
			// ツイートするトピックスを取得
			toplist = ut.topixList("livedoor", url1, className1);

			// 対象ヤフーニュース
			String url2 = "https://news.yahoo.co.jp/ranking";
			String className2 = "listFeedWrap";
			// ツイートするトピックスを取得
			// newsList = ut.topixUrlList(url2, className);

			// 対象朝日新聞
			String url3 = "https://www.asahi.com/news/?iref=comtop_gnavi";
			String className3 = "national";
			// ツイートするトピックスを取得
			// newsList = ut.topixUrlList(url2, className);
			
			// リスト内シャッフル
			// Collections.shuffle(urlList);

			int newsCnt = 0;
			// トピックスを一定間隔でツイート（MAX12）
			for (String top : toplist) {
				if (newsCnt <= 2) {
					ut.tweet(top);
					newsCnt++;
					// Thread.sleep(3600000);
				} else {
					break;
				}
			}
			ut.mutualFolow();
			ut.removeFolow();

		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
