package httprequest;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 模拟发送带附件的http post请求
 *
 */
public class MultipartFormPost {

	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			//HttpPost httppost = new HttpPost("http://localhost:8080/fileupload/loanApprovedView");
			HttpPost httppost = new HttpPost("http://180.166.169.132:8080/fileUpload");	
			FileBody img = new FileBody(new File("E://error.txt"));
			

			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("file", img)
					.addPart("fileType", new StringBody("1",ContentType.TEXT_PLAIN))
					.addPart("sysUserId", new StringBody("1",ContentType.TEXT_PLAIN))
					.addPart("relateTableId", new StringBody("1",ContentType.TEXT_PLAIN))
					.addPart("sequence", new StringBody("1",ContentType.TEXT_PLAIN))
					.build();

			httppost.setEntity(reqEntity);

			System.out
					.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response content length: "
							+ resEntity.getContentLength());
					System.out.println("Response content: "
							+ resEntity.getContent());
					byte[] b = new byte[(int)resEntity.getContentLength()];
					
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

}
