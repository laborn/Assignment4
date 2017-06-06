package command;

public class Commander {
	
	private Command command;
	
	public void setCommand(Command command){
		
		this.command = command;
		
	}

	
	public void AddInvestmentCommand(){
		
		command.execute();
	
	}

}
