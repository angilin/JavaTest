package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
  * ClassName: ReadAndWriteTextFile
  * Description: 读写文本文件
  * @author angilin
  * @date 2014-2-11 上午11:12:24
  *
 */
public class ReadAndWriteTextFile {

	public static void main(String[] args){
		System.out.println(System.getProperty("line.separator"));
		writeFile("D:\\test.txt", readFile("d:\\eclipse.txt"),false);
	}
	
	/**
	 * 
	  * Title: readFile <br>
	  * Description: 读取文本文件内容到字符串  ，换行符使用系统换行符  <br>
	  * @param filePath 文件绝对路径
	  * @return 
	  * String 返回类型  文件内容  <br>
	  * @throws
	 */
	public static String readFile(String filePath){
		return readFile(filePath, System.getProperty("line.separator"));
	}
	
	/**
	 * 
	  * Title: readFile <br>
	  * Description: 读取文本文件内容到字符串  <br>
	  * @param filePath 文件绝对路径
	  * @param lineBreak 自定换行符
	  * @return 
	  * String 返回类型 文件内容  <br>
	  * @throws
	 */
	public static String readFile(String filePath, String lineBreak){
		File file = new File(filePath);
		BufferedReader reader = null;
		StringBuilder s = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine())!=null){
				s.append(line);
				if(lineBreak!=null){
					s.append(lineBreak);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return s.toString();
	}
	
	/**
	 * 
	  * Title: writeFile <br>
	  * Description: 写入文件 <br>
	  * @param filePath 文件绝对路径
	  * @param content 需要写入的内容
	  * @param append 是否将写入内容接在原内容之后，为false时覆盖原内容
	  * void 返回类型  <br>
	  * @throws
	 */
	public static void writeFile(String filePath, String content,Boolean append){
		try {
			FileWriter fWriter = new FileWriter(filePath,append);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			bWriter.write(content);
			bWriter.close();
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
