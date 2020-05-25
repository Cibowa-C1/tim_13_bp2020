package controller;

public class ActionManager {
	
	private AddAction add;
	private DeleteAction delete;
	private UpdateAction update;
	private FilterAction filter;
	private SortAction sort;
	
	
	public ActionManager() {
		initActions();
	}

	private void initActions() {
		add = new AddAction();
		delete = new DeleteAction();
		update = new UpdateAction();
		filter = new FilterAction();
		sort = new SortAction();
		
	}

	public AddAction getAdd() {
		return add;
	}
	
	
	public SortAction getSort() {
		return sort;
	}

	public FilterAction getFilter() {
		return filter;
	}

	public DeleteAction getDelete() {
		return delete;
	}

	public UpdateAction getUpdate() {
		return update;
	}
	
}
