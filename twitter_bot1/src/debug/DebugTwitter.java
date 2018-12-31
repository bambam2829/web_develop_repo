package debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;

import beans.UseTwitter;

public class DebugTwitter {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();

		try {

			
			String url = "https://www.lifehacker.jp/feature/lh_tools/";
			String className = "lh-summary-title";
			String siteName = "life hacker";
			
			UseTwitter ut = new UseTwitter();
			list = ut.topixList(siteName, url, className);
			list.forEach(topxlist -> System.out.println(topxlist));

			
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
