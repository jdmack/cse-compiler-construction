
public class IdValueTuple {
	String id;
	STO value;
	
	public IdValueTuple(String id, STO value) {
		this.id = id;
		this.value = value;
	}
	
	public String getId() {
		return id;
	}
	
	public STO getValue() {
		return value;
	}
}
