package content.prosper

import geb.Page

class PublicSiteLoanTermPage extends Page {

	
	static at ={
		waitFor('slow'){heading.text()=="Your Debt Consolidation Loan Terms"}
	}
	
	static content ={
		loaderBox ( required: false ){$('.spinnerContent')}
		heading (wait:'slow'){$(".tila-header-text")}
		tilaDocument(wait:'slow'){$(".tila-scroll-area>p")}
		tilaAcceptAgreement_checkbox (wait:'slow'){$("#tila-acceptance-desktop")}
		continue_btn (wait:'slow'){$("#continue")}
		
	}
	
	def acceptAndSubmitTil(){
		tilaAcceptAgreement_checkbox.click()
		continue_btn.click()
		waitFor('slow'){!loaderBox.present}
	}
}

