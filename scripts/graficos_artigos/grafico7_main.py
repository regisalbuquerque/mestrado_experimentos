import matplotlib.pyplot as plt
import pandas as pd
import dados as dados

bases = ['Line', 'Sine1', 'Gauss', 'Circle']

metodos = ['V12_HOM_LeverageBagging_ADWINChangeDetector', 'V13_HOM_LeverageBagging_ADWINChangeDetector']

titulos = {
           'V12_HOM_LeverageBagging_ADWINChangeDetector':'Fixed_Initialization', 
           'V13_HOM_LeverageBagging_ADWINChangeDetector': 'Random_Initialization'
           }

metodo1 = 'V12_HOM_LeverageBagging_ADWINChangeDetector' #'Fixed_Initialization'
metodo2 = 'V13_HOM_LeverageBagging_ADWINChangeDetector' #'Random_Initialization'
base = 'Line'
 

def localiza_diversidades_top(lambda_x, path_file, base, inicio, termino):
    DIVERSIDADES = []
    for it in range(inicio, termino+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        LINHA = RESULTADO.loc[RESULTADO['cod'] == lambda_x]
        DIVERSIDADE = LINHA['diversidade'].values[0]
        DIVERSIDADES.append(DIVERSIDADE)
    return DIVERSIDADES


def localiza_drifts_metodo(path_file):
    drifts = []
    RESULTADO = pd.read_csv(path_file + '.csv')
    DRIFTS = RESULTADO.loc[RESULTADO['drift']==1]
    for index, row in DRIFTS.iterrows():
        drifts.append(row['iteracao'])
    return drifts


def consulta_top1(frequencias, lambdas):
    top_freq = max(frequencias)    
    top_index = frequencias.index(top_freq) 
    top_lambda = lambdas[top_index] 
    return top_index, top_lambda


def calcula_pontos_escolhidos(lambda_x, diversidades, escolhas_lambdas, inicio, termino):
    pontos_escolhidos = []
    diversidade_escolhido = []
    i = 0
    for it in range(inicio, termino):
        if escolhas_lambdas[it] == lambda_x:
            pontos_escolhidos.append(it)
            diversidade_escolhido.append(diversidades[i])
        i = i + 1
    return pontos_escolhidos, diversidade_escolhido

def subplot_grafico7(metodo, base, titulo, inicio, termino):
    
    # DIVERSIDADES DO TOP1
    
    PATH_FILE = dados.ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';
    
    PATH_DRIFT = dados.ROOT_PATH_DRIFT + metodo + '_' + base + '_pareto__exec_1_drift';
    
    X = range(inicio, termino+1)
    
    frequencias, lambdas, escolhas_lambdas = dados.calcula_frequencias(PATH_FILE, base)
    top_index, top_lambda = consulta_top1(frequencias, lambdas)
    Y_TOP1 = localiza_diversidades_top(top_lambda, PATH_FILE, base, inicio, termino);
    
    plt.plot(X, Y_TOP1, '-', label='FIRST', color='k', markersize=10)
    
    X_TOP1_ESCOLHAS, Y_TOP1_ESCOLHAS = calcula_pontos_escolhidos(top_lambda, Y_TOP1, escolhas_lambdas, inicio, termino)
    plt.plot(X_TOP1_ESCOLHAS, Y_TOP1_ESCOLHAS, '3', label='FIRST(CHOOSEN)', color='k', markersize=10)
    
    
    # DRIFTS 
    if dados.baseEhReal[base] == False:
        for xc in dados.drifts[base]:
            if xc in X:
                plt.axvline(x=xc)
            
    resets = localiza_drifts_metodo(PATH_DRIFT)
    for xc in resets:
        if xc in X:
            plt.axvline(x=xc, color='r')
    
    
    axes = plt.gca()
    #axes.set_xlim([-0.01, 0.52])
    axes.set_ylim(dados.range_div[base])
    
    plt.xlabel('Iteration')
    plt.ylabel('Diversity')
    plt.title(titulo)
    plt.legend()
    plt.subplots_adjust(hspace=0.6)
    
    
    
for metodo in metodos:
    for base in bases:
        print(metodo + ' - ' + base)
    
        fig, ax = plt.subplots()
        
        partes = dados.get_parts(500, base)
        
        c = len(partes)
        
        i = 1
        for parte in partes:
            plt.subplot(c,1,i)
            subplot_grafico7(metodo, base, titulos[metodo], parte[0], parte[1])
            i = i + 1
        
        fig.set_figheight(8)
        fig.set_figwidth(15)
        fig.savefig(dados.ROOT_PATH_IMG + 'grafico7_' + '_' + titulos[metodo] + '_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')




        

