package model.effects;

import model.world.Champion;

public class Stun extends Effect {

	public Stun(int duration) {
		super("Stun", duration, EffectType.DEBUFF);
	}

	public void apply(Champion c){
		
	}
	public void remove(Champion c){
		
	}
}
