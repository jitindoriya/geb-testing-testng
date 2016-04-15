package content.prosper

import geb.Page

class PublicSiteSignInPage extends Page {
	
//	static url = "http://support.qa34.c1.dev/Login.aspx?ReturnUrl=%2fHome.aspx"
	
	static at ={
			waitFor('slow'){$('div[ng-app=sign_in]').displayed}
	}
	static content={
		//Login Form
		userEmailAddress_in (wait:'slow'){$(name:'email')}
		userPassword_in (wait:'slow'){$('input[data-q=modal-password]')}
		continue_btn (to:PublicSiteAccountOverviewPage,wait:true){$('button[id=continue]')}
		
		
	}
	
	def loginAsProsperUser(emailAddress,password){
		userEmailAddress_in<<emailAddress
		userPassword_in<<password
		continue_btn.click()
	}

}
