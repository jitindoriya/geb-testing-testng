package content.support

import content.modules.LoginModule;
import geb.Page

class SupportLoginPage extends Page{
	
	static url="https://support.stg.circleone.com/Home.aspx"
	
	static at={
		heading.text()=="You must log in before you access this page."
	}

	static content={
		heading(wait:'slow'){$("#lblAuthenticationMessage")}
		login{module LoginModule}
	}
	
}
