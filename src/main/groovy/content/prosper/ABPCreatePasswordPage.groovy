package content.prosper

import geb.Page

class ABPCreatePasswordPage extends Page{

	static at={
				
			}
	static content={
		loadingBox_div ( required: false ){$(".reveal-modal-bg[style*='display: block;']")}
		createPasswordForm_div (wait:'slow'){$(".plp_heading")}
		createPassword_input (wait:'slow'){$("#create_password")}
		creditReportAuth_checkbox (wait:'slow'){$("#credit-report-authorization")}
		finishYourLoan_btn (wait:'slow'){$("#continue")}
		
	}
	
	
}
