package core;


/**
 *Esta clase administra las cartas del jugador.
 * @author Leonardo
 */
public class Hand extends Deck{
    
    private Deck deck = new Deck(); /**Baraja de la que tomará cartas.*/
    private DiscardPile discardPile = new DiscardPile(this.deck); /**Donde soltará las cartas.*/
    
    
    /**Constructor vacío.*/
    public Hand() {
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    }
    
    /**
     * Constructor de la clase.
     * @param deck Baraja de donde se tomará cartas.
     */
    public Hand(Deck deck) {
    	this.discardPile = new DiscardPile(this.deck);
    	this.cards.clear();/**Sin cartas en la mano al iniciar.*/
    	this.setDeck(deck);
    }
    
    /**
     * Instancia la Hand con las cartas especificadas.
     * 
     * @param card Cartas requeridas en la Hand.
     * Se debe validar con la expreción regula contenida en ERegex.DECK.
     */
    public Hand(String cards) {
    	super(cards);
    }
    
    /**Métodos.*/
    
    /**
     * Toma la primera carta de la baraja.
     * 
     */
    public void takeCard(){
        this.cards.add(this.getDeck().giveCard());
    }
    
    /**
     * Suelta una carta en la pila de descarte.
     * @param card Carta que soltará.
     */
    public void dropCard(Card card) {
    	try {
    		this.discardPile.receiveCard(this, card);
    	} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("La carta %s no se encuentra en la mano.", card));
		}
    }
    
    /**
     * Verifica si tiene una carta del color especificado.
     * @param color Color de la carta buscada.
     * @return false si no encuentra la carta.
     */
    public boolean checkColor(EColor color) {
    	
    	for (Card card : cards) {
			if (card.getColor().equals(color)) {
				return true;
			}
		}
    	
    	return false;
    }
    
    /**
     * Verifica si tiene una carta con el número o símbolo especificado.
     * @param value Número o símbolo de la carta buscada.
     * @return false si no encuentra la carta.
     */
    public boolean checkValue(EValue value) {
    	
    	for (Card card : cards) {
			if (card.getValue().equals(value)) {
				return true;
			}
		}
    	
    	return false;
    }
    

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	/**
	 * @return the discardPile
	 */
	public DiscardPile getDiscardPile() {
		return discardPile;
	}

	/**
	 * @param discardPile the discardPile to set
	 */
	public void setDiscardPile(DiscardPile discardPile) {
		this.discardPile = discardPile;
	}

}
