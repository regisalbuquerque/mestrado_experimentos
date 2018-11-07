package experimentos.metodos.comparacao;

import java.util.List;

import experimental.analise.ResultadoClassificador;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.MetodoFactory;
import experimental.testes.TestePrequential;

public class TestarClassificadorBase {

	private final Base base;
	private final List<BaseDrifts> baseDrifts;


	public TestarClassificadorBase(Base base, List<BaseDrifts> baseDrifts) {
		this.base = base;
		this.baseDrifts = baseDrifts;
	}

	public ResultadoClassificador executa(MetodoFactory classificador) {
		
		TestePrequential testePrequential = new TestePrequential(this.base, this.baseDrifts,
				classificador);
		ResultadoClassificador resultado = testePrequential.test();

		resultado.setCodigo(classificador.getCodigo());
		
		return resultado;

	}

}
