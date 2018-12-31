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
			// 対象サイトの情報取得
			Document document = Jsoup.connect(url).get();
			// サイトの取得する情報を抜粋
			Elements elements = document.getElementsByClass(className).select("a[href]");
			String urlwk;
			String title;
			for(Element element : elements) {
				urlwk = element.toString();
				title = element.toString();
				urlwk = urlwk.substring(urlwk.indexOf("<a href=\""), urlwk.indexOf("\" "));
				urlwk = urlwk.replace("<a href=\"", "");
				title = title.substring(title.indexOf("<h3 class=\"straightTtl\">"), title.indexOf("</h3>"));
				title = title.replace("<h3 class=\"straightTtl\">", "");
//				System.out.println("URLだよ"+urlwk);
//				System.out.println("概要だよ"+title);
			}
			UseTwitter use = new UseTwitter();
			//list = use.topixList(siteName,url,className);
			list.add("aa"+"aaa");

			
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
