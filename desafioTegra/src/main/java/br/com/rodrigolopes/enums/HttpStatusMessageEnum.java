package br.com.rodrigolopes.enums;

public enum HttpStatusMessageEnum {
	OK("OK"),
	UNAUTHORIZED("Unauthorized"),
	UNPROCESSABLE_ENTITY("Unprocessable Entity"),
	BAD_REQUEST("Bad Request"),
	NOT_FOUND("Not Found"),
	INTERNAL_SERVER_ERROR("Internal server error!");
	
	String text;
	
	HttpStatusMessageEnum (String text) {
		this.text = text;
	}
    
    public String getText() {
        return text;
    }
}



