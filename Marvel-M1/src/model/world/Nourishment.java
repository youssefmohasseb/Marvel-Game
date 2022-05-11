package model.world;
public class Nourishment{
	private String name;
	private int nourishmentAmount;
	private int manaIncreased;
	private NourishmentType type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNourishmentAmount() {
		return nourishmentAmount;
	}
	public void setNourishmentAmount(int nourishmentAmount) {
		this.nourishmentAmount = nourishmentAmount;
	}
	public void setManaIncreased(int manaIncreased) {
		this.manaIncreased = manaIncreased;
	}
	public NourishmentType getType() {
		return type;
	}
public Nourishment(String name,int nourishmentAmount,int manaIncreased,NourishmentType type){
	this.name = name;
	this.nourishmentAmount = nourishmentAmount;
	this.manaIncreased = manaIncreased;
	this.type =type;
}
}
