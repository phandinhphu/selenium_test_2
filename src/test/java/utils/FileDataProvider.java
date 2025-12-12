package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class FileDataProvider {
	@DataProvider(name = "excelData")
	public Object[][] getData(ITestContext context) throws Exception {
	    String file = context.getCurrentXmlTest().getParameter("excelPath");
	    FileInputStream fis = new FileInputStream(file);
	    Workbook wb = WorkbookFactory.create(fis);
	    Sheet sheet = wb.getSheetAt(0);

	    int rows = sheet.getLastRowNum();  // dùng getLastRowNum thay vì getPhysicalNumberOfRows
	    int cols = sheet.getRow(0).getPhysicalNumberOfCells();

	    List<Map<String, String>> dataList = new ArrayList<>();

	    // Bỏ header → bắt đầu từ i = 1
	    for (int i = 1; i <= rows; i++) {
	        Row row = sheet.getRow(i);
	        if (row == null) continue;

	        // Kiểm tra dòng có trống hoàn toàn không
	        boolean isEmptyRow = true;
	        for (int j = 0; j < cols; j++) {
	            Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	            if (cell != null && !cell.toString().trim().isEmpty()) {
	                isEmptyRow = false;
	                break;
	            }
	        }
	        if (isEmptyRow) continue;  // bỏ qua dòng trống thật

	        Map<String, String> rowData = new HashMap<>();
	        for (int j = 0; j < cols; j++) {
	            String key = sheet.getRow(0).getCell(j).getStringCellValue();
	            Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	            rowData.put(key, cell.toString().trim());
	        }
	        dataList.add(rowData);
	    }

	    wb.close();
	    fis.close();

	    Object[][] data = new Object[dataList.size()][1];
	    for (int i = 0; i < dataList.size(); i++) {
	        data[i][0] = dataList.get(i);
	    }

	    return data;
	}

}
