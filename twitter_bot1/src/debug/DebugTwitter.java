package debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import beans.UseTwitter;

public class DebugTwitter {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();

		try {

			
			String url = "http://news.livedoor.com/straight_news/";
			String className = "hasImg";
			String siteName = "livedoor";
			
			UseTwitter us = new UseTwitter();
			list = us.topixList(siteName, url, className);

			
			list.forEach(toplist -> System.out.println(toplist));
			
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
