package experimentos.metodos.comparacao;

import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.metodos.MetodoV12Reacao;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.MetodoFactory;
import experimental.analise.ResultadoClassificador;
import experimental.analise.RelatResumo;
import experimental.analise.RelatResumo30;
import experimental.metodos.DDMConfig1;
import experimental.testes.TestePrequential;
import java.util.ArrayList;
import java.util.List;

public class TestarClassificadorBase {

	private final String path;
	private final Base base;
	private final List<BaseDrifts> baseDrifts;


	public TestarClassificadorBase(String path, Base base, List<BaseDrifts> baseDrifts) {
		this.path = path;
		this.base = base;
		this.baseDrifts = baseDrifts;
	}

	public void executa() {
		int seed = 1;
			System.out.println(" * * * * * S E E D * * * * * --> " + seed);
			
			List<ResultadoClassificador> listaResultados = new ArrayList<>();

			MetodoFactory[] classificadores = new MetodoFactory[3];

			// Método v12
			classificadores[0] = new MetodoV12Reacao("RetreinaTodosComBufferWarning", "Ambiguidade", seed).getMetodo();

			// DDM
			classificadores[1] = new DDMConfig1(seed).getMetodo();

			// LeveragingBAG Original
			classificadores[2] = new LeveragingBagVersaoOriginal(seed).getMetodo();

			for (int i = 0; i < classificadores.length; i++) {
				System.out.println();
				System.out.println("************ TESTE DE REAÇÃO: " + (i + 1) + "de" + classificadores.length);
				TestePrequential testePrequential = new TestePrequential(this.base, this.baseDrifts,
						classificadores[i]);
				ResultadoClassificador resultado = testePrequential.test();

				resultado.setCodigo(classificadores[i].getCodigo());
				listaResultados.add(resultado);
			}

			RelatResumo.gravar(listaResultados, path, this.base.getNome() + "_comp_" + seed + "__", true);
			
			RelatResumo30.gravar(listaResultados, path, this.base.getNome() + "_comp30_" + seed + "__");
		
	}

}
