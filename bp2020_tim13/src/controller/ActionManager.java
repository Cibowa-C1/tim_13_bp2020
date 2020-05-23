package controller;

public class ActionManager {
	
	private AddAction add;
	private CommitAction commit;
	private DeleteAction delete;
	private RefreshAction refresh;
	
	
	
	public ActionManager() {
		initActions();
	}

	private void initActions() {
		add = new AddAction();
		commit = new CommitAction();
		delete = new DeleteAction();
		refresh = new RefreshAction();
	}

	public AddAction getAdd() {
		return add;
	}

	public CommitAction getCommit() {
		return commit;
	}

	public DeleteAction getDelete() {
		return delete;
	}

	public RefreshAction getRefresh() {
		return refresh;
	}
	
}
