package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class MailBoxLocators {
	
	/*****************************************
	 * 			Logo locators 			*
	 *****************************************/
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/app-header-main/a" )
    public WebElement buttonLogo;
	
	/*****************************************
	 * 			Avatar locators 			*
	 *****************************************/
	@FindBy(how = How.XPATH, using = "//*[@id=\"header-files\"]/app-settings/div/div[2]" )
    public WebElement buttonAvatar;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"logout\"]" )
    public WebElement buttonLogout;
	
	/*****************************************
	 * 			Crear locators 			     *
	 *****************************************/
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"app-content\"]/app-sidebar/app-upload/div/div" )	
    public WebElement buttonCrear;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-sidebar/app-upload/div/app-upload-menu/div/app-upload-menu-item[3]" )	
    public WebElement buttonCrearCarpeta;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-sidebar/app-upload/div/app-upload-menu/div/app-upload-menu-item[3]/div/input" )	
    public WebElement textFieldCrearCarpeta;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-sidebar/app-upload/div/app-upload-menu/div/app-upload-menu-item[3]/div/button" )	
    public WebElement buttonAceptarNombreCrearCarpeta;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-sidebar/app-upload/div/app-upload-menu/div/app-upload-menu-item[1]" )	
    public WebElement buttonCargarArchivo;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-sidebar/app-upload/div/app-upload-menu/div/app-upload-menu-item[2]" )	
    public WebElement buttonCargarCarpeta;
	
	/*****************************************
	 * 			Search locators      		 *
	 *****************************************/
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"Search\"]" )
    public WebElement boxSearch;
	
	/*****************************************
	 * 					Section 			 *
	 *****************************************/
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-sidebar/app-filters/div/div[2]/span" )
    public WebElement buttonSectionFiles;
	
	/*****************************************
	 * 					Utils 			     *
	 *****************************************/
			
	
	@FindBy(how = How.XPATH, using = "/html/body/div[8]/div" )
    public WebElement uploadIsDone;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app-files-content\"]/div/app-list[2]/app-file" )
    public WebElement appListSearchFirstLonely;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-files-content/div/app-list[1]/app-file[1]/div/div[1]" )
    public WebElement appListSearchFirstMultiple;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-files-content/div/app-header[1]/app-breadcrumb/div[2]/icon/svg/use" )
    public WebElement buttonGridListView;

	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-files-content/div/app-header[2]/div/div[3]/span[1]")
	public WebElement modifiedFilter;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-files-content/div/app-list[1]/app-file[4]/div/div[1]/div[4]")
	public WebElement buttonMC;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/div/a" )
    public WebElement crossDetails;
	

	/*****************************************
	 * 			Menú Contextual			     *
	 *****************************************/
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-files-content/div/app-list[1]/app-file[4]/div/div[1]/app-menu/ul/li[7]")
	public WebElement addToFeature;
	
	/*****************************************
	 * 				Notifications			 *
	 *****************************************/
	
	@FindBy(how = How.CLASS_NAME, using = "Message" )
    public WebElement featureNotification;
	//*[@id="body-user"]/div[8]
	
	
	/*****************************************
	 * 				Icons					 *
	 *****************************************/
	
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/div/app-root/app-amx-files/app-files-content/div/app-list[1]/app-file[4]/div/div[1]/div[4]/app-file-name/div/div[1]/div[3]/div" )
    public WebElement featureIcon;
	
}
