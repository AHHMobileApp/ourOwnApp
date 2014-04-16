package inventory;

public interface Observer {
	public void update(TableUpdater TU,int row, int qty);
}
