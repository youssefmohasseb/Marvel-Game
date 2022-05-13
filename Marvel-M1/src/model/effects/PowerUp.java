package model.effects;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;
public class PowerUp extends Effect {
	public PowerUp(int duration) {
		super("PowerUp", duration, EffectType.BUFF);
		
	}
	public void apply(Champion c){
		
		}
		public void remove(Champion c){	
		}
	
}
