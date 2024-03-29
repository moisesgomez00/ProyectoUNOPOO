package core;

/**
 * Maneja todas las acciones y las cartas que tiene un jugador en un a partida UNO.
 * @author leonardo
 *
 */
public class Player {

	private String name;
	private int id;
	private Hand hand;
	private boolean UNO = false; /**true si dijo uno.*/
	
	/**Construnctor vacío de la clase.*/
	public Player() {
		
	}
	/**
	 * Constructor sobrecargado de la clase.
	 * @param name Nombre del jugador.
	 * @param id Identificación del jugador.
	 * @param hand Mano que administra las cartas del jugador.
	 * @param turn Estado booleano que especifica si el jugador está en turno.
	 */
	public Player(String name, int id, Hand hand, boolean UNO) {
		this.setName(name);
		this.setId(id);
		this.setHand(hand);
		this.setUNO(UNO);
	}
	
	/**
	 * Constructor sobrecargado de la clase.
	 * @param name Nombre del jugador.
	 * @param hand Mano que administra las cartas del jugador.
	 * @param turn Estado booleano que especifica si el jugador está en turno.
	 */
	public Player(String name, Hand hand) {
		this.setName(name);
		this.setHand(hand);
	}
	
	/**
	 * Toma la primera carta de la baraja.
	 */
	public void takeCard() {
		this.hand.takeCard();
	}
	
	/**
	 * Toma las seis primeras cartas de la baraja.
	 */
	public void drawSix() {
		for (int i = 0; i < 6; i++) {
			this.hand.takeCard();
		}
	}
	
	/**
	 * Toma las cuatro primeras cartas de la baraja.
	 */
	public void drawFour() {
		for (int i = 0; i < 4; i++) {
			this.hand.takeCard();
		}
	}
	
	/**
	 * Toma las seis primeras cartas de la baraja.
	 */
	public void drawTwo() {
		for (int i = 0; i < 2; i++) {
			this.hand.takeCard();
		}
	}
	
	/**
	 * Suelta una carta en la pila de descarte.
	 * @param card Carta que soltará.
	 */
	public void dropCard(Card card) {
		try {
			this.hand.dropCard(card);
			
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("El jugador no tiene la carta: %s", card));
		}
	}
	
	/**
	 * Crea una representación JSON del objeto.
	 * @param tab Cantidad de tabs que tendrá al inicio.
	 * @return JSON del objeto.
	 */
	public String toJSON(int tab) {
		StringBuilder result = new StringBuilder("");
		
		result.append(String.format("%s{\n", "\t".repeat(tab)));
		result.append(String.format("%s\"id\":%s,\n", "\t".repeat(tab+1),this.id));
		result.append(String.format("%s\"UNO\":%s,\n", "\t".repeat(tab+1),this.isUNO()));
		result.append(String.format("%s\"name\":\"%s\",\n", "\t".repeat(tab+1),this.name));
		result.append(String.format("%s\"hand\":%s", "\t".repeat(tab+1),(this.hand.getCards().isEmpty())?"null\n":String.format("\n   %s\n", this.hand.toString(tab+2))));
		result.append(String.format("%s}", "\t".repeat(tab)));

		return result.toString();
	}
	
	/**
	 * Crea una representación JSON del objeto.
	 * @return JSON del objeto.
	 */	
	public String toJSON() {
		StringBuilder result = new StringBuilder("");
		
		result.append("{\n");
		result.append(String.format("\t\"id\":%s,\n", this.id));
		result.append(String.format("%s\"UNO\":%s,\n", this.isUNO()));
		result.append(String.format("\t\"name\":\"%s\",\n", this.name));
		result.append(String.format("\t\"hand\":%s", (this.hand.getCards().isEmpty())?"null\n":String.format("\n   %s\n", this.hand.toString(2))));
		result.append("}");

		return result.toString();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the hand
	 */
	public Hand getHand() {
		return hand;
	}
	/**
	 * @param hand the hand to set
	 */
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	/**
	 * @return the uNO
	 */
	public boolean isUNO() {
		return UNO;
	}
	/**
	 * @param uNO the uNO to set
	 */
	public void setUNO(boolean uNO) {
		UNO = uNO;
	}
}
