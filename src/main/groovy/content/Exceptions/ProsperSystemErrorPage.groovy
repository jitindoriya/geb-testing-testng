package content.Exceptions

import geb.Page

class ProsperSystemErrorPage extends Page {

	static at={
		heading.text=="Prosper System Error"
	}
	
	static content={
		heading (wait:'slow'){$("h1",2)}
	}
}
