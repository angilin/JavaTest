package httprequest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 发送http请求的客户端
 * 先运行HttpServer，再运行HttpClient
 * @author angilin
 *
 */
public class HttpClient extends Thread {

	@Override
	public void run() {
		/*//使用HttpServer类中配置的端口号
		String actionUrl = "http://localhost:8080/asset_webservice/fileUpload";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("sysUserId","1");
		map.put("Name","中文");
		map.put("Command","1.0.0.0");
		map.put("TranOrder","D000000028");

		try {
			//post方式
			StringBuffer respMsg = HttpUtils.URLPost(actionUrl, map);
			//get方式
			//StringBuffer respMsg = HttpUtils.URLGet(actionUrl, map);
			//输出返回内容
			System.out.println(respMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		
		
		try {
			   URL url = new URL("http://localhost:8080/asset_webservice/fileUpload");
			   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			   conn.setDoOutput(true);
			   conn.setDoInput(true);
			   conn.setChunkedStreamingMode(1024*1024);  
			   conn.setRequestMethod("POST");
			   conn.setRequestProperty("connection", "Keep-Alive");
			   conn.setRequestProperty("Charsert", "UTF-8");
			   String fname = "e://aab.txt";
			   File file = new File(fname);
			   conn.setRequestProperty("Content-Type","multipart/form-data;file="+file.getName());
			   conn.setRequestProperty("filename",file.getName());
			   OutputStream out = new DataOutputStream(conn.getOutputStream());
			   DataInputStream in = new DataInputStream(new FileInputStream(file));
			   int bytes = 0;
			   byte[] bufferOut = new byte[1024];
			   while ((bytes = in.read(bufferOut)) != -1) {
			    out.write(bufferOut, 0, bytes);
			   }
			   in.close();
			   out.flush();
			   out.close();
			   BufferedReader reader = new BufferedReader(new InputStreamReader(
			     conn.getInputStream()));
			   String line = null;
			   while ((line = reader.readLine()) != null) {
			    System.out.println(line);
			   }
			  } catch (Exception e) {
			   System.out.println("发送POST请求出现异常！" + e);
			   e.printStackTrace();
			  }
			 
		
	}
	
	public static void main(String[] args) {
		new HttpClient().run();
	}

}
