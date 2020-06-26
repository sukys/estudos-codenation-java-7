package challenge;

import java.util.ArrayDeque;

public class Estacionamento {

	private ArrayDeque<Carro> estacionamento = new ArrayDeque<>(10);

	public void estacionar(Carro carro) {
		validar(carro);
		if (estacionamento.size() >= Constantes.QUANTIDADE_VAGAS) {
			validarTodosSenior();
		}

		if (estacionamento.size() < Constantes.QUANTIDADE_VAGAS) {
			estacionamento.add(carro);
		} else {
			throw new EstacionamentoException("Não há vagas.");
		}
	}

	public int carrosEstacionados() {
		return estacionamento.size();
	}

	public boolean carroEstacionado(Carro carro) {
		return estacionamento.contains(carro);
	}

	/**
	 * Verifica se todos os motoristas estacionados são seniors.
	 */
	private void validarTodosSenior() {
		Carro carroParaSair = null;
		for (Carro carro : estacionamento) {
			if (carro.getMotorista().getIdade() < Constantes.IDADE_PERMANENCIA_PERMITIDA) {
				carroParaSair = carro;
				break;
			}
		}
		if (carroParaSair != null) {
			estacionamento.remove(carroParaSair);
		}
	}

	/**
	 * Validações necessárias.
	 * 
	 * @param carro
	 */
	private void validar(Carro carro) {
		if (carro == null) {
			throw new NullPointerException("Sem carro.");
		}

		if (carro.getMotorista() == null) {
			throw new EstacionamentoException("Carro deve possuir motorista.");
		}

		if (carro.getMotorista().getIdade() < Constantes.IDADE_MINIMA) {
			throw new EstacionamentoException("Condutor abaixo da idade.");
		}

		if (carro.getMotorista().getPontos() > Constantes.MAXIMO_PONTOS_CNH) {
			throw new EstacionamentoException("Condutor com pontos excedidos.");
		}

	}

}
