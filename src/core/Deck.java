package core;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Baraja para una partida de UNO.
 * Maneja objetos de tipo Card.
 * @author Leonardo
 */
public class Deck {
    
    /**Atributos.*/
 
    protected ArrayList<Card> cards = new ArrayList<>();  

    
    /**
     * Constructor de la clase.
     * 
     * Añade todas las cartas necesarias para el juego en un ArrayList de Cards.
     */
    public Deck(){
    	/**
    	 * Por cada color (excepto el negro) de EColor una carta con valor cero, dos del uno al nueve y dos comodines
    	 * de color (Draw two card, Reverse card, Skip card).
    	 */
    	for (EColor color: EnumSet.range(EColor.BLUE, EColor.YELLOW)) {
    		this.getCards().add(new Card(EValue.CERO,color));/**Agrega una carta con el número cero a la baraja.*/
    		
    		for (int i = 0; i < 2; i++) {/**Dos de cada número.*/
				for (EValue number: EnumSet.range(EValue.ONE, EValue.SKIP)) {
					this.getCards().add(new Card(number,color));/**Agrega la carta a la baraja.*/
				}
			}
        }
    	
    	for (int i = 0; i < 4; i++) {/**Cuatro cartas de especiales (de color negro).*/
    		this.getCards().add(new Card(EValue.WILD,EColor.BLACK));/**Agrega cuatro cartas "cambiar color" a la baraja.*/
    		this.getCards().add(new Card(EValue.DFOUR,EColor.BLACK));/**Agrega cuatro cartas "toma cuatro" a la baraja.*/
			
		}
    }
    
    /**
     * Instancia el Deck con las cartas especificadas.
     * 
     * @param deckCards Cartas requeridas en el deck.
     * Se debe validar con la expreción regula contenida en ERegex.DECK.
     */
    public Deck(String deckCards) {
    	this.stringToCards(deckCards);
    }
    
    protected void stringToCards(String stringCards) {
    	if (!stringCards.matches(String.format("^%s$", ERegex.DECK))) { /**Si no está en el formato correcto.*/
    		throw new IllegalArgumentException("Formato para Deck no es el adecuado.");
    	}
    	
    	this.cards.clear();
    	String [] array = stringCards.split(",");
    	for (String card : array) {
			this.cards.add(new Card(card.strip()));
		}
    }
    
    /**Métodos*/
    
    /**
     * 
     * @return La primera carta de la baraja.
     */
    public Card giveCard() {
    	
    	if (this.getCards().isEmpty()) {
    		throw new IllegalArgumentException("No hay cartas en la baraja.");
    	}
    	Card result = this.getCards().get(0); /**La primera carta de la baraja.*/
    	this.getCards().remove(0); /**Elimina la primera carta.*/
    	return result;
    	
    }
    
    /**
     * Busca una carta que se encuentre en el índice especificado.
     * @param cardIndex Indice de la carta a buscar.
     * @return Carta buscada.
     */
    public Card giveCard(int cardIndex) {
    	if (this.getCards().isEmpty()) {/**Si la baraja está vacía lanzará una excepción.*/
    		throw new IllegalArgumentException("No hay cartas en la baraja.");
    	}
    	/**Si el parametro es menor que cero o mayor o igual que la cantidad de cartas lanzará una excepción.*/
    	if (cardIndex < 0 || cardIndex >= this.getCards().size()) { 
    		throw new IllegalArgumentException("Parámetro fuera de rango.");
    	}
    	Card result = this.getCards().get(cardIndex);
    	this.getCards().remove(cardIndex);
    	return result;    	
    }
    
    /**
     * 
     * @param card Carta que se requiere.
     * @return La carta dentro de cards.
     */
    public Card giveCard(Card card){
    	/**Resultado de la busqueda de la carta en la baraja. (false si no se encuntra la carta en la baraja.*/
    	int searchResult = this.searchCard(card.getValue(), card.getColor());
    	
        if (searchResult == -1) { /**Sí la carta no está lanzará una excepción.*/
            throw new IllegalArgumentException("La carta no se encuentra en la baraja.");
        }
        
        Card result = this.getCards().get(searchResult);/**Guarda la carta requerida.*/
        this.getCards().remove(searchResult);/**Remueve la carta de la baraja.*/
        
        return result;
    	
    }
    
