package experimentos.metodos.comparacao;

import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
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

	public Resultado executa(MetodoFactory classificador) {
		
		TestePrequential testePrequential = new TestePrequential(this.base, this.baseDrifts,
				classificador);
		Resultado resultado = testePrequential.test();

		resultado.setCodigo(classificador.getCodigo());
		
		return resultado;

	}

}
