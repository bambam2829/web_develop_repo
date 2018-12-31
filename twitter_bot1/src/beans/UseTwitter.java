package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class UseTwitter {

	Twitter tw = TwitterFactory.getSingleton();
	// フォロワーリスト
	List<Long> followersList = new ArrayList<Long>();
	// フォローリスト
	List<Long> friendsList = new ArrayList<Long>();

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

	// トピックスのURLを返す処理
	public List<String> topixList(String siteName, String targetUrl, String className)
			throws IOException, TwitterException {

		List<String> list = new ArrayList<>();

		// 対象サイトの情報取得
		Document document = Jsoup.connect(targetUrl).get();
		// サイトの取得する情報を抜粋
		Elements elements = document.getElementsByClass(className).select("a[href]");
		for (Element element : elements) {
			String url;
			String title;
			url = element.toString();
			title = element.toString();
			url = url.substring(url.indexOf("<a href=\""), url.indexOf("\" "));
			url = url.replace("<a href=\"", "");
			switch (siteName) {
			case "livedoor":
				title = title.substring(title.indexOf("<h3 class=\"straightTtl\">"), title.indexOf("</h3>"));
				title = title.replace("<h3 class=\"straightTtl\">", "");
				list.add(title + url);
			case "yahoo":
				title = title.substring(title.indexOf(""), title.indexOf(""));
				title = title.replace("", "");
				list.add(title + url);
			case "asahi":
				title = title.substring(title.indexOf(""), title.indexOf(""));
				title = title.replace("", "");
				list.add(title + url);
			}
		}
		return list;
	}

	public List<String> topixTitleList(String targetUrl, String className) throws IOException, TwitterException {

		List<String> list = new ArrayList<>();

		// 対象サイトの情報取得
		Document document = Jsoup.connect(targetUrl).get();
		// サイトの取得する情報を抜粋
		Elements elements = document.getElementsByClass(className);
		for (Element element : elements) {
			String title = element.toString();
			StringBuilder sb = new StringBuilder();
			sb.append(title);
			title = sb.substring(sb.indexOf("<h3 class=\"straightTtl\">"), sb.indexOf("</h3>"));
			title = title.replace("<h3 class=\"straightTtl\">", "");
			list.add(title);
		}
		return list;
	}

	public void setFollowersListAndFriendsList() throws TwitterException {

		// フォロワーリスト取得
		long cursor = -1L;
		while (true) {
			IDs followers = tw.getFollowersIDs(cursor);
			long[] ids = followers.getIDs();
			if (0 == ids.length)
				break;
			for (int i = 0; i < ids.length; i++) {
				this.followersList.add(ids[i]);
			}
			cursor = followers.getNextCursor();
		}
		// フレンドリスト取得
		cursor = -1L;
		while (true) {
			IDs friends = tw.getFriendsIDs(cursor);
			long[] ids = friends.getIDs();
			if (0 == ids.length)
				break;
			for (int i = 0; i < ids.length; i++) {
				this.friendsList.add(ids[i]);
			}
			cursor = friends.getNextCursor();
		}
	}

	// 相互フォローする処理
	public void mutualFolow() throws TwitterException {

		setFollowersListAndFriendsList();
		// フォロワーリストをループし、1件ごとにフレンド登録されているか確認し、されていなければフレンド登録する。
		for (Long userId : this.followersList) {
			if (!friendsList.contains(userId)) {
				tw.createFriendship(userId);
			}
		}

	}

	// フォローが返されない場合リムーブ
	public void removeFolow() throws TwitterException {

		setFollowersListAndFriendsList();
		for (Long userId : friendsList) {
			if (!followersList.contains(userId)) {
				tw.destroyFriendship(userId);
			}
		}
	}

}
