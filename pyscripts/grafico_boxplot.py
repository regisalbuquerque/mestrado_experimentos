import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_v14_LB_DDM__Online_DDM_BufferAndReset__sinteticas/'
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

bases = basess


metodos = ['V12_HOM_OnlineBagging_DDM', 'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning']

titulos = {
           'V12_HOM_OnlineBagging_DDM':'V12 - OnlineBagging com Reset', 
           'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning': 'V12 - OnlineBagging com Buffer'
           }


def calcula_lambdas(path_file, base):
    #Cria os contadores
    PRIMEIRA_IT = pd.read_csv(path_file + '1.csv')
    PRIMEIRA_IT_ORD = PRIMEIRA_IT.sort_values('cod')
    lambdas_df = PRIMEIRA_IT_ORD['cod']
    lambdas = []
    for it in lambdas_df:
        lambdas.append(it)

    return lambdas

def localiza_diversidade(lambda_x, path_file, base):
    DIVERSIDADES = []
    for it in range(1, limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        LINHA = RESULTADO.loc[RESULTADO['cod'] == lambda_x]
        DIVERSIDADE = LINHA['diversidade'].values[0]
        DIVERSIDADES.append(DIVERSIDADE)
    return DIVERSIDADES
 

def subplot_grafico10(metodo, base):
    
    PATH_FILE = ROOT_PATH_ITERACOES + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';

    Y_ARRAY = []

    X = calcula_lambdas(PATH_FILE , base);
    for lambda_x in X:
        Y = localiza_diversidade(lambda_x, PATH_FILE, base);
        Y_ARRAY.append(Y)
    
    
    plt.boxplot(Y_ARRAY, notch=True)
    
    axes = plt.gca()
    axes.set_ylim([-0.1, 0.6])
    
    X_range = range(1, len(X)+1)
    plt.xticks(X_range, X)
    
    plt.xlabel('Lambda')     
    plt.ylabel('Diversity')
    #plt.title(titulo)
    
    return Y_ARRAY, X


for metodo in metodos:
    for base in bases:
        print(titulos[metodo] + ' - ' + base)
    
        fig, ax = plt.subplots() #Para o primeiro gráfico
        plt.subplot(4,1,1)
        retorno1, retorno2 = subplot_grafico10(metodo, base)
        plt.title(titulos[metodo] + ' - ' + base)
        fig.set_figheight(10)
        fig.set_figwidth(15)
        fig.savefig(ROOT_PATH_IMG + 'boxplot'  + '_' + titulos[metodo] + '__' + base + '_' + '.eps', format='eps', dpi=1200, bbox_inches='tight')





        

