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
	List<Long> followersList = new ArrayList<>();
	// フォローリスト
	List<Long> friendsList = new ArrayList<>();
	//フォローのリクエストリスト
	List<Long> requestList = new ArrayList<>();

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
			switch (siteName) {
			case "livedoor":
				url = url.substring(url.indexOf("<a href=\""), url.indexOf("\" "));
				url = url.replace("<a href=\"", "");
				title = title.substring(title.indexOf("<h3 class=\"straightTtl\">"), title.indexOf("</h3>"));
				title = title.replace("<h3 class=\"straightTtl\">", "");
				list.add(title + url);
				break;
			case "life hacker":
				url = url.substring(url.indexOf("<a href=\""), url.indexOf("\">"));
				url = url.replace("<a href=\"", "");
				title = title.substring(title.indexOf("<a href=\"" + url + "\">"), title.indexOf("</a>"));
				title = title.replace("<a href=\"" + url + "\">", "");
				title = title.replace("</a>", "");
				list.add(title + "https://www.lifehacker.jp"+url);
				break;
			case "asahi":
				url = url.substring(url.indexOf("<a href=\""), url.indexOf("\">"));
				url = url.replace("<a href=\"", "");
				title = title.substring(title.indexOf("<a href=\"" + url + "\">"), title.indexOf("</a>"));
				title = title.replace("<a href=\"" + url + "\">", "");
				title = title.replace("</a>", "");
				list.add(title + url);
				break;
			}
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
			for (Long id : ids) {
				this.followersList.add(id);
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
			for (Long id : ids) {
				this.friendsList.add(id);
			}
			cursor = friends.getNextCursor();
		}
	}
	
	public void folowRequestList() throws TwitterException {
		// フレンドリスト取得
		long cursor = -1L;
				while (true) {
					IDs folowRequest = tw.getIncomingFriendships(cursor);
					long[] ids = folowRequest.getIDs();
					if (0 == ids.length)
						break;
					for (Long id : ids) {
						this.requestList.add(id);
					}
					cursor = folowRequest.getNextCursor();
				}
	}

	// 相互フォローする処理
	public void syncFolows() throws TwitterException {

		this.setFollowersListAndFriendsList();
		this.folowRequestList();
		// フォロワーリストをループし、1件ごとにフレンド登録されているか確認し、されていなければフレンド登録する。
		for (Long userId : this.followersList) {
			if (!(this.friendsList.contains(userId))&&!(this.requestList.contains(userId))) {
				tw.createFriendship(userId);
			}
		}

	}

	// フォローが返されない場合リムーブ
	public void removeFolows() throws TwitterException {

		this.setFollowersListAndFriendsList();
		for (Long userId : this.friendsList) {
			if (!(followersList.contains(userId))) {
				tw.destroyFriendship(userId);
			}
		}
	}

}
