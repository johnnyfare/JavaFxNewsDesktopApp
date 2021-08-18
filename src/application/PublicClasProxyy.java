package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class PublicClasProxyy implements LetsFindIsJournalist{
	private boolean isjournalist=false;
	private LetsFindOutIfIsJournalistImPl comamad;
	private String yes;
	
	public PublicClasProxyy(User user,BorderPane snow){
		if(user.returnIsJournalist().equals("T")) {
			this.isjournalist=true;
			comamad = new LetsFindOutIfIsJournalistImPl();
			this.yes=user.returnIsJournalist();
		}
	}

	@Override
	public void runCommand(BorderPane id) {
		if(this.isjournalist) {
			comamad.runCommand(id);
		}
		else {
			//System.out.println(this.yes);
		}
	}

}
