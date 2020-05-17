package AutomationPractice;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AssignmentDataDrivenTest {
	
	WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
	 
    @BeforeTest
	public void initialization() throws IOException{
	    
    	// To set the path of the Chrome driver.
		
    	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver =new ChromeDriver();
	     
	    // To launch URL
	    driver.get("http://automationpractice.com/index.php");
	    
	    // To maximize the browser
	    driver.manage().window().maximize();
	    
	    // implicit wait
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    
					
    }
		  
	@Test
	public void BookingFLow() throws IOException, InterruptedException{
		// Import excel sheet.
		File src=new File(System.getProperty("user.dir")+"\\src\\AutomationPractice\\ExportExcel.xlsx");		  
		// Load the file.
		FileInputStream fis = new FileInputStream(src);
		// Load the workbook.
		workbook = new XSSFWorkbook(fis);
		
		// Load the sheet in which data is stored.
		sheet= workbook.getSheetAt(0);
		
		 // Load the properties File		
	    Properties obj = new Properties();					
	    FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\AutomationPractice\\application.properties");									
	    obj.load(objfile);
		
		
		
			for(int i=1; i<=sheet.getLastRowNum(); i++){
				
				
				driver.findElement(By.xpath(obj.getProperty("Sign_in"))).click();
				
				// Import data for Email.
				cell = sheet.getRow(i).getCell(0);
				cell.setCellType(CellType.STRING);
				driver.findElement(By.xpath(obj.getProperty("Email_id"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Email_id"))).sendKeys(cell.getStringCellValue());
				
				//To click on Create Account button
				driver.findElement(By.xpath(obj.getProperty("CreateAccount"))).click();
				
				
				// Inputting data's for registration	
				
				// Import data for Title				
				String exptitle = "Mr";
				String actualTitle = sheet.getRow(i).getCell(1).getStringCellValue();
				
				if (actualTitle.equals(exptitle))
			      {
					driver.findElement(By.xpath(obj.getProperty("Title_Mr"))).click();
			      }
			    else 
			      {
			    	  driver.findElement(By.xpath(obj.getProperty("Title_Mrs"))).click();  
			      }				
				
				// Import data for First Name				
				driver.findElement(By.xpath(obj.getProperty("First_Name"))).clear();
				driver.findElement(By.xpath(obj.getProperty("First_Name"))).sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
				
				// Import data for Last Name				
				driver.findElement(By.xpath(obj.getProperty("Last_Name"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Last_Name"))).sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
				
				// Import data for Password				
				driver.findElement(By.xpath(obj.getProperty("Password"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Password"))).sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
				
				// Import data for Date of Birth(Date)				
				String Date = sheet.getRow(i).getCell(5).getStringCellValue();				
				Select DOB_Date = new Select(driver.findElement(By.xpath(obj.getProperty("DOB_Date"))));
				DOB_Date.selectByValue(Date);

				// Import data for Date of Birth(Month)				
				String Month = sheet.getRow(i).getCell(6).getStringCellValue();				
				Select DOB_Month = new Select(driver.findElement(By.xpath(obj.getProperty("DOB_Month"))));
				DOB_Month.selectByVisibleText(Month+" ");
				
				// Import data for Date of Birth(Month)				
				String Year = sheet.getRow(i).getCell(7).getStringCellValue();				
				Select DOB_Year = new Select(driver.findElement(By.xpath(obj.getProperty("DOB_Year"))));
				DOB_Year.selectByValue(Year);
				
				//To click on Sign up for our newsletter! button
				driver.findElement(By.xpath(obj.getProperty("Sign_upcheckbox"))).click();
				
				//To click on Receive special offers from our partners! button
				driver.findElement(By.xpath(obj.getProperty("SpecialOffers"))).click();
				
				// Import data for Company				
				driver.findElement(By.xpath(obj.getProperty("Company"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Company"))).sendKeys(sheet.getRow(i).getCell(8).getStringCellValue());
								
				// Import data for Address				
				driver.findElement(By.xpath(obj.getProperty("Address"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Address"))).sendKeys(sheet.getRow(i).getCell(9).getStringCellValue());
				
				// Import data for City				
				driver.findElement(By.xpath(obj.getProperty("City"))).clear();
				driver.findElement(By.xpath(obj.getProperty("City"))).sendKeys(sheet.getRow(i).getCell(10).getStringCellValue());
				
				// Import data for State			
				String State = sheet.getRow(i).getCell(11).getStringCellValue();				
				Select Add_State = new Select(driver.findElement(By.xpath(obj.getProperty("State"))));
				Add_State.selectByVisibleText(State);								
				
				// Import data for PostalCode				
				driver.findElement(By.xpath(obj.getProperty("PostalCode"))).clear();
				driver.findElement(By.xpath(obj.getProperty("PostalCode"))).sendKeys(sheet.getRow(i).getCell(12).getStringCellValue());
				
				// Import data for Country			
				String Country = sheet.getRow(i).getCell(13).getStringCellValue();				
				Select Add_Country = new Select(driver.findElement(By.xpath(obj.getProperty("Country"))));
				Add_Country.selectByVisibleText(Country);	
				
				// Import data for Add_Info				
				driver.findElement(By.xpath(obj.getProperty("Add_Info"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Add_Info"))).sendKeys(sheet.getRow(i).getCell(14).getStringCellValue());
				
				// Import data for Home_Phone				
				driver.findElement(By.xpath(obj.getProperty("Home_Phone"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Home_Phone"))).sendKeys(sheet.getRow(i).getCell(15).getStringCellValue());
								
				// Import data for Mobile_Phone				
				driver.findElement(By.xpath(obj.getProperty("Mobile_Phone"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Mobile_Phone"))).sendKeys(sheet.getRow(i).getCell(16).getStringCellValue());
				
				// Import data for Add_Alias				
				driver.findElement(By.xpath(obj.getProperty("Add_Alias"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Add_Alias"))).sendKeys(sheet.getRow(i).getCell(17).getStringCellValue());
				
				//To click on Register button
				driver.findElement(By.xpath(obj.getProperty("Register"))).click();
				
				//To click on Sign Out button
				driver.findElement(By.xpath(obj.getProperty("Sign_out"))).click();
				
				
				// Import data for Email.
				cell = sheet.getRow(i).getCell(0);
				cell.setCellType(CellType.STRING);
				driver.findElement(By.xpath(obj.getProperty("Login_id"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Login_id"))).sendKeys(cell.getStringCellValue());
				
				// Import data for Password				
				driver.findElement(By.xpath(obj.getProperty("Login_pwd"))).clear();
				driver.findElement(By.xpath(obj.getProperty("Login_pwd"))).sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
				
				
				//To click on Sign in button
				driver.findElement(By.xpath(obj.getProperty("Login_Sign_in"))).click();
				
				//To click on Women button
				driver.findElement(By.xpath(obj.getProperty("Women"))).click();
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1000)");				
				
				//To click on Add to Cart button
				driver.findElement(By.xpath(obj.getProperty("AddtoCart"))).click();				
						
				// Import data for AddQuality				
				driver.findElement(By.xpath(obj.getProperty("AddQuantity"))).clear();
				driver.findElement(By.xpath(obj.getProperty("AddQuantity"))).sendKeys("2");
				
				//To click on ProceedtoCheckout button
				driver.findElement(By.xpath(obj.getProperty("AddToCart1"))).click();	
				
				//To click on ProceedtoCheckout button
				driver.findElement(By.xpath(obj.getProperty("ProceedtoCheckout"))).click();		
				
				
				// To Calculate the Total_Price 				
				String Unit_Price = driver.findElement(By.xpath(obj.getProperty("Unit_Price"))).getText();				
				String Unit_Price_dlr = Unit_Price.replace("$","");						
			    float Unit_Prc = Float.parseFloat(Unit_Price_dlr);				    
			    float Total_Prod_Price = Unit_Prc * 2;			    
			    float Calc_Total_Price = Total_Prod_Price + 2;			    
			  
			    // To get the Total_Price 				
				String Total = driver.findElement(By.xpath(obj.getProperty("Total"))).getText();				
				String Total_Price_dlr = Total.replace("$","");
				float Act_Total_Price = Float.parseFloat(Total_Price_dlr);
				
				
				if (Calc_Total_Price == Act_Total_Price){
					//To click on ProceedtoCheckout1 button
					driver.findElement(By.xpath(obj.getProperty("ProceedtoCheckout1"))).click();
					
				}			
				
				//To click on ProceedtoCheckout2 button
				driver.findElement(By.xpath(obj.getProperty("ProceedtoCheckout2"))).click();
				
				//To click on CGV checkbox
				driver.findElement(By.xpath(obj.getProperty("CGV"))).click();
				
				//To click on ProceedtoCheckout3 button
				driver.findElement(By.xpath(obj.getProperty("ProceedtoCheckout3"))).click();
				
				
				
				// To Calculate the Total_Price in payment Page				
				String Unit_Price_Pay = driver.findElement(By.xpath(obj.getProperty("Unit_Price"))).getText();				
				String Unit_Price_dlr_Pay = Unit_Price_Pay.replace("$","");						
			    float Unit_Prc_Pay = Float.parseFloat(Unit_Price_dlr_Pay);				    
			    float Total_Prod_Price_Pay = Unit_Prc_Pay * 2;			    
			    float Calc_Total_Price_Pay = Total_Prod_Price_Pay + 2;			    
			  
			    // To get the Total_Price in payment page			
				String Total_Pay = driver.findElement(By.xpath(obj.getProperty("Total"))).getText();				
				String Total_Price_dlr_Pay = Total_Pay.replace("$","");
				float Act_Total_Price_Pay = Float.parseFloat(Total_Price_dlr_Pay);
				
				
				if (Calc_Total_Price_Pay == Act_Total_Price_Pay){
					//To click on ProceedtoCheckout3 button
					driver.findElement(By.xpath(obj.getProperty("Payment_mode"))).click();
					
				}
				
				// To get the Total_Price in payment page			
				String Total_Summary = driver.findElement(By.xpath(obj.getProperty("Amount"))).getText();				
				String Total_Price_dlr_Summary = Total_Summary.replace("$","");
				float Act_Total_Price_Summary = Float.parseFloat(Total_Price_dlr_Summary);
				
				if (Calc_Total_Price_Pay == Act_Total_Price_Summary){
					//To click on ProceedtoCheckout3 button
					driver.findElement(By.xpath(obj.getProperty("Confirm_order"))).click();
					
				}				
				
				JavascriptExecutor jse1 = (JavascriptExecutor)driver;
				jse1.executeScript("window.scrollBy(0,300)");
				
				// To get the Total_Price in payment page			
				String Total_Confirm = driver.findElement(By.xpath(obj.getProperty("Price"))).getText();				
				String Total_Price_dlr_Confirm = Total_Confirm.replace("$","");
				float Act_Total_Price_Confirm = Float.parseFloat(Total_Price_dlr_Confirm);
				
				if (Calc_Total_Price_Pay == Act_Total_Price_Confirm){
					//To Print Total_Price
					System.out.println("Total Price from Payment Page" + Total_Confirm);
					
				}
				
				//To take screenshot on order summary page
				takeSnapShot(driver, System.getProperty("user.dir")+"\\src\\AutomationPractice\\Screenshots\\ConfirmOrder.jpg") ;
				
				//To click on View my customer account link
				driver.findElement(By.xpath(obj.getProperty("View_Cus_Acc"))).click();
				
				//To click on Order History link
				driver.findElement(By.xpath(obj.getProperty("Order_His"))).click();
				
				// To get the Total_Price in Order History Page			
				String Total_Order_His = driver.findElement(By.xpath(obj.getProperty("Order_His_TableValue"))).getText();				
				String Total_Price_dlr_Order_His = Total_Order_His.replace("$","");
				float Act_Total_Price_Order_His = Float.parseFloat(Total_Price_dlr_Order_His);
				
				if (Calc_Total_Price_Pay == Act_Total_Price_Order_His){
					//To Print Total_Price
					System.out.println("Total Price from Order History Page" + Total_Order_His);
					
				}
					
				//To take screenshot on order History page
				takeSnapShot(driver, System.getProperty("user.dir")+"\\src\\AutomationPractice\\Screenshots\\OrderHistory.jpg") ;    
								
				//To write data in the excel
				 FileOutputStream fos=new FileOutputStream(src);
				 
				 // Message to be written in the excel sheet
				     String message = "Pass";
				     
				     // Create cell where data needs to be written.
				    sheet.getRow(i).createCell(18).setCellValue(message);
				         
				     // finally write content
				    workbook.write(fos);

				 // close the file
				fos.close();		
				
				// To close the Driver
				driver.close();
			}
			
}

	private void takeSnapShot(WebDriver webdriver, String fileWithPath) throws IOException {
		
		// TODO Auto-generated method stub
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);                

        //Move image file to new destination
        File DestFile=new File(fileWithPath);
           
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
        
	}
}