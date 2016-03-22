package fastdfs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class TestFastDfs {

	public static String conf_filename = "src/main/resource/conf/fdfs_client.conf";

	public static String local_filename = "E:\\111.png";

	public static String remote_filename = "M00/00/00/wKjEhlbxF4aAfivlAABd29PC_9g609.png";

	public static void main(String[] args) {
		TestFastDfs fdfs = new TestFastDfs();
		// fdfs.testUpload();
		// fdfs.testDelete();
		/*
		 * fdfs.testDownload(); //如果服务器上文件不存在，在本地也会生成一个空文件 重新下载后覆盖文件
		 * 
		 * fdfs.testGetFileInfo(); fdfs.testGetFileMate();
		 */

		
		//生成防盗链token
		try {
			//需要注意配置文件中的charset会在getToken方法中用到
			ClientGlobal.init(conf_filename);
			int ts = (int) (System.currentTimeMillis() / 1000);
			String token = ProtoCommon.getToken(remote_filename, ts, ClientGlobal.g_secret_key);
			System.out.println("http://192.168.196.134/" + remote_filename
					+ "?ts=" + ts + "&token=" + token);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		

	}
	

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	public void testUpload() {

		try {
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;

			StorageClient storageClient = new StorageClient(trackerServer,
					storageServer);
			// NameValuePair nvp = new NameValuePair("age", "18");
			NameValuePair nvp[] = new NameValuePair[] {
					new NameValuePair("age", "18"),
					new NameValuePair("sex", "male") };
			String fileIds[] = storageClient.upload_file(local_filename, "png",
					nvp);

			System.out.println(fileIds.length);
			System.out.println("组名：" + fileIds[0]);
			System.out.println("路径: " + fileIds[1]);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}

	public void testDownload() {
		try {

			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;

			StorageClient storageClient = new StorageClient(trackerServer,
					storageServer);
			byte[] b = storageClient.download_file("group1", remote_filename);
			System.out.println(b);
			IOUtils.write(b, new FileOutputStream("E:/222.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetFileInfo() {
		try {
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;

			StorageClient storageClient = new StorageClient(trackerServer,
					storageServer);
			FileInfo fi = storageClient
					.get_file_info("group1", remote_filename);
			System.out.println(fi.getSourceIpAddr());
			System.out.println(fi.getFileSize());
			System.out.println(fi.getCreateTimestamp());
			System.out.println(fi.getCrc32());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetFileMate() {
		try {
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;

			StorageClient storageClient = new StorageClient(trackerServer,
					storageServer);
			NameValuePair nvps[] = storageClient.get_metadata("group1",
					remote_filename);
			for (NameValuePair nvp : nvps) {
				System.out.println(nvp.getName() + ":" + nvp.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDelete() {
		try {
			ClientGlobal.init(conf_filename);

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;

			StorageClient storageClient = new StorageClient(trackerServer,
					storageServer);
			int i = storageClient.delete_file("group1", remote_filename);
			System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}