package jobbole.test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * http://tz.jobbole.com/4284/
 * 第二期，字典可以在winedt上下载，主帖有下载地址
 * 说明：
 * 		de_neu.dic等字典文件的编码需要改为UTF-8
 * 		测试文件的编码为UTF-8，测试文件的每一行被认为是一句完整的语句，单词之间用空格分隔
 * 实现：
 * 		将字典文件读入HashSet，使用contains判断该单词是否在该语言中
 * 		记录每一行的语种出现次数，如果有单词出现在多语言中，则取出这些多语言中在该行出现次数最多的语言作为该单词的语种
 * @author angilin
 *
 */
public class LanguageScanner {

	private static final String DictionaryRootPath = "I:/";
	private static final String TestFilePath = "I:/test.txt";
	
	public static void main(String args[]) throws Exception {
		HashMap<String,HashSet<String>> languageMap = new HashMap<String,HashSet<String>>();
		languageMap.put("engilish", loadDictionary(DictionaryRootPath+"eng_com.dic"));
		languageMap.put("germen", loadDictionary(DictionaryRootPath+"de_neu.dic"));
		languageMap.put("spanish", loadDictionary(DictionaryRootPath+"es.dic"));
		languageMap.put("france", loadDictionary(DictionaryRootPath+"fr.dic"));
		languageMap.put("portuguese", loadDictionary(DictionaryRootPath+"portugueseU.dic"));
        
        File file = new File(TestFilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            HashSet<String> languageTotalSet = new HashSet<String>();
            
            while ((tempString = reader.readLine()) != null) {
            	String text = new String(tempString.getBytes(),"UTF-8");
            	String[] words = text.split(" ");
            	HashSet<String> languagePerLineSet = new HashSet<String>();
            	HashMap<String,Integer> languagePerLineCounter = new HashMap<String,Integer>();
            	if(words.length==0)
            		continue;
            	HashSet[] wordsLanguage = new HashSet[words.length];
            	for(int i=0;i<words.length;i++){
            		Iterator<String> languageIt = languageMap.keySet().iterator();
            		HashSet<String> wordLanguage = new HashSet<String>();
            		while(languageIt.hasNext()){
            			String language = languageIt.next();
            			if(languageMap.get(language).contains(words[i])){
            				wordLanguage.add(language);
            				if(languagePerLineCounter.containsKey(language)){
            					languagePerLineCounter.put(language,languagePerLineCounter.get(language)+1);
            				}
            				else{
            					languagePerLineCounter.put(language, 1);
            				}
                		}
            		}
            		wordsLanguage[i] = wordLanguage;
            		showLanguageDetail(words[i], wordLanguage);
            	}
            	for(int i=0;i<wordsLanguage.length;i++){
            		HashSet<String> languageSet = wordsLanguage[i];
            		if(languageSet.size()==1){
            			String language = languageSet.iterator().next();
            			languageTotalSet.add(language);
            			languagePerLineSet.add(language);
            		}
            		else if(languageSet.size()>1){
            			int count = 0;
            			String language = null;
            			Iterator<String> it = languageSet.iterator();
            			while(it.hasNext()){
            				String languageMultiple = it.next();
            				if(languagePerLineCounter.get(languageMultiple)>count){
            					language = languageMultiple;
            					count = languagePerLineCounter.get(languageMultiple);
            				}
            			}
            			languageTotalSet.add(language);
            			languagePerLineSet.add(language);
            		}
            	}
            	showLanguageDetail(text, languagePerLineSet);
            }
            showLanguageDetail("the total language", languageTotalSet);  
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}
	
	/**
	 * 加载字典文件
	 * @param filePath
	 * @return
	 */
	private static HashSet<String> loadDictionary(String filePath){
		HashSet<String> wordSet = new HashSet<String>();
		File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            
            int i=0;
            while ((tempString = reader.readLine()) != null) {
            	if(i==1000){
            		int test =1;
            		test++;
            	}
                if(!tempString.startsWith("%")){
                	wordSet.add(new String(tempString.getBytes(),"UTF-8"));
                }
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return wordSet;
	}
	
	/**
	 * 控制台打印详细语言信息
	 * @param text
	 * @param languageSet
	 */
	private static void showLanguageDetail(String text , HashSet<String> languageSet){
		StringBuilder s = new StringBuilder();
    	s.append("[").append(text).append("]").append(" is ");
    	if(languageSet.size()==0){
    		s.append("unkown ");
    	}
    	Iterator<String> it = languageSet.iterator();
    	while(it.hasNext()){
    		String language = it.next();
    		s.append(language).append(",");
    	}
    	System.out.println(s.substring(0, s.length()-1));
	}
}
