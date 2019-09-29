package com.leandrorezende.cursomc.domain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		Optional<EstadoPagamento> estadoFound = Arrays.asList(EstadoPagamento.values()).stream()
				.filter(t -> cod.equals(t.getCod())).findFirst();
		return estadoFound.orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));
	}
}
