package steps;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.*;
import lib.ConsoleColors;
import pages.actions.MailBoxActions;
import utils.ConfigAttributes;
import utils.ConfigSingleton;

public class MailBoxGeneralSteps {
	
	private final ConfigSingleton config = ConfigSingleton.getInstance();
	
	MailBoxActions mailBoxActions = new MailBoxActions();
	
	@And( "I search {string}" )
	public void iSearch( String name ) throws InterruptedException {
		assertTrue( mailBoxActions.isOnTheSearch( name ));
	}
	
	@And( "Verify the uploaded file" )
	public void verifyTheUploadedFile() throws InterruptedException {
		assertTrue( mailBoxActions.isOnTheSearch( mailBoxActions.getTmpName() ));
	}
	
	@And( "I click on view button" )
	public void iClickOnViewButton() throws InterruptedException {
		mailBoxActions.clickViewButton();
	}
	
	@When( "I change to files section" )
	public void iChangeToFileSection() throws InterruptedException {
		mailBoxActions.changetToFileSection();
	}
	
	@When( "I create a new directory" )
	public void iCreateANewDirectory () throws InterruptedException {
		mailBoxActions.createDirectory();
	}
	
	@When( "I upload a new file" )
	public void iUploadANewFile() throws InterruptedException {
		mailBoxActions.uploadFiles( config.getProperty(ConfigAttributes.Crate_File_Command), config.getProperty(ConfigAttributes.File_Extension)  );
	} 
	
	@When( "I upload a new directory" )
	public void iUploadANewDirectory() throws InterruptedException {
		mailBoxActions.uploadDirectory( config.getProperty(ConfigAttributes.Crate_File_Command), config.getProperty(ConfigAttributes.File_Extension)  );
	} 
	
	@And("I click in the Modified filter")
	public void iClickInModifiedFilter() throws InterruptedException {
		mailBoxActions.clickModifiedFilter();
	}
	
	@And ("I close the details tab")
	public void iCloseDetailsTab () throws InterruptedException {
		mailBoxActions.closeDetails();
	}
	
	@Then ("I add to feature a directory")
	public void iAddToFetureADirectory() throws InterruptedException {
		mailBoxActions.addToFeature();

	}
}
