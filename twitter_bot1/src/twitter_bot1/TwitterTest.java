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

			// 対象ライフハッカー
			String url2 = "https://www.lifehacker.jp/feature/lh_tools/";
			String className2 = "lh-summary-title";
			// ツイートするトピックスを取得
			toplist.addAll(ut.topixList("life hacker",url2, className2));

			// 対象朝日新聞
			String url3 = "http://www.asahi.com/whatsnew/ranking/";
			String className3 = "Ranking";
			// ツイートするトピックスを取得
			toplist.addAll(ut.topixList("asahi",url3, className3));
			
			// リスト内シャッフル
			Collections.shuffle(toplist);

			int newsCnt = 0;
			// トピックスを一定間隔でツイート（MAX12）
			for (String top : toplist) {
				if (newsCnt <= 10) {
					ut.tweet(top);
					newsCnt++;
					// Thread.sleep(3600000);
				} else {
					break;
				}
			}
			
			//相互フォロー
			ut.synchroFolows();

		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
