package content.prosper

import geb.Page

class ABPBankInfoPage extends Page{

	static at ={
		makeYourPayment_text.text()=="How would you like to make your payments?"
	}
	static content={
		loaderBox ( required: false ){$('.spinnerContent')}
		makeYourPayment_text (wait:'slow'){$(".radio-header-text")}
		bankName_input (wait:'slow'){$("#bank-name")}
		altAccountHolderName_input (wait:'slow'){$("#second-account-holder-name")}
		routingNumber_input (wait:'slow'){$("#routing-number")}
		accountNumber_input (wait:'slow'){$("#account-number")}
		confirmAccountNumber_input (wait:'slow'){$("#confirm-account-number")}
		addBankAccount_btn (wait:'slow'){$("#add-bank-account")}
	}
	
	def submitABPBankInfoPage(){
		bankName_input ="Test Bank Name"
		altAccountHolderName_input="Alt Account Holder"
		routingNumber_input ="113024520"
		accountNumber_input="123456789"
		confirmAccountNumber_input="123456789"
		addBankAccount_btn.click()
		waitFor('slow'){!loaderBox.present}
	}
}
