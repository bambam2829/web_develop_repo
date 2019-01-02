package twitter_bot1;

import beans.UseTwitter;
import twitter4j.TwitterException;

public class RemoveUser {

	public static void main(String[] args) {
		
		UseTwitter ut = new UseTwitter();

		try {
			ut.removeFollows();
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}
	}

}
