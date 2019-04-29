def LOG_PATH = "logs"
def LOG_ARCHIVE = "${LOG_PATH}/archive"
def DEFAULT_PATTERN = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
def user_home = System.getProperty( "user.dir" )

appender("FILE", FileAppender) {
    // add a status message regarding the file property
    addInfo("Setting [file] property to [/var/log/findmylingo/findmylingo.log]")
    file = "/var/log/findmylingo/findmylingo.log"
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
logger("com.clackjones", DEBUG, ["CONSOLE"])
logger("org.springframework", DEBUG, ["CONSOLE"])