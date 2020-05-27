package controller;

public class ActionManager {
	
	private AddAction add;
	private DeleteAction delete;
	private UpdateAction update;
	private FilterSortAction filter;
	private RelationAction relation;
	
	
	public ActionManager() {
		initActions();
	}

	private void initActions() {
		add = new AddAction();
		delete = new DeleteAction();
		update = new UpdateAction();
		filter = new FilterSortAction();
		relation = new RelationAction();
		
	}

	public AddAction getAdd() {
		return add;
	}
	
	
	public RelationAction getRelation() {
		return relation;
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
