package htmlparse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PbccrcReport {
	
	private String name;
	
	private String certType;
	
	private String certNo;
	
	private Date queryTime;
	
	private Date reportTime;
	
	private String maritalStatus;
	
	private String reportNo;
	
	private String description;
	
	private String creditRecordDescription;
	
	private String queryRecordDescription;
	
	private String commonRecordDescription;
	
	private String commonRecordDetail;
	
	private String creditRecordSummary;
	
	private Integer creditCardTotal;
	private Integer creditCardActive;
	private Integer creditCardOverdue;
	private Integer creditCardSeriousOverdue;
	private Integer creditCardGuarantee;
	
	private Integer housingLoanTotal;
	private Integer housingLoanActive;
	private Integer housingLoanOverdue;
	private Integer housingLoanSeriousOverdue;
	private Integer housingLoanGuarantee;
	
	private Integer otherLoanTotal;
	private Integer otherLoanActive;
	private Integer otherLoanOverdue;
	private Integer otherLoanSeriousOverdue;
	private Integer otherLoanGuarantee;
	
	private String errorMsg;
	
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

	public Date getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
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
	
	public Integer getCreditCardTotal() {
		return creditCardTotal;
	}

	public void setCreditCardTotal(Integer creditCardTotal) {
		this.creditCardTotal = creditCardTotal;
	}

	public Integer getCreditCardActive() {
		return creditCardActive;
	}

	public void setCreditCardActive(Integer creditCardActive) {
		this.creditCardActive = creditCardActive;
	}

	public Integer getCreditCardOverdue() {
		return creditCardOverdue;
	}

	public void setCreditCardOverdue(Integer creditCardOverdue) {
		this.creditCardOverdue = creditCardOverdue;
	}

	public Integer getCreditCardSeriousOverdue() {
		return creditCardSeriousOverdue;
	}

	public void setCreditCardSeriousOverdue(Integer creditCardSeriousOverdue) {
		this.creditCardSeriousOverdue = creditCardSeriousOverdue;
	}

	public Integer getCreditCardGuarantee() {
		return creditCardGuarantee;
	}

	public void setCreditCardGuarantee(Integer creditCardGuarantee) {
		this.creditCardGuarantee = creditCardGuarantee;
	}

	public Integer getHousingLoanTotal() {
		return housingLoanTotal;
	}

	public void setHousingLoanTotal(Integer housingLoanTotal) {
		this.housingLoanTotal = housingLoanTotal;
	}

	public Integer getHousingLoanActive() {
		return housingLoanActive;
	}

	public void setHousingLoanActive(Integer housingLoanActive) {
		this.housingLoanActive = housingLoanActive;
	}

	public Integer getHousingLoanOverdue() {
		return housingLoanOverdue;
	}

	public void setHousingLoanOverdue(Integer housingLoanOverdue) {
		this.housingLoanOverdue = housingLoanOverdue;
	}

	public Integer getHousingLoanSeriousOverdue() {
		return housingLoanSeriousOverdue;
	}

	public void setHousingLoanSeriousOverdue(Integer housingLoanSeriousOverdue) {
		this.housingLoanSeriousOverdue = housingLoanSeriousOverdue;
	}

	public Integer getHousingLoanGuarantee() {
		return housingLoanGuarantee;
	}

	public void setHousingLoanGuarantee(Integer housingLoanGuarantee) {
		this.housingLoanGuarantee = housingLoanGuarantee;
	}

	public Integer getOtherLoanTotal() {
		return otherLoanTotal;
	}

	public void setOtherLoanTotal(Integer otherLoanTotal) {
		this.otherLoanTotal = otherLoanTotal;
	}

	public Integer getOtherLoanActive() {
		return otherLoanActive;
	}

	public void setOtherLoanActive(Integer otherLoanActive) {
		this.otherLoanActive = otherLoanActive;
	}

	public Integer getOtherLoanOverdue() {
		return otherLoanOverdue;
	}

	public void setOtherLoanOverdue(Integer otherLoanOverdue) {
		this.otherLoanOverdue = otherLoanOverdue;
	}

	public Integer getOtherLoanSeriousOverdue() {
		return otherLoanSeriousOverdue;
	}

	public void setOtherLoanSeriousOverdue(Integer otherLoanSeriousOverdue) {
		this.otherLoanSeriousOverdue = otherLoanSeriousOverdue;
	}

	public Integer getOtherLoanGuarantee() {
		return otherLoanGuarantee;
	}

	public void setOtherLoanGuarantee(Integer otherLoanGuarantee) {
		this.otherLoanGuarantee = otherLoanGuarantee;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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
