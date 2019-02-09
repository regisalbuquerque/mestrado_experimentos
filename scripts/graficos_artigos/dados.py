
import pandas as pd
import matplotlib.pyplot as plt

def get_base():
    return 'Line';

def get_metodo():
    return 'V12';

# PASTA_CONJUNTOS
conjunto_homogeneo = '_HOM/'
conjunto_heterogeneo = '_HET/'

#Path
ROOT_PATH = '/Users/regisalbuquerque/Documents/git/regis/mestrado_resultados/comparacao3/pareto/'
ROOT_PATH2 = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/Teste_v12_v13/pareto/'

#PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/Teste_v12_v13/pareto/V12_HOM_LeverageBagging_ADWINChangeDetector/Line/V12_HOM_LeverageBagging_ADWINChangeDetector_Line_pareto__exec_1_it_1.csv'

ROOT_PATH_LB = ROOT_PATH + 'LB_Original'

ROOT_PATH_IMG = '/Users/regisalbuquerque/Desktop/'




drifts = {
           'Line':[1000], 
           'Sine1': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
           'Gauss': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
           'Circle': [1000,2000,3000],
           'Elec': [],
           'Spam': [],
           'KDDCup99': []
           }

range_div = {
        'Line':[-0.1,1], 
        'Sine1': [-0.1,1], 
        'Gauss': [-0.1,1], 
        'Circle': [-0.1,1],
        'Elec': [-0.1,1],
        'Spam': [-0.1,1],
        'KDDCup99': [-0.1,1]
        }

ranges_ensemble = {
        'Line':[0,11], 
        'Sine1': [0,11], 
        'Gauss': [0,11], 
        'Circle': [0,11],
        'Elec': [0,11],
        'Spam': [0,11],
        'KDDCup99': [0,11]
        }

ranges_acc = {
        'Line':[-0.1, 1.19], 
        'Sine1': [-0.1, 1.19], 
        'Gauss': [-0.1, 1.19], 
        'Circle': [-0.1, 1.19],
        'Elec': [-0.1, 1.19],
        'Spam': [-0.1, 1.19],
        'KDDCup99': [-0.1, 1.19]
        }

baseEhReal = {
        'Line':False, 
        'Sine1': False, 
        'Gauss': False, 
        'Circle': False,
        'Elec': True,
        'Spam': True,
        'KDDCup99': True
        }

limiteBase = {
        'Line': 2000, 
        'Sine1': 10000, 
        'Gauss': 10000, 
        'Circle': 4000,
        'Elec': -1,
        'Spam': -1,
        'KDDCup99': -1
        }


stepBase = {
        'Line': 1, 
        'Sine1': 20, 
        'Gauss': 20, 
        'Circle': 15,
        'Elec': 250,
        'Spam': 250,
        'KDDCup99': 250
        }

# PONTOS
TAM_PONTO = 16;
TAM_PONTO_ATUAL = 16;
TAM_PONTO_2 = 5;

def termino(base):
    return limiteBase[base]


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


    plt.scatter(X, Y, s=tamanho, marker='^', color=cor)
    plt.scatter(X_pareto, Y_pareto, s=tamanho, marker='s', color=cor_pareto)
    
    VENCEDOR = dataset.loc[dataset['pareto_maior'] == True]
    lambda_vencedor = VENCEDOR['cod'].values[0]
    div_vencedor = VENCEDOR['diversidade'].values[0]
    acc_vencedor = VENCEDOR['acc'].values[0]

    texto = "λ=" + str("%.2f" % round(lambda_vencedor,2)) + " (" + str("%.2f" % round(div_vencedor,2)) + "," + str("%.2f" % round(acc_vencedor,2)) + ")"

    
    ax = plt.gca()
    
    plt.text(0.1, 0.9,texto,
     horizontalalignment='left',
     verticalalignment='center',
     transform = ax.transAxes)


def subplot_grafico1(iteracao, metodo, conjunto, base):
    file_csv = ROOT_PATH + metodo + conjunto + base + '/' + base + '_pareto__exec_0_it_' + str(iteracao) + '.csv'


    # TODOS OS PONTOS - ANTERIORES
    # pontos(inicio, iteracao, TAM_PONTO, '0.75', path_csv)
    # TODOS OS PONTOS - POSTERIORES
    # pontos(iteracao + 1, termino + 1, TAM_PONTO, '0.75', path_csv)

    # APENAS OS PONTOS ATUAIS
    pontos_atuais(TAM_PONTO_ATUAL, 'k', 'b', file_csv)

    axes = plt.gca()
    axes.set_xlim(range_div[base])
    axes.set_ylim(ranges_acc[base])

    plt.xlabel('Diversity')
    plt.ylabel('Prequential Accuracy')
    #plt.title('Iteration ' + str(iteracao))
    #plt.legend()
    plt.subplots_adjust(hspace=0.6, wspace = 0.4)

def get_slice(ARRAY, base):
    ARRAY_STEP = []
    ARRAY_STEP.append(ARRAY[0])
    for index in range(0, limiteBase[base], stepBase[base]):
        ARRAY_STEP.append(ARRAY[index])
    ARRAY_STEP.append(ARRAY[-1])
    return ARRAY_STEP

    

