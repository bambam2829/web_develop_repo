package twitter_bot1;

import java.util.ArrayList;
import java.util.List;

import beans.UseTwitter;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class AutoFollows {

	public static void main(String[] args) {

		try {
			List<String> followUserList = new ArrayList<>();
			Twitter tw = TwitterFactory.getSingleton();
			UseTwitter ut = new UseTwitter();
			followUserList = ut.findKeyUser("相互フォロー", 30);
			followUserList.addAll(ut.findKeyUser("ニュース", 20));
			for (String user : followUserList) {
				tw.createFriendship(user);
				Thread.sleep(60 * 1000);
			}
		} catch (TwitterException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
