package htmlparse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import file.ReadAndWriteTextFile;

public class PbccrcUtil {

	public static void main(String[] args) {

		String htmlContent = ReadAndWriteTextFile
				.readFile(
						"E:\\征信\\央行征信\\个人信用报告-公共记录.html",
						"GBK");
		PbccrcReport report = getPbccrcContent(htmlContent);
		System.out.println(report.getCertType());
		System.out.println(report.getMaritalStatus());

		System.out.println(report.getDescription());
		System.out.println(report.getQueryTime());
		System.out.println(report.getReportTime());
		System.out.println(report.getCreditRecordDescription());
		System.out.println(report.getCreditRecordSummary());

		System.out.println(report.getCreditCardActive());
		System.out.println(report.getCreditRecords());
		System.out.println(report.getErrorMsg());
		for(PbccrcCreditRecord r : report.getCreditRecords()){
			System.out.println(r.getType()+":"+r.getContent());
		}
		for(PbccrcQueryRecord r : report.getQueryRecords()){
			System.out.println(r.getType()+":"+r.getQueryNo()+","+r.getQueryDate()+","+r.getOperator()+","+r.getQueryReason());
		}
	}
	
	private static final String QUERY_RECORD_TYPE_ORG = "1";
	private static final String QUERY_RECORD_TYPE_SELF = "2";
	
	private static final String CREDIT_RECORD_TYPE_CREDIT_CARD_OVERDUE = "1";
	private static final String CREDIT_RECORD_TYPE_CREDIT_CARD_NORMAL = "2";
	
	private static final String CREDIT_RECORD_TYPE_HOUSING_LOAN_OVERDUE = "11";
	private static final String CREDIT_RECORD_TYPE_HOUSING_LOAN_NORMAL = "12";
	
	private static final String CREDIT_RECORD_TYPE_OTHER_LOAN_OVERDUE = "21";
	private static final String CREDIT_RECORD_TYPE_OTHER_LOAN_NORMAL = "22";
	
	private static final String CREDIT_RECORD_TYPE_GUARANTEE = "31";
	
	private static final String CREDIT_RECORD_TYPE_REPAYMENT = "42";
	
