def LOG_PATH = "logs"
def LOG_ARCHIVE = "${LOG_PATH}/archive"
def DEFAULT_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
def user_home = System.getProperty( "user.dir" )

appender("FILE", FileAppender) {
    // add a status message regarding the file property
    addInfo("Setting [file] property to [${user_home}/services_wales.log]")
    file = "${user_home}/services_wales.log"
    encoder(PatternLayoutEncoder) {
        pattern = DEFAULT_PATTERN
    }
}

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = DEFAULT_PATTERN
    }
}

root(OFF, ["Console-Appender"])
logger("com.clackjones.cymraeg", INFO, ["CONSOLE", "FILE"])

// logging hibernate
logger("org.hibernate.SQL", DEBUG, ["CONSOLE", "FILE"])
logger("org.hibernate.type", TRACE, ["CONSOLE", "FILE"])