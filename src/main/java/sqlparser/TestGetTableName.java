package sqlparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import file.ReadAndWriteTextFile;

public class TestGetTableName {
	
	public static void main(String[] args){
		String sql = "";
		//sql = ReadAndWriteTextFile.readFile("d:\\00225619\\桌面\\借款查询-首次进入.txt");
		//sql = ReadAndWriteTextFile.readFile("d:\\00225619\\桌面\\借款查询-第一次查询.txt");
		//sql = ReadAndWriteTextFile.readFile("d:\\00225619\\桌面\\借款查询-第二次查询.txt");
		
		//sql = ReadAndWriteTextFile.readFile("d:\\00225619\\桌面\\审核任务分配-第一次查询.txt");
		
		sql = ReadAndWriteTextFile.readFile("d:\\00225619\\桌面\\新建文本文档.txt");
		
		
		
		sql = sql + "Hibernate:";
		
		Pattern p = Pattern.compile("Hibernate: (((?!Hibernate:).)+)");
		//Pattern p = Pattern.compile("Hibernate: ([^:]+)");
		Matcher m = p.matcher(sql);
		List<String> list = new ArrayList<String>();
		while(m.find()){
			list.add(m.group(1));
			//System.out.println(m.group(1));
		}
		System.out.println(list.size());
		
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String s : list){
			p = Pattern.compile(" from (.+) where ");
			m = p.matcher(s);
			while(m.find()){
				String key = m.group(1);
				if(map.containsKey(key)){
					map.put(key, map.get(key)+1);
				}
				else{
					map.put(key, 1);
				}
			}
		}
		
		
		for(String a : map.keySet()){
			String tableName = a;
			if(tableName.indexOf("join")==-1){
				tableName = tableName.substring(0,tableName.indexOf(" "));
			}
			System.out.println(map.get(a)+"-------"+tableName);
		}
	}

}
