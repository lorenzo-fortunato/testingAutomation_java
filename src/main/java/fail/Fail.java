package fail;

import org.apache.log4j.Logger;
import utilities.Constants;
import utilities.ExcelUtils;

import java.io.IOException;

public class Fail {
    public static void fail(Logger log, String message, int row, String filePath) throws IOException {
        ExcelUtils excelUtils = new ExcelUtils();
        log.error("******* " + message + " *******");
        excelUtils.setCellValue(row, Constants.Col_result, "FAIL", filePath);
        log.warn("******* Written FAIL in result cell *******");
    }
}
