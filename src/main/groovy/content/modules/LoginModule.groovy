package content.modules

import geb.Module

class LoginModule extends Module{
	
	static content={
		supportSiteEmailAddress_input{$("#tbEmail")}
		supportSitePassword_input{$("#tbPwd")}
		supportSiteSignIn_btn {$("#btnSubmit")}
	}

	def loginToSupportSite(){
		supportSiteEmailAddress_input ="admin@circleone.stg"
		supportSitePassword_input ="Password23"
		supportSiteSignIn_btn.click()
	}
}
