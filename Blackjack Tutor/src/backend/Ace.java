package backend;

public class Ace extends Card {

	private boolean soft = true;
	
	public Ace(Suit suit) {
		super(Value.ACE, suit);
	}

	public boolean isSoft() {
		return soft;
	}

	public void setSoft(boolean soft) {
		this.soft = soft;
	}
}
