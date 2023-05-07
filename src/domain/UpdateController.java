package domain;

public class UpdateController extends Observer {

	private static UpdateController instance;
	
	private void UpdateController() {
		
	}
	
	public static UpdateController getInstance() {
		if(instance == null) {
			instance = new UpdateController();
			return instance;
		}
		else {
			return instance;
		}
	}
	
	@Override
	public void update(Observable subject) {
		
		
	}
}
