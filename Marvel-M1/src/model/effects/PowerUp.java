package model.effects;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;
public class PowerUp extends Effect {
	public PowerUp(int duration) {
		super("PowerUp", duration, EffectType.BUFF);
		
	}
	public void apply(Champion c){
		int a,b;
		DamagingAbility d = new DamagingAbility();
		HealingAbility h  = new HealingAbility();
		int i =0;
		while(i<c.getAppliedEffects().size()){
			if(c.getAppliedEffects().get(i) instanceof PowerUp){
				a = (int)(d.getDamageAmount()/100)*120;
				b=(int)(h.getHealAmount()/100)*120;
			}
			d.setDamageAmount(a);
			b.setHealAmount(b);
		}
		}
		public void remove(Champion c){	
		}
	
}
