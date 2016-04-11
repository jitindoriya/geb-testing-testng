package content.prosper

import geb.Page

class PublicSiteLoanOfferPage extends Page{
	
	static at ={
		heading.text()=="Your customized loan offer:"
	}
	
	static content={
		heading (wait:'slow'){$('h2.main-offer-table-header')}
	}

}
