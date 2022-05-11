package model.world;
public class LootedItem extends Nourishment{
	private int actionPointsNeeded;
	public  LootedItem(String name,int nourishmentAmount,int manaIncreased,NourishmentType type, int actionPointsNeeded){
		super(name,nourishmentAmount,manaIncreased,type);
	    this.actionPointsNeeded = actionPointsNeeded;
	}
	public int getActionPointsNeeded() {
		return actionPointsNeeded;
	}

	public void setActionPointsNeeded(int actionPointsNeeded) {
		this.actionPointsNeeded = actionPointsNeeded;
	}

}
