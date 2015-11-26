package httprequest;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HTTP请求工具
 * @author angilin
 *
 */
@SuppressWarnings("rawtypes")
public class HttpUtils {
	
    private static int connTimeOut = 100000000;
    private static int readTimeOut = 100000000;
    
    private static String ENCODING = "UTF-8";
    
    public static StringBuffer URLGet(String urlStr, Map parameterMap) throws IOException {
        return URLGet(getTotalURL(urlStr, parameterMap));
    }
    
    public static StringBuffer URLGet(String urlStr, String content) throws IOException {
    	return URLGet(getTotalURL(urlStr, content));
    }
    
    public static StringBuffer URLGet(String urlStr) throws IOException {
    	URL url = new URL(urlStr);
        //这里不需要设置编码，因为getContentURL中已经进行了编码转换
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setConnectTimeout(connTimeOut);
        con.setReadTimeout(readTimeOut);
        HttpURLConnection.setFollowRedirects(true);
        
        //从服务器取得返回内容
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        return getStringBufferFormBufferedReader(in);
    }
    
    
    public static StringBuffer URLPost(String urlStr, String content) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setAllowUserInteraction(false);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset="+ENCODING);
        //表单包含附件时，需要使用multipart/form-data
        //或者在表单包含大量非ascii字符时，需要使用multipart/form-data
        //详细见 http://stackoverflow.com/questions/4007969/application-x-www-form-urlencoded-or-multipart-form-data
        //con.setRequestProperty("Content-Type", "multipart/form-data;charset="+ENCODING);
        con.setConnectTimeout(connTimeOut);
        con.setReadTimeout(readTimeOut);      
        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        bout.write(content);
        bout.flush();
        bout.close();
        
        //从服务器取得返回内容
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),ENCODING));
        return getStringBufferFormBufferedReader(in);
    }


    public static StringBuffer URLPost(String urlStr, Map map) throws IOException {
        return URLPost(urlStr, getContentURL(map));
    }
    
 
    private static StringBuffer getStringBufferFormBufferedReader(BufferedReader in)
            throws IOException {
        StringBuffer returnStringBuffer = new StringBuffer();
        char[] tmpbuf = new char[1024];
        int num = in.read(tmpbuf);
        while (num != -1) {
            returnStringBuffer.append(tmpbuf, 0, num);
            num = in.read(tmpbuf);
        }
        in.close();
        return returnStringBuffer;
    }

    public static String getTotalURL(String strUrl, Map parameterMap) {
        String content = getContentURL(parameterMap);
        return getTotalURL(strUrl, content);
    }

    public static String getTotalURL(String strUrl, String content) {
        String totalURL = strUrl;
        if (totalURL.indexOf("?") == -1)
            totalURL = totalURL + "?";
        else
            totalURL = totalURL + "&";

        totalURL = totalURL + content;
        return totalURL;
    }

    public static String getContentURL(Map parameterMap) {
        if ((parameterMap == null) || (parameterMap.keySet().size() == 0))
            return "";
        StringBuffer url = new StringBuffer();
        Set keys = parameterMap.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = String.valueOf(i.next());
            if (!(parameterMap.containsKey(key)))
                break;
            Object val = parameterMap.get(key);
            String str = (val != null) ? val.toString() : "";
            try {
                str = URLEncoder.encode(str, ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            url.append(key).append("=").append(str).append("&");
        }
        String strURL = "";
        strURL = url.toString();
        if ('&' == strURL.charAt(strURL.length() - 1)) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }
        return strURL;
    }
}