def subplot_grafico2(metodo, conjunto, base):
    PATH_FILE = ROOT_PATH + metodo + conjunto + base + '/' + base + '_pareto__exec_0_it_';
    X = range(1, limiteBase[base]+1)
    Y = localiza_vencedores(PATH_FILE, base);
    
    X_STEP = get_slice(X, base)
    Y_STEP = get_slice(Y, base)
    
    #plt.scatter(X_STEP, Y_STEP, alpha=0.5, s=TAM_PONTO_2, color='k')
    plt.plot(X_STEP, Y_STEP, '-', label='', color='k', markersize=10)
    
     # DRIFTS 
    if baseEhReal[base] == False:
        for xc in drifts[base]:
            plt.axvline(x=xc)
    
    axes = plt.gca()
    #axes.set_xlim([-0.01, 0.52])
    axes.set_ylim(ranges_ensemble[base])
    
    plt.xlabel('Iteration')
    plt.ylabel('Ensemble')
    #plt.title('Gráfico 2: ' + base + ' - ' + metodo + conjunto)
    #plt.legend()
    plt.subplots_adjust(hspace=0.6)
    
def subplot_grafico3(metodo, conjunto, base):
    PATH_FILE = ROOT_PATH + metodo + conjunto + base + '/' + base + '_pareto__exec_0_it_';
    FILE_PATH_LB = ROOT_PATH_LB +  '/' + base + '/' + base + '_pareto__exec_0';
    
    X = range(1, limiteBase[base]+1)
    Y = localiza_diversidades(PATH_FILE, base);
    
    Y_LB = localiza_diversidades_lb(FILE_PATH_LB, base);
    
    X_STEP = get_slice(X, base)
    Y_STEP = get_slice(Y, base)
    Y_LB_STEP = get_slice(Y_LB, base)
    
    #plt.scatter(X_STEP, Y_STEP, alpha=0.5, s=TAM_PONTO_2, color='k')
    plt.plot(X_STEP, Y_STEP, '-', label='DESDD', color='k', markersize=10)
    #plt.scatter(X_STEP, Y_LB_STEP, alpha=0.5, s=TAM_PONTO_2, color='g')
    plt.plot(X_STEP, Y_LB_STEP, ':', label='LB', color='r', markersize=10)
    
     # DRIFTS 
    if baseEhReal[base] == False:
        for xc in drifts[base]:
            plt.axvline(x=xc)
    
    axes = plt.gca()
    #axes.set_xlim([-0.01, 0.52])
    axes.set_ylim(range_div[base])
    
    plt.xlabel('Iteration')
    plt.ylabel('Diversity')
    #plt.title('Gráfico 3: ' + base + ' - ' + metodo + conjunto)
    plt.legend()
    plt.subplots_adjust(hspace=0.6)
    
def subplot_grafico4(metodo, conjunto, base):
    PATH_FILE = ROOT_PATH + metodo + conjunto + base + '/' + base + '_pareto__exec_0_it_';
    X = range(1, limiteBase[base]+1)
    Y = localiza_diversidades(PATH_FILE, base);
    Y_MENOR = localiza_diversidades_pareto_menor(PATH_FILE, base);
    
    X_STEP = get_slice(X, base)
    Y_STEP = get_slice(Y, base)
    Y_MENOR_STEP = get_slice(Y_MENOR, base)
    
    plt.plot(X_STEP, Y_STEP, '-', label='Pareto Highest Diversity Solution', color='k', markersize=10)
    plt.plot(X_STEP, Y_MENOR_STEP, ':', label='Pareto Lowest Diversity Solution', color='b', markersize=10)
    
     # DRIFTS 
    if baseEhReal[base] == False:
        for xc in drifts[base]:
            plt.axvline(x=xc)
    
    axes = plt.gca()
    #axes.set_xlim([-0.01, 0.52])
    axes.set_ylim(range_div[base])
    
    plt.xlabel('Iteration')
    plt.ylabel('Diversity')
    #plt.title('Gráfico 4: ' + base + ' - ' + metodo + conjunto)
    plt.legend()
    plt.subplots_adjust(hspace=0.6)
    
def subplot_grafico5(metodo, base, titulo):
    
    # HISTOGRAMA DOS ENSEMBLES

    PATH_FILE = ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';
    
    Y, X_Labels, escolhas = calcula_frequencias(PATH_FILE, base);
    
    
    X = range(1, len(Y)+1)
    
    plt.bar(X, Y, align='center', alpha=0.5)
    plt.xticks(X, X_Labels)
    
    for i in range(len(Y)):
        plt.text(x = X[i]-0.2 , y = Y[i]+0.1, s = Y[i], size = 15)

    
    plt.xlabel('Ensembles')
    plt.ylabel('Count')
    plt.title(titulo)
    #plt.legend()
    plt.subplots_adjust(hspace=0.6)
    
