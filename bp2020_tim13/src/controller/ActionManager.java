package controller;

public class ActionManager {
	
	private AddAction add;
	private DeleteAction delete;
	private UpdateAction update;
	private FilterSortAction filter;
	
	
	public ActionManager() {
		initActions();
	}

	private void initActions() {
		add = new AddAction();
		delete = new DeleteAction();
		update = new UpdateAction();
		filter = new FilterSortAction();
		
	}

	public AddAction getAdd() {
		return add;
	}
	

	public FilterSortAction getFilter() {
		return filter;
	}

	public DeleteAction getDelete() {
		return delete;
	}

	public UpdateAction getUpdate() {
		return update;
	}
	
}
