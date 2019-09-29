package com.leandrorezende.cursomc.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TipoCliente {

	PESSSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		Optional<TipoCliente> tipoFound = Arrays.asList(TipoCliente.values()).stream()
				.filter(t -> cod.equals(t.getCod())).findFirst();
		return tipoFound.orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));
	}
}
