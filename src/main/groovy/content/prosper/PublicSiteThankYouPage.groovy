package content.prosper

import geb.Page

class PublicSiteThankYouPage extends Page {

	static at ={
		heading.text()=="Thank you for your loan request"
	}
	static content={
		heading(wait:'slow'){$(".thank-you-secondary-header")}
		goToMyAccount_btn(wait:'slow'){$(".thank-you .button")}
	}
	
}
