package content.support

import geb.Page

class SupportHomePage extends Page{

	static at={
		waitFor(wait:'slow'){abpSection.present}
	}
	static content={
		abpSection(wait:'slow'){$("#abp-form-directmail")}
		abpEmailAddress_input (wait:'slow'){$("#abp-dm-email")}
		abpSelectPartner_dropdown(wait:'slow'){$("#abp-form-select")}
		abpOfferCode_input(wait:'slow'){$("#abp-dm-referral-code")}
		abpStartApp_btn(wait:'slow'){$("#abp-directmail-btn")}
	}

	def submitABPviaDMPartner(){

		abpSelectPartner_dropdown = "Direct Mail"
		return abpEmailAddress_input ='automation'+Math.abs(new Random().nextInt() % 600) + 1+'@p2pcredit.com'
	}
}
