
# Gráfico do Ensemble X Frequência. HISTOGRAMA 

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_v14_LB_DDM_DDD__Online_DDM_BufferAndReset__sinteticas/'
ROOT_PATH_ITERACOES = ROOT_PATH + 'pareto/'
ROOT_PATH_IMG = '/Users/regisalbuquerque/Desktop/'


basesr = [
        'PokerHand', 
        'ForestCovertype', 
        'Spam', 
        'KDDCup99'
        ]

basess = [
        'Sine1', 
        'Gauss', 
        'Circle', 
        'AgrawalAbrupt', 
        'AgrawalAbruptNoise',
        'AgrawalGradual',
        'AgrawalGradualNoise',
        'SEAAbrupt', 
        'SEAAbruptNoise',
        'SEAGradual',
        'SEAGradualNoise'
        ]

limiteBase = {
        'Sine1': 10000, 
        'Gauss': 10000, 
        'Circle': 4000, 
        'AgrawalAbrupt': 10000, 
        'AgrawalAbruptNoise': 10000,
        'AgrawalGradual': 10000,
        'AgrawalGradualNoise': 10000,
        'SEAAbrupt': 10000, 
        'SEAAbruptNoise': 10000,
        'SEAGradual': 10000,
        'SEAGradualNoise': 10000,
        
        'Spam': 9324,
        'KDDCup99': 489844,
        'PokerHand': 829200, 
        'ForestCovertype': 581012 
        }

drifts = {
        'Sine1': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'Gauss': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'Circle': [1000,2000,3000],
        'AgrawalAbrupt': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'AgrawalAbruptNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'AgrawalGradual': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'AgrawalGradualNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'SEAAbrupt': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'SEAAbruptNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'SEAGradual': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'SEAGradualNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        
        'Spam': [],
        'KDDCup99': [],
        'PokerHand': [], 
        'ForestCovertype': [] 
       }

bases = ['SEAGradual']


metodos = ['V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning']

nomes = {
           'V12_HOM_OnlineBagging_DDM':'v12_OnlineBagging_DDM_Reset', 
           'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning': 'v12_OnlineBagging_DDM_Buffer'
           }

titulos = {
           'V12_HOM_OnlineBagging_DDM':'V12 - OnlineBagging com Reset', 
           'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning': 'V12 - OnlineBagging com Buffer'
           }

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
    for it in range(1, limiteBase[base]+1):
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

def subplot_grafico5(metodo, base):
    
    # HISTOGRAMA DOS ENSEMBLES

    PATH_FILE = ROOT_PATH_ITERACOES + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';
    
    frequencias, lambdas, escolhas_lambdas, lambdas_ordem_frequencia = calcula_frequencias(PATH_FILE, base)
    
    Y = frequencias
    
    X_Labels = []
    for x in lambdas:
        X_Labels.append(round(x,6))
        
    
    X = range(1, len(Y)+1)
    
    plt.bar(X, Y, align='center', alpha=0.5)
    plt.xticks(X, X_Labels, rotation=20)
    
    for i in range(len(Y)):
        plt.text(x = X[i]-0.3 , y = Y[i]+0.1, s = Y[i], size = 15)

    
    plt.xlabel('Lambda Value')
    plt.ylabel('Selection Frequency')
    #plt.legend()
    plt.subplots_adjust(hspace=0.5, top=0.9)
    

for metodo in metodos:
    for base in bases:
        print(titulos[metodo] + ' - ' + base)
    
        fig, ax = plt.subplots() #Para o primeiro gráfico
        subplot_grafico5(metodo, base)
        ax.set_ylim(0, 4000)
        fig.savefig(ROOT_PATH_IMG + 'histogram'  + '_' + nomes[metodo] + '__' + base + '_' + '.eps', format='eps', dpi=1200, bbox_inches='tight')





        

