package controller;

public class ActionManager {
	
	private AddAction add;
	private DeleteAction delete;
	private RefreshAction refresh;
	
	
	
	public ActionManager() {
		initActions();
	}

	private void initActions() {
		add = new AddAction();
		delete = new DeleteAction();
		refresh = new RefreshAction();
	}

	public AddAction getAdd() {
		return add;
	}


	public DeleteAction getDelete() {
		return delete;
	}

	public RefreshAction getRefresh() {
		return refresh;
	}
	
}
