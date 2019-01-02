package twitter_bot1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.javafx.util.Logging;

import beans.UseTwitter;
import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class NewsTweetBatch {

	public static void main(String[] args) throws IOException {
		final Logger logger = Logger.getLogger(Logging.class.getName());
		  final String filePath = "/var/log/tweet_batch.log";
		try {

			// Handlerを生成しloggerに登録
            FileHandler fHandler = new FileHandler(filePath, true);
            fHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fHandler);
 
            // ログレベルの設定
            logger.setLevel(Level.INFO);
            
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
			for (int i = 0; i < 48; i++) {
				for (int j = 0; j < 3; j++) {
					ut.tweet(toplist.get(newsCnt));
					Thread.sleep(60*1000);
					newsCnt++;
				}
				System.out.println("３回ツイートしました。");
				Thread.sleep(27*60*1000);
			}

			// 相互フォロー更新
			ut.syncFollows();

		} catch (TwitterException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
