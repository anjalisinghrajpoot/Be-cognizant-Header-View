// Package declaration
package utilities;
 
// Import necessary classes
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
/**
* This class provides utility methods for working with Excel files in the XLSX format.
*/
public class ExcelUtility {
 
  /**
   * Empty constructor.
   */
  public ExcelUtility() {
  }
 
  /**
   * Writes data from four lists to a new Excel file.
   * 
   * @param list1 The first list of data (strings) to be written.
   * @param list2 The second list of data (strings) to be written.
   * @param list3 The third list of data (strings) to be written.
   * @param list4 The fourth list of data (strings) to be written.
   * @throws IOException If an error occurs while writing data to the file.
   */
  public static void putData(List<String> list1, List<String> list2, List<String> list3, List<String> list4) throws IOException {
    // Create a new workbook
    XSSFWorkbook workbook = new XSSFWorkbook();
 
    // Create a new sheet and add it to the workbook
    XSSFSheet sheet = workbook.createSheet("Data");  // Sheet name set to "Data"
 
    // Get the maximum size of the lists to iterate through
    int maxSize = Math.max(Math.max(list1.size(), list2.size()), Math.max(list3.size(), list4.size()));
 
    // Iterate through each row up to the maximum size
    for (int i = 0; i < maxSize; i++) {
      // Create a new row in the sheet
      Row row = sheet.createRow(i);
 
      // Create cells in the current row
      Cell cell1 = row.createCell(0);
      Cell cell2 = row.createCell(1);
      Cell cell3 = row.createCell(2);
      Cell cell4 = row.createCell(3);
 
      // Write data to cells only if there is data available in the corresponding list
      if (i < list1.size()) {
        cell1.setCellValue(list1.get(i));
      }
      if (i < list2.size()) {
        cell2.setCellValue(list2.get(i));
      }
      if (i < list3.size()) {
        cell3.setCellValue(list3.get(i));
      }
      if (i < list4.size()) {
        cell4.setCellValue(list4.get(i));
      }
    }
 
    // Create a FileOutputStream to write data to the file
    FileOutputStream out = new FileOutputStream(
        new File(System.getProperty("user.dir") + "\\testData\\Data.xlsx"));
 
    // Write the workbook data to the file
    workbook.write(out);
 
    // Close the workbook and file output stream
    workbook.close();
    out.close();
  }
}