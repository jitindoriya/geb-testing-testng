package content.prosper

import geb.Page

class PublicSitePersonalDetailPage extends Page{

	static at = {
		heading.text()=="You're Almost Finished"
	}

	static content ={
		loaderBox ( required: false ){$('.spinnerContent')}
		heading (wait:'slow'){$(".small-12.columns>h1")}
		primaryPhoneNo_input (wait:'slow'){$("#home_phone")}
		secondaryPhoneNo_input (wait:'slow'){$("#mobile_phone")}
		workPhoneNo_input (wait:'slow'){$("#work_phone")}
		employerName_input (wait:'slow'){$("#employer-name")}
		employerPhoneNo_input (wait:'slow'){$("#employer_phone")}
		occupation_dropdown (wait:'slow'){$("#occupation")}
		employmentStartDate_input (wait:'slow'){$("#employment_start_period")}
		ssn_input (wait:'slow'){$("#social")}
		continue_btn (wait:'slow'){$("#continue")}
	}

	def enterPersonalDetailAndSubmit(){
		primaryPhoneNo_input = "2066611811"
		secondaryPhoneNo_input ="2066611811"
		workPhoneNo_input = "2066611811"
		employerPhoneNo_input ="2066611811"
		employerName_input ="TestEmployer"
		occupation_dropdown ="Analyst"
//		ssn_input ="666543705"
		employmentStartDate_input = "02/2000"
		continue_btn.click()

		waitFor('slow'){!loaderBox.present}
	}
}
