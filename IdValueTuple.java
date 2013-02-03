
public class IdValueTuple {
	String id;
	STO sto;
	
	public IdValueTuple(String id, STO sto) {
		this.id = id;
		this.sto = sto;
	}
	
	public String getId() {
		return id;
	}
	
	public STO getSTO() {
		return sto;
	}
}
