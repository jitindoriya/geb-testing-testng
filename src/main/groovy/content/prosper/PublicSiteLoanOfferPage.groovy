package content.prosper

import geb.Page

class PublicSiteLoanOfferPage extends Page{

	static at ={
		assert heading.text()=="Your customized loan offer:"
	}

	static content={
		loaderBox ( required: false ){$('.spinnerContent')}
		heading (wait:'slow'){$('h2.main-offer-table-header')}
		getThisLoan_btn (to: PublicSitePersonalDetailPage,wait:'slow'){$("#get-this-loan")}
	}

	def selectLoanOffers(){
		getThisLoan_btn.click()
		waitFor('slow'){!loaderBox.present}
	}
}
