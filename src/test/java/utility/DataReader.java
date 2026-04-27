package utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataReader {
	
	public static Object[] getCSVData(String filePath) throws IOException {
		List<Object[]> data = new ArrayList<>();
		BufferedReader bf = new BufferedReader(new FileReader(filePath));
		
		String line;
		bf.readLine();
		
		while((line=bf.readLine())!=null) {
			String[] values = line.split(",");
			data.add(values);
		}
		bf.close();
		
		return data.toArray(new Object[0][]);
		
	}
	
	public static List<String[]> getCSVDataList(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        
        String line;
        br.readLine(); 

        while((line = br.readLine()) != null) {
            data.add(line.split(","));
        }

        br.close();
        return data;
    }

	
	public static Object[][] getExceldata(String path, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int rows =  sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int i=1; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				data[i-1][j]=sheet.getRow(i).getCell(j).toString();
				
			}
		}
		wb.close();
		return data;
		
		
		
	}
	
	@DataProvider(name = "loginData")
    public static Object[][] getLoginData() throws IOException {
		String validUser = ConfigReader.getProperty("validUser");
		String validPass =ConfigReader.getPassword("validPass");
		String invalidUser =ConfigReader.getProperty("invalidUser");
		String invalidPass = ConfigReader.getPassword("invalidPass");
		 
        return new Object[][] {
            {
                validUser,
                validPass,
                true
            },

            {
                invalidUser,
                invalidPass,
                false
            }
        };
    }


}