	//适应201606样式修改后的报告
	//因为没有数据样本，缺少【公共记录】 的解析
	public static PbccrcReport getPbccrcContent(String htmlContent) {
			PbccrcReport report = new PbccrcReport();

			StringBuilder errorMsg = new StringBuilder();
			
			DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			
			Document doc = Jsoup.parse(htmlContent, "", Parser.xmlParser());
			Element body = doc.body();
			
			
			//Elements et = body.select("span[class=h1]:contains(信用卡) + ol span:contains(发生过逾期) + li");
			//Elements et = body.select("span[class=h1]:contains(信用卡) + ol span:contains(从未逾期) + li");
			/*Elements et = body.select("tr > td > strong[class=p]");
			for (int i = 0; i < et.size(); i++) {
				Element e = et.get(i);
				System.out.println("text:"+e.text());
				System.out.println("html:"+e.html());
			}*/
			

			Elements e1 = body.select("tr > td > strong[class=p]");
			if(e1.isEmpty()){
				errorMsg.append("征信报告样式发生变化，请重新编写解析方法。");
			}
			for (int i = 0; i < e1.size(); i++) {
				Element e = e1.get(i);
				if (e.text().startsWith("报告编号")) {
					report.setReportNo(e.text().substring(5, e.text().length()));
				} else if (e.text().startsWith("查询时间")) {
					try {
						report.setQueryTime(df.parse(e.text().substring(5, e.text().length())));
					} catch (ParseException ex) {
						errorMsg.append("查询时间转换失败，原值为"+e.text().substring(5, e.text().length())+"。");
					}
				} else if (e.text().startsWith("报告时间")) {
					try {
						report.setReportTime(df.parse(e.text().substring(5, e.text().length())));
					} catch (ParseException ex) {
						errorMsg.append("报告时间转换失败，原值为"+e.text().substring(5, e.text().length())+"。");
					}
				} else if (e.text().startsWith("姓名")) {
					report.setName(e.text().substring(5, e.text().length()));
				} else if (e.text().startsWith("证件类型")) {
					report.setCertType(e.text().substring(5, e.text().length()));
				} else if (e.text().startsWith("证件号码")) {
					report.setCertNo(e.text().substring(5, e.text().length()).trim());
					//婚姻状况
					report.setMaritalStatus(e1.get(i + 1).text());
					//信贷记录说明
					report.setCreditRecordDescription(e1.get(i + 2).text());
					//公共记录说明
					report.setCommonRecordDescription(e1.get(i + 3).text());
					//查询记录说明
					report.setQueryRecordDescription(e1.get(i + 4).text());
				}

				// 查询记录
				if (e.text().startsWith("机构查询记录明细")) {
					Elements queryRecordElements = e.parent().parent().parent()
							.parent().select("tr");
					for (int j = 3; j < queryRecordElements.size(); j++) {
						Elements tdElements = queryRecordElements.get(j).select(
								"td");
						if (tdElements.size() == 4) {
							PbccrcQueryRecord queryRecord = new PbccrcQueryRecord();
							queryRecord.setQueryNo(tdElements.get(0).text());
							queryRecord.setQueryDate(tdElements.get(1).text());
							queryRecord.setOperator(tdElements.get(2).text());
							queryRecord.setQueryReason(tdElements.get(3).text());
							queryRecord.setType(QUERY_RECORD_TYPE_ORG);
							report.addQueryRecord(queryRecord);
						}
					}
				}
				if (e.text().startsWith("个人查询记录明细")) {
					Elements queryRecordElements = e.parent().parent().parent()
							.parent().select("tr");
					for (int j = 3; j < queryRecordElements.size(); j++) {
						Elements tdElements = queryRecordElements.get(j).select(
								"td");
						if (tdElements.size() == 4) {
							PbccrcQueryRecord queryRecord = new PbccrcQueryRecord();
							queryRecord.setQueryNo(tdElements.get(0).text());
							queryRecord.setQueryDate(tdElements.get(1).text());
							queryRecord.setOperator(tdElements.get(2).text());
							queryRecord.setQueryReason(tdElements.get(3).text());
							queryRecord.setType(QUERY_RECORD_TYPE_SELF);
							report.addQueryRecord(queryRecord);
						}
					}
				}
				
			}
			
			String type = CREDIT_RECORD_TYPE_CREDIT_CARD_OVERDUE;
			//信贷记录-信用卡
			Elements eCreditCard = body.select("span[class=h1]:contains(信用卡) + ol span, span[class=h1]:contains(信用卡) + ol li");
			for (int i = 0; i < eCreditCard.size(); i++) {
				Element e = eCreditCard.get(i);
				if(e.tagName().equals("span")){
					if(e.text().startsWith("发生过逾期")){
						type = CREDIT_RECORD_TYPE_CREDIT_CARD_OVERDUE;
					}
					else if(e.text().startsWith("从未逾期过")){
						type = CREDIT_RECORD_TYPE_CREDIT_CARD_NORMAL;
					}
				}
				else{
					PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
					creditRecord.setContent(e.text());
					creditRecord.setType(type);
					report.addCreditRecord(creditRecord);
				}
			}
			
			//信贷记录-购房贷款
			Elements eHousingLoan = body.select("span[class=h1]:contains(购房贷款) + ol span, span[class=h1]:contains(购房贷款) + ol li");
			for (int i = 0; i < eHousingLoan.size(); i++) {
				Element e = eHousingLoan.get(i);
				if(e.tagName().equals("span")){
					if(e.text().startsWith("发生过逾期")){
						type = CREDIT_RECORD_TYPE_HOUSING_LOAN_OVERDUE;
					}
					else if(e.text().startsWith("从未逾期过")){
						type = CREDIT_RECORD_TYPE_HOUSING_LOAN_NORMAL;
					}
				}
				else{
					PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
					creditRecord.setContent(e.text());
					creditRecord.setType(type);
					report.addCreditRecord(creditRecord);
				}
			}
			
			//信贷记录-其他贷款
			Elements eOtherLoan = body.select("span[class=h1]:contains(其他贷款) + ol span, span[class=h1]:contains(其他贷款) + ol li");
			for (int i = 0; i < eOtherLoan.size(); i++) {
				Element e = eOtherLoan.get(i);
				if(e.tagName().equals("span")){
					if(e.text().startsWith("发生过逾期")){
						type = CREDIT_RECORD_TYPE_OTHER_LOAN_OVERDUE;
					}
					else if(e.text().startsWith("从未逾期过")){
						type = CREDIT_RECORD_TYPE_OTHER_LOAN_NORMAL;
					}
				}
				else{
					PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
					creditRecord.setContent(e.text());
					creditRecord.setType(type);
					report.addCreditRecord(creditRecord);
				}
			}
			
			//保证人代偿信息
			Elements eRepayment = body.select("span[class=h1]:contains(保证人代偿信息) + br ol:eq(0) li");
			for (int i = 0; i < eRepayment.size(); i++) {
				Element e = eRepayment.get(i);
				PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
				creditRecord.setContent(e.text());
				creditRecord.setType(CREDIT_RECORD_TYPE_REPAYMENT);
				report.addCreditRecord(creditRecord);
			}
			
			//为他人担保信息
			Elements eGuarantee = body.select("span[class=h1]:contains(为他人担保信息) + ol li");
			for (int i = 0; i < eGuarantee.size(); i++) {
				Element e = eGuarantee.get(i);
				PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
				creditRecord.setContent(e.text());
				creditRecord.setType(CREDIT_RECORD_TYPE_GUARANTEE);
				report.addCreditRecord(creditRecord);
			}
			
			
			
			// 底部的说明
			StringBuilder description = new StringBuilder();
			Elements e2 = body.select("ol > li[class=p]");
			for (int i = 0; i < e2.size(); i++) {
				Element e = e2.get(i);
				description.append(e.text());
			}
			report.setDescription(description.toString());

			
			// 信贷记录-信息概要
			StringBuilder creditReportSummary = new StringBuilder();
			Elements e3 = body.select("td div > span");
			for (int i = 0; i < e3.size(); i++) {
				Element e = e3.get(i);
				creditReportSummary.append(e.text());
			}
			report.setCreditRecordSummary(creditReportSummary.toString());

			Elements e4 = body.select("tr > td > table > tbody > tr > td > table > tbody > tr > td[class=p]");
			for (int i = 0; i < e4.size(); i++) {
				Element e = e4.get(i);
				try{
					if (e.text().endsWith("笔数") && e.text().length()<4) {
						report.setAssetTotal(Integer.valueOf(e4.get(i + 1).text()));
						report.setRepaymentTotal(Integer.valueOf(e4.get(i + 2).text()));
					}	
					if (e.text().endsWith("账户数") && e.text().length()<5) {
						report.setCreditCardTotal(Integer.valueOf(e4.get(i + 1).text()));
						report.setHousingLoanTotal(Integer.valueOf(e4.get(i + 2).text()));
						report.setOtherLoanTotal(Integer.valueOf(e4.get(i + 3).text()));
					}
					if (e.text().endsWith("未结清/未销户账户数")) {
						report.setCreditCardActive(Integer.valueOf(e4.get(i + 1).text()));
						report.setHousingLoanActive(Integer.valueOf(e4.get(i + 2).text()));
						report.setOtherLoanActive(Integer.valueOf(e4.get(i + 3).text()));
					}
					if (e.text().endsWith("发生过逾期的账户数")) {
						report.setCreditCardOverdue(Integer.valueOf(e4.get(i + 1).text()));
						report.setHousingLoanOverdue(Integer.valueOf(e4.get(i + 2).text()));
						report.setOtherLoanOverdue(Integer.valueOf(e4.get(i + 3).text()));
					}
					if (e.text().endsWith("发生过90天以上逾期的账户数")) {
						report.setCreditCardSeriousOverdue(Integer.valueOf(e4.get(i + 1).text()));
						report.setHousingLoanSeriousOverdue(Integer.valueOf(e4.get(i + 2).text()));
						report.setOtherLoanSeriousOverdue(Integer.valueOf(e4.get(i + 3).text()));
					}
					if (e.text().endsWith("为他人担保笔数")) {
						report.setCreditCardGuarantee(Integer.valueOf(e4.get(i + 1).text()));
						report.setHousingLoanGuarantee(Integer.valueOf(e4.get(i + 2).text()));
						report.setOtherLoanGuarantee(Integer.valueOf(e4.get(i + 3).text()));
					}
				}
				catch(NumberFormatException ex){
					errorMsg.append("信贷记录信息概要表格数字转换失败，出错标识位为"+e.text()+"，原值为"+e4.get(i + 1).text()+"，"+e4.get(i + 2).text()+"，"+e4.get(i + 3).text()+"。");
				}
			}
			
			report.setErrorMsg(errorMsg.toString());

			return report;
		}
	
	
	//因为没有数据样本，缺少【购房贷款】、【为他人担保】、【公共记录】 的解析
	public static PbccrcReport getPbccrcContentBefore201606(String htmlContent) {
		PbccrcReport report = new PbccrcReport();

		StringBuilder errorMsg = new StringBuilder();
		
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		
		Document doc = Jsoup.parse(htmlContent, "", Parser.xmlParser());
		Element body = doc.body();

		Elements e1 = body.select("p > strong > span, p > b > span");
		if(e1.isEmpty()){
			errorMsg.append("征信报告样式发生变化，请重新编写解析方法。");
		}
		for (int i = 0; i < e1.size(); i++) {
			Element e = e1.get(i);
			if (e.text().startsWith("报告编号")) {
				report.setReportNo(e.select("span > span").text());
			} else if (e.text().startsWith("查询时间")) {
				try {
					report.setQueryTime(df.parse(e.select("span > span").text()));
				} catch (ParseException ex) {
					errorMsg.append("查询时间转换失败，原值为"+e.select("span > span").text()+"。");
				}
			} else if (e.text().startsWith("报告时间")) {
				try {
					report.setReportTime(df.parse(e.select("span > span").text()));
				} catch (ParseException ex) {
					errorMsg.append("报告时间转换失败，原值为"+e.select("span > span").text()+"。");
				}
			} else if (e.text().startsWith("姓名")) {
				report.setName(e.select("span > span").text());
			} else if (e.text().startsWith("证件类型")) {
				report.setCertType(e.text().substring(5, e.text().length()));
			} else if (e.text().startsWith("证件号码")) {
				report.setCertNo(e.select("span > span").text());
				report.setMaritalStatus(e1.get(i + 1).text());
			} else if (e.text().startsWith("信贷记录")) {
				report.setCreditRecordDescription(e1.get(i + 2).text());
			} else if (e.text().startsWith("公共记录")) {
				report.setCommonRecordDescription(e1.get(i + 1).text());
			} else if (e.text().startsWith("查询记录")) {
				report.setQueryRecordDescription(e1.get(i + 1).text());
			}

			// 信用卡
			if (e.text().startsWith("发生过逾期的贷记卡")) {
				Element creditRecordElement = e.parent().parent()
						.nextElementSibling();
				if (creditRecordElement != null
						&& creditRecordElement.text().length() > 0) {
					int chr = creditRecordElement.text().charAt(0);
					//取以数字开始的记录
					while (chr >= 48 && chr <= 57) {
						PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
						creditRecord.setContent(creditRecordElement.text());
						creditRecord.setType(CREDIT_RECORD_TYPE_CREDIT_CARD_OVERDUE);
						report.addCreditRecord(creditRecord);
						creditRecordElement = creditRecordElement
								.nextElementSibling();
						if (creditRecordElement != null
								&& creditRecordElement.text().length() > 0) {
							chr = creditRecordElement.text().charAt(0);
						}
						else{
							break;
						}
					}
				}
			}
			if (e.text().startsWith("从未逾期过的贷记卡")) {
				Element creditRecordElement = e.parent().parent()
						.nextElementSibling();
				if (creditRecordElement != null
						&& creditRecordElement.text().length() > 0) {
					int chr = creditRecordElement.text().charAt(0);
					while (chr >= 48 && chr <= 57) {
						PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
						creditRecord.setContent(creditRecordElement.text());
						creditRecord.setType(CREDIT_RECORD_TYPE_CREDIT_CARD_NORMAL);
						report.addCreditRecord(creditRecord);
						creditRecordElement = creditRecordElement
								.nextElementSibling();
						if (creditRecordElement != null
								&& creditRecordElement.text().length() > 0) {
							chr = creditRecordElement.text().charAt(0);
						}
						else{
							break;
						}
					}
				}
			}

			// 其他贷款
			if (e.text().startsWith("发生过逾期的账户明细")) {
				Element creditRecordElement = e.parent().parent()
						.nextElementSibling();
				if (creditRecordElement != null
						&& creditRecordElement.text().length() > 0) {
					int chr = creditRecordElement.text().charAt(0);
					while (chr >= 48 && chr <= 57) {
						PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
						creditRecord.setContent(creditRecordElement.text());
						creditRecord.setType(CREDIT_RECORD_TYPE_OTHER_LOAN_OVERDUE);
						report.addCreditRecord(creditRecord);
						creditRecordElement = creditRecordElement
								.nextElementSibling();
						if (creditRecordElement != null
								&& creditRecordElement.text().length() > 0) {
							chr = creditRecordElement.text().charAt(0);
						}
						else{
							break;
						}
					}
				}
			}
			if (e.text().startsWith("从未逾期过的账户明细")) {
				Element creditRecordElement = e.parent().parent()
						.nextElementSibling();
				if (creditRecordElement != null
						&& creditRecordElement.text().length() > 0) {
					int chr = creditRecordElement.text().charAt(0);
					while (chr >= 48 && chr <= 57) {
						PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
						creditRecord.setContent(creditRecordElement.text());
						creditRecord.setType(CREDIT_RECORD_TYPE_OTHER_LOAN_NORMAL);
						report.addCreditRecord(creditRecord);
						creditRecordElement = creditRecordElement
								.nextElementSibling();
						if (creditRecordElement != null
								&& creditRecordElement.text().length() > 0) {
							chr = creditRecordElement.text().charAt(0);
						}
						else{
							break;
						}
					}
				}
			}

			// 查询记录
			if (e.text().startsWith("机构查询记录明细")) {
				Elements queryRecordElements = e.parent().parent().parent()
						.parent().parent().select("tr");
				for (int j = 3; j < queryRecordElements.size(); j++) {
					Elements tdElements = queryRecordElements.get(j).select(
							"td");
					if (tdElements.size() == 4) {
						PbccrcQueryRecord queryRecord = new PbccrcQueryRecord();
						queryRecord.setQueryNo(tdElements.get(0).text());
						queryRecord.setQueryDate(tdElements.get(1).text());
						queryRecord.setOperator(tdElements.get(2).text());
						queryRecord.setQueryReason(tdElements.get(3).text());
						queryRecord.setType(QUERY_RECORD_TYPE_ORG);
						report.addQueryRecord(queryRecord);
					}
				}
			}
			if (e.text().startsWith("个人查询记录明细")) {
				Elements queryRecordElements = e.parent().parent().parent()
						.parent().parent().select("tr");
				for (int j = 3; j < queryRecordElements.size(); j++) {
					Elements tdElements = queryRecordElements.get(j).select(
							"td");
					if (tdElements.size() == 4) {
						PbccrcQueryRecord queryRecord = new PbccrcQueryRecord();
						queryRecord.setQueryNo(tdElements.get(0).text());
						queryRecord.setQueryDate(tdElements.get(1).text());
						queryRecord.setOperator(tdElements.get(2).text());
						queryRecord.setQueryReason(tdElements.get(3).text());
						queryRecord.setType(QUERY_RECORD_TYPE_SELF);
						report.addQueryRecord(queryRecord);
					}
				}
			}
			
			System.out.println("text:"+e.text());
			System.out.println("html:"+e.html());
		}

		// 底部的说明
		Boolean descriptionField = false;
		StringBuilder description = new StringBuilder();
		Elements e2 = body.select("p > span");
		for (int i = 0; i < e2.size(); i++) {
			Element e = e2.get(i);

			if (e.text().startsWith("除查询记录外")) {
				descriptionField = true;
				description.append(e2.get(i - 1).text());
			}

			if (descriptionField) {
				description.append(e.text());
			}

			if (e.text().endsWith("请致电全国客户服务热线400-810-8866。")) {
				descriptionField = false;
			}
		}
		report.setDescription(description.toString());

		
		// 信贷记录-信息概要
		StringBuilder creditReportSummary = new StringBuilder();
		Elements e3 = body.select("td > div > p > span");
		for (int i = 0; i < e3.size(); i++) {
			Element e = e3.get(i);
			creditReportSummary.append(e.text());
		}
		report.setCreditRecordSummary(creditReportSummary.toString());

		
		Elements e4 = body.select("td > p > span");
		for (int i = 0; i < e4.size(); i++) {
			Element e = e4.get(i);
			try{
				if (e.text().equals("账户数")) {
					report.setCreditCardTotal(Integer.valueOf(e4.get(i + 1).text()));
					report.setHousingLoanTotal(Integer.valueOf(e4.get(i + 2).text()));
					report.setOtherLoanTotal(Integer.valueOf(e4.get(i + 3).text()));
				}
				if (e.text().equals("未结清/未销户账户数")) {
					report.setCreditCardActive(Integer.valueOf(e4.get(i + 1).text()));
					report.setHousingLoanActive(Integer.valueOf(e4.get(i + 2).text()));
					report.setOtherLoanActive(Integer.valueOf(e4.get(i + 3).text()));
				}
				if (e.text().equals("发生过逾期的账户数")) {
					report.setCreditCardOverdue(Integer.valueOf(e4.get(i + 1).text()));
					report.setHousingLoanOverdue(Integer.valueOf(e4.get(i + 2).text()));
					report.setOtherLoanOverdue(Integer.valueOf(e4.get(i + 3).text()));
				}
				if (e.text().equals("发生过90天以上逾期的账户数")) {
					report.setCreditCardSeriousOverdue(Integer.valueOf(e4.get(i + 1).text()));
					report.setHousingLoanSeriousOverdue(Integer.valueOf(e4.get(i + 2).text()));
					report.setOtherLoanSeriousOverdue(Integer.valueOf(e4.get(i + 3).text()));
				}
				if (e.text().equals("为他人担保笔数")) {
					report.setCreditCardGuarantee(Integer.valueOf(e4.get(i + 1).text()));
					report.setHousingLoanGuarantee(Integer.valueOf(e4.get(i + 2).text()));
					report.setOtherLoanGuarantee(Integer.valueOf(e4.get(i + 3).text()));
				}
			}
			catch(NumberFormatException ex){
				errorMsg.append("信贷记录信息概要表格数字转换失败，出错标识位为"+e.text()+"，原值为"+e4.get(i + 1).text()+"，"+e4.get(i + 2).text()+"，"+e4.get(i + 3).text()+"。");
			}
		}
		
		report.setErrorMsg(errorMsg.toString());

		return report;
	}
	
	
}
