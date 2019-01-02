package twitter_bot1;

import beans.UseTwitter;
import twitter4j.TwitterException;

public class SyncFollows {

	public static void main(String[] args) {
		UseTwitter ut = new UseTwitter();
		try {
			ut.syncFollows();
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
