



# Gráfico da Acurácia_Prequencial x Diversidade.



# Analise de Pareto para algumas iterações.
# Destacar o ensemble escolhido no gráfico.

import matplotlib.pyplot as plt
import pandas as pd

#Path
ROOT_PATH = '/Users/regisalbuquerque/Documents/git/regis/mestrado_resultados/comparacao3/pareto/'
ROOT_PATH_IMG = '/Users/regisalbuquerque/Desktop/'

# CLASSIFICADOR
metodo = 'V12'

# PASTA_CONJUNTOS
conjunto_homogeneo = metodo + '_HOM/'
conjunto_heterogeneo = metodo + '_HET/'

# BASE
base = 'Line'
inicio = 1
termino = 2000

# PONTOS
TAM_PONTO = 16;
TAM_PONTO_ATUAL = 16;



def pontos(inicio, termino, tamanho, cor, path):
    X_PTS_BEFORE = []
    Y_PTS_BEFORE = []
    X_PTS_BEFORE_PARETO = []
    Y_PTS_BEFORE_PARETO = []
    for i in range(inicio, termino):
        dataset = pd.read_csv(path + str(i) + '.csv')

        dataset1 = dataset.loc[dataset['pareto_member'] == False]
        X_PTS_BEFORE.extend(dataset1['diversidade'].values)
        Y_PTS_BEFORE.extend(dataset1['acc'].values)

        dataset2 = dataset.loc[dataset['pareto_member'] == True]
        X_PTS_BEFORE_PARETO.extend(dataset2['diversidade'].values)
        Y_PTS_BEFORE_PARETO.extend(dataset2['acc'].values)

    plt.scatter(X_PTS_BEFORE, Y_PTS_BEFORE, alpha=0.5, s=tamanho, color=cor)
    plt.scatter(X_PTS_BEFORE_PARETO, Y_PTS_BEFORE_PARETO, alpha=0.5, s=tamanho, color=cor)


def pontos_atuais(tamanho, cor, cor_pareto, path_file):
    dataset = pd.read_csv(path_file)

    dataset1 = dataset.loc[dataset['pareto_member'] == False]
    X = dataset1['diversidade'].values
    Y = dataset1['acc'].values

    dataset2 = dataset.loc[dataset['pareto_member'] == True]
    X_pareto = dataset2['diversidade'].values
    Y_pareto = dataset2['acc'].values

    plt.scatter(X, Y, s=tamanho, label='Comitê', marker='^', color=cor)
    plt.scatter(X_pareto, Y_pareto, s=tamanho, label='Comitê(Pareto)', marker='s', color=cor_pareto)


def subplot(iteracao, conjunto):
    file_csv = ROOT_PATH + conjunto + base + '/' + base + '_pareto__exec_0_it_' + str(iteracao) + '.csv'


    # TODOS OS PONTOS - ANTERIORES
    # pontos(inicio, iteracao, TAM_PONTO, '0.75', path_csv)
    # TODOS OS PONTOS - POSTERIORES
    # pontos(iteracao + 1, termino + 1, TAM_PONTO, '0.75', path_csv)

    # APENAS OS PONTOS ATUAIS
    pontos_atuais(TAM_PONTO_ATUAL, 'k', 'b', file_csv)

    axes = plt.gca()
    axes.set_xlim([-0.01, 0.52])
    axes.set_ylim([0, 1])

    plt.xlabel('Diversity')
    plt.ylabel('Prequential Accuracy')
    plt.title('Iteration ' + str(iteracao))
    plt.legend()


plt.subplot(241)
subplot(50, conjunto_heterogeneo)
plt.subplot(242)
subplot(950, conjunto_heterogeneo)
plt.subplot(243)
subplot(1050, conjunto_heterogeneo)
plt.subplot(244)
subplot(termino, conjunto_heterogeneo)

plt.subplot(245)
subplot(50, conjunto_homogeneo)
plt.subplot(246)
subplot(950, conjunto_homogeneo)
plt.subplot(247)
subplot(1050, conjunto_homogeneo)
plt.subplot(248)
subplot(termino, conjunto_homogeneo)

# fig = plt.figure()
plt.show()

# fig.savefig(path_img + str(iteracao_atual) + '.png', bbox_inches='tight')
# plt.savefig(ROOT_PATH_IMG + 'pareto_grafico1_' + metodo + '_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')