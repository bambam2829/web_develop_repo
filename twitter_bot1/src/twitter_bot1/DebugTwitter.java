package twitter_bot1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DebugTwitter {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();

		try {

			String url = "http://news.livedoor.com/straight_news/";
			String classNameSite = "hasImg";
			//List<String> list = new ArrayList<>();

			// 対象サイトの情報取得
			Document document = Jsoup.connect(url).get();
			// サイトの取得する情報を抜粋
			Elements elements = document.getElementsByClass(classNameSite).select("a[href]");;
			for (Element element : elements) {
				String url1 = element.toString();
				String title1 = element.toString();
				StringBuilder sb = new StringBuilder();
				sb.append(url1);
				url1 = sb.substring(sb.indexOf("<a href=\""), sb.indexOf("\" "));
				title1 = sb.substring(sb.indexOf("\""), sb.indexOf("</h3>"));
				title1 = title1.replace("<h3 class=\"straightTtl\">", "");
				url1 = url1.replace("<a href=\"", "");
				list.add(title1+url1);
			}
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
