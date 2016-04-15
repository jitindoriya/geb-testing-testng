package content.prosper

import geb.Page

class ABPRegistrationPage extends Page{

	static at={
//		waitFor (wait:'slow'){emailAddress_disabled_input.present}
	}
	
	static content={
		loaderBox ( required: false ){$('.spinnerContent')}
		loanAmount_input (wait:'slow'){$("#loan-amount")}
		loanPurpose_dropdown (wait:'slow'){$("#loan_purpose_id")}
		primaryPhoneNo_input (wait:'slow'){$("#home_phone")}
		secondaryPhoneNo_input (wait:'slow'){$("#mobile_phone")}
		firstName_in    (wait:'slow'){$('#first-name')}
		lastName_in (wait:'slow'){$('#last-name')}
		homeAddress_in  (wait:'slow'){$('#home-address')}
		city_in     (wait:'slow'){$('#city')}
		zip_in  (wait:'slow') {$('#zip')}
		state_dropDown (wait:'slow'){$('#state')}
		employmentStatus_dropDown (wait:'slow') {$('#employment_status_id')}
		yearlIncome_in  (wait:'slow') {$('#individual-yearly-income')}
		dateOfBirth_in (wait:'slow')    {$('#date-of-birth-text')}
		emailAddress_disabled_input  {$("#email[disabled='disabled']")}
		checkBoxes (wait:'slow'){$(".checkbox-label")}
		getRate_btn (wait:'slow'){$("[data-q='get-rate']")}
	}
	
	def enterABPRegistrationAndSubmit(){
		loanAmount_input ="4000"
		loanPurpose_dropdown="Debt Consolidation"
		primaryPhoneNo_input="2066611811"
		secondaryPhoneNo_input="2066611811"
		firstName_in="Gary"
		lastName_in="BAKOWSKI"
		homeAddress_in="2416 WASHINGTON AVE"
		city_in="SAINT ALBANS"
		zip_in="251773232"
		state_dropDown="WV"
		employmentStatus_dropDown="Employed"
		yearlIncome_in="40000"
		dateOfBirth_in="01/01/1953"
		checkBoxes.each {
			it.click()
		}
		getRate_btn.click()
		waitFor('slow'){!loaderBox.present}
	}
}
