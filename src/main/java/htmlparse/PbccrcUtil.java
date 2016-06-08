package htmlparse;

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
						"E:\\征信\\央行征信\\个人信用报告.html",
						"GBK");
		PbccrcReport report = getPbccrcContent(htmlContent);
		System.out.println(report.getCertType());
		System.out.println(report.getMaritalStatus());
		System.out.println(report.getDescription());
		
		System.out.println(report.getCreditCardActive());
		
		System.out.println(report.getCreditRecords());
	}

	
	//因为没有数据样本，缺少购房贷款、为他人担保的信用卡或贷款、公共记录的解析
	public static PbccrcReport getPbccrcContent(String htmlContent) {
		PbccrcReport report = new PbccrcReport();

		Document doc = Jsoup.parse(htmlContent, "", Parser.xmlParser());
		Element body = doc.body();

		Elements e1 = body.select("p > strong > span, p > b > span");
		if (e1.isEmpty()) {
			// 抛出格式发生改变异常
			return null;
		}
		for (int i = 0; i < e1.size(); i++) {
			Element e = e1.get(i);
			if (e.text().startsWith("报告编号")) {
				report.setReportNo(e.select("span > span").text());
			} else if (e.text().startsWith("查询时间")) {
				report.setQueryTime(e.select("span > span").text());
			} else if (e.text().startsWith("报告时间")) {
				report.setReportTime(e.select("span > span").text());
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
						creditRecord.setType("1");
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
						creditRecord.setType("2");
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
						creditRecord.setType("21");
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
						creditRecord.setType("22");
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
						queryRecord.setType("1");
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
						queryRecord.setType("2");
						report.addQueryRecord(queryRecord);
					}
				}
			}
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
		int creditIndex = 0;
		for (int i = 0; i < e4.size(); i++) {
			if (creditIndex == 15) {
				break;
			}
			Element e = e4.get(i);
			if (e.text().equals("账户数")) {
				report.setCreditCardTotal(e4.get(i + 1).text());
				report.setHousingLoanTotal(e4.get(i + 2).text());
				report.setOtherLoanTotal(e4.get(i + 3).text());
			}
			if (e.text().equals("未结清/未销户账户数")) {
				report.setCreditCardActive(e4.get(i + 1).text());
				report.setHousingLoanActive(e4.get(i + 2).text());
				report.setOtherLoanActive(e4.get(i + 3).text());
			}
			if (e.text().equals("发生过逾期的账户数")) {
				report.setCreditCardOverdue(e4.get(i + 1).text());
				report.setHousingLoanOverdue(e4.get(i + 2).text());
				report.setOtherLoanOverdue(e4.get(i + 3).text());
			}
			if (e.text().equals("发生过90天以上逾期的账户数")) {
				report.setCreditCardSeriousOverdue(e4.get(i + 1).text());
				report.setHousingLoanSeriousOverdue(e4.get(i + 2).text());
				report.setOtherLoanSeriousOverdue(e4.get(i + 3).text());
			}
			if (e.text().equals("为他人担保笔数")) {
				report.setCreditCardGuarantee(e4.get(i + 1).text());
				report.setHousingLoanGuarantee(e4.get(i + 2).text());
				report.setOtherLoanGuarantee(e4.get(i + 3).text());
			}
		}

		return report;
	}
	
	
	public static PbccrcCreditRecord createCreditRecord(Element creditRecordElement){
		if (creditRecordElement != null
				&& creditRecordElement.text().length() > 0) {
			int chr = creditRecordElement.text().charAt(0);
			if (chr >= 48 && chr <= 57) {
				PbccrcCreditRecord creditRecord = new PbccrcCreditRecord();
				creditRecord.setContent(creditRecordElement.text());
				return creditRecord;
			}
		}
		return null;
	}
}
