package core;

/**
 * Enumeración regex para validar distintos objetos.
 * @author leonardo
 *
 */

public enum ERegex {
	
	SPECIALCARD("(WILD|DFOUR)_(BLACK)"),/**Carta especial de UNO (Wild o Draw four)*/
	ACTIONCARD("(DTWO|REVERSE|SKIP)_(BLUE|RED|GREEN|YELLOW)"), /**Carta de acción de UNO (Draw two, Reverse o Skip)*/
	NUMBERCARD("(CERO|ONE|TWO|THREE|FOUR|FIVE|SIX|SEVEN|EIGHT|NINE)_(BLUE|RED|GREEN|YELLOW)"), /***/
	CARD(String.format("(%s|%s|%s)",NUMBERCARD,ACTIONCARD,SPECIALCARD)),
    DECK(String.format("((%s,\\s?)*%s)", CARD,CARD)),
    DATE("(\\d){2}/\\d{2}/\\d{4}"),
	SCPLAYER("\\{\"rank\":\\d+,\"name\":\"[\\w\\W]+\",\"points\":\\d+,\"lastResult\":\\d+,\"date\":\"\\d\\d-\\d\\d-\\d\\d\\d\\d\"\\}");
	
    private final String value;
    
    ERegex(String value){
        this.value = value;
    }
    
    @Override
    public String toString() {
    	return getValue();
    }

	/**
	 * @return the value
	 */
	private String getValue() {
		return value;
	}
}