	/**
	 * Da una carta en especifica que se encuentra en la baraja.
	 * @param value Número o símbolo de la carta.
	 * @param color Color de la carta.
	 * @return Un objeto de tipo Card.
	 */
    public Card giveCard(EValue value, EColor color){
    	/**Resultado de la busqueda de la carta en la baraja. (false si no se encuntra la carta en la baraja.*/
    	int searchResult = this.searchCard(value, color);
    	
        if (searchResult == -1) { /**Sí la carta no está lanzará una excepción.*/
            throw new IllegalArgumentException("La carta no se encuentra en la baraja");
        }
        
        Card result = this.getCards().get(searchResult);/**Guarda la carta requerida.*/
        this.getCards().remove(searchResult);/**Remueve la carta de la baraja.*/
        
        return result;
    }
    /**
     * Ordena la baraja aleatoriamente.
     * @return Un objeto Deck con las cartas desordenadas.
     */
    public Deck shuffle() {
    	ArrayList<Card> newCards = new ArrayList<>();

    	while (!this.getCards().isEmpty()) {/**Mientras la baraja no esté vacía.*/
    		/**Seleciona una carta aleatoria de la baraja y la añade a una nueva.*/
    		newCards.add(this.giveCard(RandomGenerator.randomInt(0, this.getCards().size()-1)));
    	}
    	this.cards = newCards;/**Se remplaza el arreglo de cartas por el nuevo ordenado aleatoriamente.*/ 
    	
    	Deck result = new Deck();
    	result.cards = this.cards;
    	return result;
    	
    }
    
    /**
     * Devuelve el índice de una carta especifica que se encuentra en la baraja.
     * @param value Número o símbolo de la carta a buscar.
     * @param color Color de la carta a buscar.
     * @return El inidce de la carta, -1 si no se encuentra la carta.
     */
    public int searchCard(EValue value, EColor color) {
    	int result = -1;
    	
    	for (Card card : this.getCards()) {
			if (card.equals(new Card(value,color))) {
				result = this.getCards().indexOf(card); /**Encuentra la carta en la baraja.*/
				break;
			}
		}
    	
    	return result;
    }
    
    public int searchCard(Card card) {
    	return this.searchCard(card.getValue(),card.getColor());
    }
    
    
    /**
     * @return Una representación String de un arreglo de cartas.
     */
    @Override
    public String toString(){
    	if (this.cards.isEmpty()) {
    		return "[]";
    	}
    	int size = this.cards.size();
    	StringBuilder result = new StringBuilder();
    	result.append("[\n");
    	if (this.cards.size() == 1) {
    		result.append(String.format("%s%s\n", "\t".repeat(1),this.cards.get(0)));
    	}else {
    		for (Card card : getCards().subList(0, this.cards.size()-1)) {
    			result.append(String.format("%s%s,\n", "\t".repeat(1),card));
    		}  		
    		result.append(String.format("%s%s\n", "\t".repeat(1),this.cards.get(size-1)));
    	}
    	result.append("]");  
    	
    	return result.toString();
    }
    /**
     * 
     * @param tab Cantidad de tabs que estará corrido.
     * @return Una representación String de un arreglo de cartas
     */
    public String toString(int tab){
    	if (this.cards.isEmpty()) {
    		return String.format("%s[]", "\t".repeat(tab));
    	}
    	int size = this.cards.size();
    	StringBuilder result = new StringBuilder();
    	result.append(String.format("%s[\n", "\t".repeat(tab)));
    	if (this.cards.size() == 1) {
    		result.append(String.format("%s%s\n", "\t".repeat(tab+1),this.cards.get(0)));
    	}else {
    		for (Card card : getCards().subList(0, this.cards.size()-1)) {
    			result.append(String.format("%s%s,\n", "\t".repeat(tab+1),card));
    		}  		
    		result.append(String.format("%s%s\n", "\t".repeat(tab+1),this.cards.get(size-1)));
    	}
    	result.append(String.format("%s]", "\t".repeat(tab)));  
    	
    	return result.toString();
    }
      
    
    /**
	 * @return the cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

}