def subplot_grafico6(metodo, base, titulo):
    
    # DIVERSIDADES DOS TOP3
    
    PATH_FILE = ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';
    
    X = range(1, limiteBase[base]+1)
    
    frequencias, diversidades, escolhas = calcula_frequencias(PATH_FILE, base)
    
    
    INDEX_TOP1 = frequencias.index(max(frequencias))
    frequencias[INDEX_TOP1] = -1
    INDEX_TOP2 = frequencias.index(max(frequencias))
    frequencias[INDEX_TOP2] = -1
    INDEX_TOP3 = frequencias.index(max(frequencias))
    frequencias[INDEX_TOP3] = -1
    
    Y_TOP1 = localiza_diversidades_top(INDEX_TOP1, PATH_FILE, base);
    Y_TOP2 = localiza_diversidades_top(INDEX_TOP2, PATH_FILE, base);
    Y_TOP3 = localiza_diversidades_top(INDEX_TOP3, PATH_FILE, base);
    
    X_STEP = get_slice(X, base)
    Y_TOP1_STEP = get_slice(Y_TOP1, base)
    Y_TOP2_STEP = get_slice(Y_TOP2, base)
    Y_TOP3_STEP = get_slice(Y_TOP3, base)
    
    plt.plot(X_STEP, Y_TOP1_STEP, '-', label='FIRST', color='k', markersize=10)
    X_TOP1_ESCOLHAS, Y_TOP1_ESCOLHAS = calcula_pontos_escolhidos(INDEX_TOP1, Y_TOP1, escolhas)
    plt.plot(X_TOP1_ESCOLHAS, Y_TOP1_ESCOLHAS, 'x', label='FIRST(CHOOSEN)', color='k', markersize=20)
    
    
    #plt.plot(X_STEP, Y_TOP2_STEP, ':', label='SECOND', color='b', markersize=10)
    #plt.plot(X_STEP, Y_TOP3_STEP, '-', label='THIRD', color='r', markersize=10)
    
     # DRIFTS 
    if baseEhReal[base] == False:
        for xc in drifts[base]:
            plt.axvline(x=xc)
    
    axes = plt.gca()
    #axes.set_xlim([-0.01, 0.52])
    axes.set_ylim(range_div[base])
    
    plt.xlabel('Iteration')
    plt.ylabel('Diversity')
    plt.title(titulo)
    plt.legend()
    plt.subplots_adjust(hspace=0.6)
    


def localiza_diversidades_top(indice, path_file, base):
    DIVERSIDADES = []
    for it in range(1, limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        X = RESULTADO.loc[indice]
        DIVERSIDADE = X['diversidade']
        DIVERSIDADES.append(DIVERSIDADE)
    return DIVERSIDADES
    

def calcula_pontos_escolhidos(index, diversidade_index, escolhas):
    pontos_escolhidos = []
    diversidade_escolhido = []
    for it in range(1, len(escolhas)):
        if escolhas[it] == index:
            pontos_escolhidos.append(it)
            diversidade_escolhido.append(diversidade_index[it])
    return pontos_escolhidos, diversidade_escolhido

def calcula_frequencias(path_file, base):
    #Cria os contadores
    PRIMEIRA_IT = pd.read_csv(path_file + '1.csv')
    frequencias = [0]*len(PRIMEIRA_IT.index.values)
    frequencias_ord = []
    diversidades_ord = []
    escolhas = []
    
    for it in range(1, limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        X = RESULTADO.loc[RESULTADO['pareto_maior'] == True]
        VENCEDOR = X.index.values[0]
        frequencias[VENCEDOR] = frequencias[VENCEDOR] + 1
        escolhas.append(VENCEDOR)
      
    ORDENADO = PRIMEIRA_IT.sort_values('cod')    
    ordem = ORDENADO.index.values
    diversidades = ORDENADO['cod']
    for it in ordem:
        frequencias_ord.append(frequencias[it])
        diversidades_ord.append(round(diversidades[it], 6))
    return frequencias_ord, diversidades_ord, escolhas

def localiza_vencedores(path_file, base):
    VENCEDORES = []
    for it in range(1, limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        X = RESULTADO.loc[RESULTADO['pareto_maior'] == True]
        VENCEDOR = X.index.values[0]
        #print('\nVENCEDOR:' + str(VENCEDOR))
        VENCEDORES.append(VENCEDOR)
    return VENCEDORES

def localiza_diversidades(path_file, base):
    VENCEDORES = []
    for it in range(1, limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        X = RESULTADO.loc[RESULTADO['pareto_maior'] == True]
        VENCEDOR = X['diversidade'].values[0]
        #print('\nVENCEDOR:' + str(VENCEDOR))
        VENCEDORES.append(VENCEDOR)
    return VENCEDORES

def localiza_diversidades_lb(path_file, base):
     
    RESULTADO = pd.read_csv(path_file)
    DIVERSIDADES = RESULTADO['ambiguidade'].values
    return DIVERSIDADES

def localiza_diversidades_pareto_menor(path_file, base):
    VENCEDORES = []
    for it in range(1, limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        X = RESULTADO.loc[RESULTADO['pareto_menor'] == True]
        VENCEDOR = X['diversidade'].values[0]
        #print('\nVENCEDOR:' + str(VENCEDOR))
        VENCEDORES.append(VENCEDOR)
    return VENCEDORES



