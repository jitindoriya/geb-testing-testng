package content.prosper

import geb.Page

class ABPLoanOfferPage extends Page {

	static at={
//		waitFor (wait:'slow'){offerTable_div.present}
	}
	
	static content={
		loaderBox ( required: false ){$('.spinnerContent')}
		offerTable_div (wait:'slow'){$("offer-options-tabl")}
		chooseRate_btn (wait:'slow'){$("#get-this-loan-instead")}
	}
	
}
