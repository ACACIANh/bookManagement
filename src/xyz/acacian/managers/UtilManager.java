package xyz.acacian.managers;

public enum UtilManager {
	INSTANCE;
	
	private UtilManager() {
	}

	public UtilManager getInstance() {
		return INSTANCE;
	}
	
	public static final int OUT_OF_INDEX = -1;

}
