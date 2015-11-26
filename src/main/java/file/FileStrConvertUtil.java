package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.codec.binary.Base64;

import ftp.FTPUtil;

/**
 *  文件转字符串，字符串转文件
 *  适用于json传输小文件
 *
 */
public class FileStrConvertUtil {

	public static void main(String[] args) {
		
		FTPUtil ftp = new FTPUtil();
		try {
			ftp.connectServer("192.168.1.1", "21", "appftp", "appftp", "/home/appftp/");
			/*ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			if(!ftp.existDirectory("table_name")){
				ftp.createDirectory("table_name");
			}
			ftp.changeDirectory("table_name");
			if(!ftp.existDirectory("id123456")){
				ftp.createDirectory("id123456");
			}
			ftp.changeDirectory("id123456");
			ftp.uploadFile("D://test.mp4", "abc123.mp4");*/
			InputStream is = ftp.downFile("1447746284015115.mp4");
			
			String str = getFileStr(is);
			System.out.println("111");
			System.out.println(str);
		
			//generateFile(str,"e:\\","aaaaaa.mp4");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*Scanner cin = new Scanner(System.in);
		while(true){
			String s = cin.next();
			generateFile(s,"E:\\18101863211\\",RandomTest.randomLong()+".jpg");
		}*/
		
		/*System.out.println(getFileStr("E:\\20150409_162132.jpg"));
		generateFile(getFileStr("E:\\20150409_162132.jpg"),"E:\\","test123.jpg");
		
		//System.out.println(getFileStr("E:\\111.mp4"));
		generateFile(getFileStr("E:\\111.mp4"),"E:\\","aac.mp4");*/
	}

	/**
	 * Java使用Base64编码处理文件转String
	 * 将文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 过大的文件会导致失败
	 *  
	 * @param filePath
	 * @return
	 */
	public static String getFileStr(String filePath) {
		InputStream in;
		try {
			in = new FileInputStream(filePath);
			return getFileStr(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Java使用Base64编码处理文件转String
	 * 将文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 过大的文件会导致失败
	 *  
	 * @param filePath
	 * @return
	 */
	public static String getFileStr(InputStream in) {
		byte[] data = null;
		try {
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Base64 encoder = new Base64();
		return encoder.encodeToString(data);
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成文件
	 * 
	 * @param fileStr
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static boolean generateFile(String fileStr, String filePath, String fileName) {
		if (fileStr == null) { 
			return false;
		}
		Base64 decoder = new Base64();
		try {
			// Base64解码
			byte[] bytes = decoder.decode(fileStr);
			String fileFullPath = getFilePath(filePath, fileName); 
			OutputStream out = new FileOutputStream(fileFullPath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取文件路径
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static String getFilePath(String dir, String fileName) {
		String fileSeparator = System.getProperty("file.separator");
		if (!dir.endsWith(fileSeparator)) {
			dir += fileSeparator;
		}
		File file = new File(dir);
		if (!file.isDirectory()) {
			// 如果文件夹不存在就新建
			file.mkdirs();
		}
		return dir + fileName;
	}
}
