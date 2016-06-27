package htmlparse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class ChsiUtil {

	private static final Logger log = Logger.getLogger(ChsiUtil.class);
	

	public static void main(String[] args){
		/*TUsrChsi bak = new TUsrChsi();
		bak.setChsiCode("123");
		ChsiUtil.getResultByChsiCode(bak);*/
		
		try {
			Document doc = Jsoup.parse("<dict><br/><abc>aaa</abc><abc>abb</abc></dict>", "", Parser.xmlParser());
			Elements a = doc.select("dict > abc");
			if(!a.isEmpty()){
				System.out.println(a.html());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用html解析学信网数据
	 * @param chsi
	 * @param content
	 */
	public static void getResultByChsiContent(TUsrChsi chsi, String content) {
		try {
			Document doc = Jsoup.parse(content, "UTF-8");
			fillChsiByDoc(doc,chsi);
		} catch (Exception e) {
			chsi.setChsiType(3);
			chsi.setChsiException(e.getMessage());
		}
	}

	
	/**
	 * 根据Document得到学信网数据
	 * @param doc
	 * @param bak
	 */
	private static void fillChsiByDoc(Document doc, TUsrChsi bak){
		//log.info(doc.html());
		//System.out.println(doc.html());
		// 获取内容的所有的tbody
		Elements eletBodys = doc.select(
				"div[id=resultTable],[class=clearfix]").select("tbody");

		if (eletBodys.isEmpty()) {
			
			Elements eletAlert = doc.select(
					"div[class=alertTXT colorRed]").select("span[id=msgDiv]");
			if(eletAlert!=null){
				log.info("======学信网错误消息======"+eletAlert.html());
				bak.setChsiException(eletAlert.html());
				if(eletAlert.html()!=null && eletAlert.html().indexOf("您访问系统过于频繁")!=-1){
					bak.setChsiType(3);
					bak.setChsiException("您访问系统过于频繁");
					return;
				}
			}			
			bak.setChsiType(2);
			return;
		}
		bak.setChsiType(1);

		String gender = eletBodys.get(0).select("tr").get(1).select("td")
				.get(1).select("div").text();
		bak.setGender(gender);
		log.debug("性别：" + gender);

		String idCard = eletBodys.get(0).select("tr").get(1).select("td")
				.get(3).select("div").text();
		bak.setIdCard(idCard);
		log.debug("身份证：" + idCard);

		String nation = eletBodys.get(0).select("tr").get(2).select("td")
				.get(1).select("div").text();
		bak.setNation(nation);
		log.debug("民族：" + nation);

		String birthday = eletBodys.get(0).select("tr").get(2).select("td")
				.get(3).select("div").text();
		bak.setBirthday(birthday);;
		log.debug("生日：" + birthday);

		String college = eletBodys.get(1).select("tr").get(0).select("td")
				.get(1).select("div").text();
		bak.setCollege(college);
		log.debug("院校：" + college);

		String educationalBg = eletBodys.get(1).select("tr").get(0)
				.select("td").get(3).select("div").text();
		bak.setEducationalBg(educationalBg);
		log.debug("学历：" + educationalBg);

		String major = eletBodys.get(1).select("tr").get(1).select("td")
				.get(1).select("div").text();
		bak.setMajor(major);
		log.debug("院系：" + major);

		String clazz = eletBodys.get(1).select("tr").get(1).select("td")
				.get(3).select("div").text();
		bak.setClazz(clazz);
		log.debug("班级：" + clazz);

		String specialty = eletBodys.get(1).select("tr").get(2)
				.select("td").get(1).select("div").text();
		bak.setSpecialty(specialty);
		log.debug("专业：" + specialty);

		String studentNo = eletBodys.get(1).select("tr").get(2)
				.select("td").get(3).select("div").text();
		bak.setStudentNo(studentNo);
		log.debug("学号：" + studentNo);

		String educationalForm = eletBodys.get(1).select("tr").get(3)
				.select("td").get(1).select("div").text();
		bak.setEducationalForm(educationalForm);
		log.debug("形式：" + educationalForm);

		String entranceTime = eletBodys.get(1).select("tr").get(3)
				.select("td").get(3).select("div").text();
		bak.setEntranceTime(entranceTime);
		log.debug("入学日期：" + entranceTime);

		String educationalSystem = eletBodys.get(1).select("tr").get(3)
				.select("td").get(5).select("div").text();
		bak.setEducationalSystem(educationalSystem);
		log.debug("学制：" + educationalSystem);

		String educationalType = eletBodys.get(1).select("tr").get(4)
				.select("td").get(1).select("div").text();
		bak.setEducationalType(educationalType);
		log.debug("类型：" + educationalType);

		String educationalStatus = eletBodys.get(1).select("tr").get(4)
				.select("td").get(3).select("div").text();
		bak.setEducationalStatus(educationalStatus);
		log.debug("学籍状态：" + educationalStatus);

	}
	
	
}
