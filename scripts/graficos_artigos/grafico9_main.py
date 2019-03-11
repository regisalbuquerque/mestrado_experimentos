import matplotlib.pyplot as plt
import pandas as pd
import dados as dados

bases = ['Line', 'Sine1', 'Gauss', 'Circle']
#bases = ['Line']

metodos = ['V12_HOM_LeverageBagging_ADWINChangeDetector', 'V13_HOM_LeverageBagging_ADWINChangeDetector']

titulos = {
           'V12_HOM_LeverageBagging_ADWINChangeDetector':'Fixed_Initialization', 
           'V13_HOM_LeverageBagging_ADWINChangeDetector': 'Random_Initialization'
           }

metodo1 = 'V12_HOM_LeverageBagging_ADWINChangeDetector' #'Fixed_Initialization'
metodo2 = 'V13_HOM_LeverageBagging_ADWINChangeDetector' #'Random_Initialization'
base = 'Line'
 

def calcula_frequencias(path_file, base):
    #Cria os contadores
    PRIMEIRA_IT = pd.read_csv(path_file + '1.csv')
    PRIMEIRA_IT_ORD = PRIMEIRA_IT.sort_values('cod')
    frequencias = [0]*len(PRIMEIRA_IT.index.values) #INICIALIZA OS CONTADORES DE FREQUENCIAS
    lambdas_df = PRIMEIRA_IT_ORD['cod']
    lambdas = []
    for it in lambdas_df:
        lambdas.append(it)
        
    escolhas_lambdas = []
        
    # C O N T A G E M
    for it in range(1, dados.limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        linha = RESULTADO.loc[RESULTADO['pareto_maior'] == True]
        lambda_escolhido = linha['cod'].values[0]
        VENCEDOR = lambdas.index(lambda_escolhido)
        frequencias[VENCEDOR] = frequencias[VENCEDOR] + 1
        escolhas_lambdas.append(lambda_escolhido)
    
    lambdas_ordem_frequencia = []
    frequencias_aux = frequencias.copy()
    for it in range(0, len(frequencias_aux)):
        top_freq = max(frequencias_aux) 
        top_index = frequencias_aux.index(top_freq) 
        top_lambda = lambdas[top_index] 
        lambdas_ordem_frequencia.append(top_lambda)
        frequencias_aux[top_index] = -1
    
    return frequencias, lambdas, escolhas_lambdas, lambdas_ordem_frequencia

def localiza_drifts_metodo(path_file):
    drifts = []
    RESULTADO = pd.read_csv(path_file + '.csv')
    DRIFTS = RESULTADO.loc[RESULTADO['drift']==1]
    for index, row in DRIFTS.iterrows():
        drifts.append(row['iteracao'])
    return drifts


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


def subplot_grafico9(metodo, base, titulo):
    
    PATH_FILE = dados.ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';
    PATH_DRIFT = dados.ROOT_PATH_DRIFT + metodo + '_' + base + '_pareto__exec_1_drift';


    frequencias, lambdas, escolhas_lambdas, lambdas_ordem = calcula_frequencias(PATH_FILE, base)
    
    X = range(1, dados.limiteBase[base]+1)
    Y = []
    
    for it in range (0, dados.limiteBase[base]):
        if escolhas_lambdas[it] == lambdas_ordem[0]:
            Y.append(1)
        elif escolhas_lambdas[it] == lambdas_ordem[1]:
            Y.append(2)
        elif escolhas_lambdas[it] == lambdas_ordem[2]:
            Y.append(3)
        else:
            Y.append(0)
    
    plt.plot(X, Y, '-', label='', color='k', markersize=10)
    
     # DRIFTS 
    if dados.baseEhReal[base] == False:
        for xc in dados.drifts[base]:
            plt.axvline(x=xc)
    
    resets = localiza_drifts_metodo(PATH_DRIFT)
    for xc in resets:
        if xc in X:
            plt.axvline(x=xc, color='r')
    
    
    #axes = plt.gca()
    #axes.set_xlim([-0.01, 0.52])
    plt.yticks([0,1,2,3], ['Others', '1st', '2nd', '3rd'])
    
    plt.xlabel('Iteration')     
    plt.ylabel('Ensemble')
    plt.title(titulo)
    #plt.legend()
    plt.subplots_adjust(hspace=0.6)

def executa_grafico():
    for metodo in metodos:
        for base in bases:
            print(metodo + ' - ' + base)
        
            fig, ax = plt.subplots() #Para o primeiro gráfico
            plt.subplot(4,1,1)
            subplot_grafico9(metodo, base, titulos[metodo] + ' - ' + base)
            fig.set_figheight(8)
            fig.set_figwidth(15)
            fig.savefig(dados.ROOT_PATH_IMG + 'grafico9_'  + '_' + titulos[metodo] + '_' + base + '_' + '.eps', format='eps', dpi=1200, bbox_inches='tight')


executa_grafico()

#PATH_FILE = dados.ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';
#frequencias, lambdas, escolhas_lambdas, lambdas_ordem = calcula_frequencias(PATH_FILE, base)



        

