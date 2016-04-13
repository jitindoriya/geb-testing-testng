package content.prosper

import geb.Page

class PublicSiteBankInfoPage extends Page{

	
	static content ={
		heading(wait:'slow'){$("#directPanel>h1")}
		addBankManually_btn (wait:'slow'){$(".bank-info-option-2>form>div:nth-child(4)>button")}
		newUserBankName_input (wait:'slow'){$("input",1,id='bank-name')}
	}
}
