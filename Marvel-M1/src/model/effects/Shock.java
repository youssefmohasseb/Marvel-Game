package model.effects;

import model.world.Champion;

public class Shock extends Effect {

	public Shock(int duration) {
		super("Shock", duration, EffectType.DEBUFF);
		System.out.print(false);
	}
	public void apply(Champion c){
		
	}
	public void remove(Champion c){
		
	}

}
