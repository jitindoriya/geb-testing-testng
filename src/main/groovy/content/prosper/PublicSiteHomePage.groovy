package content.prosper

import geb.Page

class PublicSiteHomePage extends Page {

	static url ="/"
	static content = {
		//homewidget
		loanAmount (wait:'slow'){$(name:'loan_amount')}
		loanPurpose(wait:'slow') {$('#listing-category-id')}
		creditQuality(wait:true) {$('#credit_quality_id')}
		checkYourRateButton(wait:true){$("button[data-q='homepage-check-rate-button']")}
		
		//header
		signIn_btn(to:PublicSiteSignInPage){$('a[data-q=header-sign-in]')}
		
		
	}
}
