package content.prosper

import geb.Page

class ABPThankYouPage extends Page{
	
	
	static at ={
		thankYouPage_text.text()=="Thank you! Application by phone completed."
	}

	static content ={
		thankYouPage_text (wait:'slow') {$(".panel-container>h1")}
	}
}
