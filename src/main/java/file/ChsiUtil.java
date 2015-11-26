package file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class ChsiUtil {


	public static void main(String[] args){
		String s = ReadAndWriteTextFile.readFile("E:\\CityOrder.plist");

		try {
			Document doc = Jsoup.parse(s, "", Parser.xmlParser());
			//Elements a = doc.select("dict").select("key");
			//Elements a = doc.select("dict").select("array").select("dict").select("key");
			//Elements a = doc.select("dict").select("array").select("string");
			
			Elements a = doc.select("array").select("string");
			if(!a.isEmpty()){
				System.out.println(a.html());
				String[] citys = a.html().split("\\n");
				System.out.println(citys.length);
				List<String> list = Arrays.asList(citys);
				Set<String> set = new TreeSet<String>();
				set.addAll(list);
				System.out.println(set.size());
				/*for(String ss : set){
					System.out.println(ss);
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
