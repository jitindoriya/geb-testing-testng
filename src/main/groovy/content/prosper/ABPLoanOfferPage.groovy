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
		completeLater_btn (wait:'slow'){$('#complete-later')}
		abandonModal_div (required:false) {$(".reveal-modal.fade.in")}
		miscellaneousReasonID_radio (wait:'slow') {$("label[for='reason8']")}
		submitCompleteLater_btn (wait:'slow'){$("button[data-q='submit-complete-later']")}
		successCompleteLaterModal (required: false){$(".complete-later-modal")}
	}
	
}
