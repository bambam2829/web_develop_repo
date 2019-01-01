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

			
			UseTwitter ut = new UseTwitter();
			ut.syncFolows();
			ut.removeFolows();

			
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
