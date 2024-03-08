package utilities;

import org.apache.log4j.*;

public class TestLogger {
    public static Logger setLogger() {
        BasicConfigurator.configure();
        Logger Log = LogManager.getLogger("MyLogger");

        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("logfile.txt");
        fileAppender.setLayout(new SimpleLayout());

        Log.addAppender(fileAppender);
        fileAppender.activateOptions();
        return Log;
    }
}
