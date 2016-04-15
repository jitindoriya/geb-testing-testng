package content.prosper

import geb.Page

class PublicSiteBankInfoPage extends Page{

	
	static content ={
		linkedBankLogo_div {$(".bank-logo")}
		heading(wait:'slow'){$("#directPanel>h1")}
		addBankManually_btn (wait:'slow'){$(".bank-info-option-2>form>div:nth-child(4)>button")}
		newUserBankName_input (wait:'slow'){$(".optionContainer #bank-name")}
		newAccountHolderName_input (wait:'slow'){$(".optionContainer #first-account-holder-name")}
		newAltAccountHolderName_input (wait:'slow'){$(".optionContainer #second-account-holder-name")}
		newRoutingNumber_input (wait:'slow'){$(".optionContainer #routing-number")}
		newAccountNumber_input (wait:'slow'){$(".optionContainer #account-number")}
		newConfirmAccountNumber_input (wait:'slow'){$(".optionContainer #confirm-account-number")}
		addBankAccount_btn (wait:'slow'){$(".bank-info-option-2 [class='funnel-CTA row'] #add-bank-account")}
	}
	
	def submitManualBankOptions(){
		waitFor(wait:'slow'){linkedBankLogo_div.present}
		addBankManually_btn.click()
		
		newUserBankName_input = "TestBank"
//		newAccountHolderName_input =" Account holdername"
		newAltAccountHolderName_input ="Alt TestBank"
		newRoutingNumber_input ="113024520"
		newAccountNumber_input="123456789"
		newConfirmAccountNumber_input="123456789"
		addBankAccount_btn.click()
	}
}
