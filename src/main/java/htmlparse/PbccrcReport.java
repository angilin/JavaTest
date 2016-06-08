package htmlparse;

import java.util.ArrayList;
import java.util.List;

public class PbccrcReport {
	
	private String name;
	
	private String certType;
	
	private String certNo;
	
	private String queryTime;
	
	private String reportTime;
	
	private String maritalStatus;
	
	private String reportNo;
	
	private String description;
	
	private String creditRecordDescription;
	
	private String queryRecordDescription;
	
	private String commonRecordDescription;
	
	private String commonRecordDetail;
	
	private String creditRecordSummary;
	
	private String creditCardTotal;
	private String creditCardActive;
	private String creditCardOverdue;
	private String creditCardSeriousOverdue;
	private String creditCardGuarantee;
	
	private String housingLoanTotal;
	private String housingLoanActive;
	private String housingLoanOverdue;
	private String housingLoanSeriousOverdue;
	private String housingLoanGuarantee;
	
	private String otherLoanTotal;
	private String otherLoanActive;
	private String otherLoanOverdue;
	private String otherLoanSeriousOverdue;
	private String otherLoanGuarantee;
	
	private List<PbccrcCreditRecord> creditRecords;
	
	private List<PbccrcQueryRecord> queryRecords;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreditRecordDescription() {
		return creditRecordDescription;
	}

	public void setCreditRecordDescription(String creditRecordDescription) {
		this.creditRecordDescription = creditRecordDescription;
	}

	public String getQueryRecordDescription() {
		return queryRecordDescription;
	}

	public void setQueryRecordDescription(String queryRecordDescription) {
		this.queryRecordDescription = queryRecordDescription;
	}

	public String getCommonRecordDescription() {
		return commonRecordDescription;
	}

	public void setCommonRecordDescription(String commonRecordDescription) {
		this.commonRecordDescription = commonRecordDescription;
	}

	public String getCommonRecordDetail() {
		return commonRecordDetail;
	}

	public void setCommonRecordDetail(String commonRecordDetail) {
		this.commonRecordDetail = commonRecordDetail;
	}
	
	public String getCreditRecordSummary() {
		return creditRecordSummary;
	}

	public void setCreditRecordSummary(String creditRecordSummary) {
		this.creditRecordSummary = creditRecordSummary;
	}

	public List<PbccrcCreditRecord> getCreditRecords() {
		return creditRecords;
	}
	
	public String getCreditCardTotal() {
		return creditCardTotal;
	}

	public void setCreditCardTotal(String creditCardTotal) {
		this.creditCardTotal = creditCardTotal;
	}

	public String getCreditCardActive() {
		return creditCardActive;
	}

	public void setCreditCardActive(String creditCardActive) {
		this.creditCardActive = creditCardActive;
	}

	public String getCreditCardOverdue() {
		return creditCardOverdue;
	}

	public void setCreditCardOverdue(String creditCardOverdue) {
		this.creditCardOverdue = creditCardOverdue;
	}

	public String getCreditCardSeriousOverdue() {
		return creditCardSeriousOverdue;
	}

	public void setCreditCardSeriousOverdue(String creditCardSeriousOverdue) {
		this.creditCardSeriousOverdue = creditCardSeriousOverdue;
	}

	public String getCreditCardGuarantee() {
		return creditCardGuarantee;
	}

	public void setCreditCardGuarantee(String creditCardGuarantee) {
		this.creditCardGuarantee = creditCardGuarantee;
	}

	public String getHousingLoanTotal() {
		return housingLoanTotal;
	}

	public void setHousingLoanTotal(String housingLoanTotal) {
		this.housingLoanTotal = housingLoanTotal;
	}

	public String getHousingLoanActive() {
		return housingLoanActive;
	}

	public void setHousingLoanActive(String housingLoanActive) {
		this.housingLoanActive = housingLoanActive;
	}

	public String getHousingLoanOverdue() {
		return housingLoanOverdue;
	}

	public void setHousingLoanOverdue(String housingLoanOverdue) {
		this.housingLoanOverdue = housingLoanOverdue;
	}

	public String getHousingLoanSeriousOverdue() {
		return housingLoanSeriousOverdue;
	}

	public void setHousingLoanSeriousOverdue(String housingLoanSeriousOverdue) {
		this.housingLoanSeriousOverdue = housingLoanSeriousOverdue;
	}

	public String getHousingLoanGuarantee() {
		return housingLoanGuarantee;
	}

	public void setHousingLoanGuarantee(String housingLoanGuarantee) {
		this.housingLoanGuarantee = housingLoanGuarantee;
	}

	public String getOtherLoanTotal() {
		return otherLoanTotal;
	}

	public void setOtherLoanTotal(String otherLoanTotal) {
		this.otherLoanTotal = otherLoanTotal;
	}

	public String getOtherLoanActive() {
		return otherLoanActive;
	}

	public void setOtherLoanActive(String otherLoanActive) {
		this.otherLoanActive = otherLoanActive;
	}

	public String getOtherLoanOverdue() {
		return otherLoanOverdue;
	}

	public void setOtherLoanOverdue(String otherLoanOverdue) {
		this.otherLoanOverdue = otherLoanOverdue;
	}

	public String getOtherLoanSeriousOverdue() {
		return otherLoanSeriousOverdue;
	}

	public void setOtherLoanSeriousOverdue(String otherLoanSeriousOverdue) {
		this.otherLoanSeriousOverdue = otherLoanSeriousOverdue;
	}

	public String getOtherLoanGuarantee() {
		return otherLoanGuarantee;
	}

	public void setOtherLoanGuarantee(String otherLoanGuarantee) {
		this.otherLoanGuarantee = otherLoanGuarantee;
	}

	public void setCreditRecords(List<PbccrcCreditRecord> creditRecords) {
		this.creditRecords = creditRecords;
	}

	public List<PbccrcQueryRecord> getQueryRecords() {
		return queryRecords;
	}

	public void setQueryRecords(List<PbccrcQueryRecord> queryRecords) {
		this.queryRecords = queryRecords;
	}

	public void addCreditRecord(PbccrcCreditRecord creditRecord){
		if(creditRecords==null){
			creditRecords = new ArrayList<PbccrcCreditRecord>();
		}
		creditRecords.add(creditRecord);
	}
	
	public void addQueryRecord(PbccrcQueryRecord queryRecord){
		if(queryRecords==null){
			queryRecords = new ArrayList<PbccrcQueryRecord>();
		}
		queryRecords.add(queryRecord);
	}
}
