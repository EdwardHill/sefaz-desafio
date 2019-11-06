package br.com.sefaz.desafio.models;

public enum Tipo {
	CELULAR("Celular"),
	FIXO("Fixo");
	
	private final String tipo;

	private Tipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
}
