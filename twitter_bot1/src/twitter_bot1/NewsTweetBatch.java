package twitter_bot1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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

public class NewsTweetBatch {

	public static void main(String[] args) throws IOException {
			
		try {

			System.setOut(new PrintStream(new FileOutputStream("newsTweetBatch.log")));
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
			toplist.addAll(ut.topixList("life hacker", url2, className2));

			// 対象朝日新聞
			String url3 = "http://www.asahi.com/whatsnew/ranking/";
			String className3 = "Ranking";
			// ツイートするトピックスを取得
			toplist.addAll(ut.topixList("asahi", url3, className3));

			// リスト内シャッフル
			Collections.shuffle(toplist);

			int newsCnt = 0;
			//30分間隔でツイート
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 3; j++) {
					ut.tweet(toplist.get(newsCnt));
					newsCnt++;
				}
				System.out.println("３回ツイートしました。");
				//Thread.sleep(3600000);
			}


			// 相互フォロー更新
			ut.syncFolows();

		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
