log4j {
	rootLogger="DEBUG, A1"
	appender.A1 = "org.apache.log4j.ConsoleAppender"
	appender.'A1.layout' = "org.apache.log4j.PatternLayout"
	if (System.properties['env'] == 'prod') {
		appender.'A1.layout.ConversionPattern'="prod %d{ABSOLUTE} [%t] %-5p %c %x - %m%n"
	} else {
		appender.'A1.layout.ConversionPattern'="STG %d{ABSOLUTE} [%t] %-5p %c %x - %m%n"
	}
	logger.org.apache.http = "fatal"
	logger.com.gargoylesoftware = "fatal"
}