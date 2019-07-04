# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
#from matplotlib import style
import pandas as pd

#style.use('default')

#Path
#ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_v14_LB_DDM_DDD__Online_DDM_BufferAndReset__sinteticas/'
ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/'



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

rangesy = {
        'Sine1': [70,100], 
        'Gauss': [70,100], 
        'Circle': [87,100],
        'AgrawalAbrupt': [70,92], 
        'AgrawalAbruptNoise': [60,90],
        'AgrawalGradual': [60,87],
        'AgrawalGradualNoise': [60,85],
        'SEAAbrupt': [90,95], 
        'SEAAbruptNoise': [78,87],
        'SEAGradual': [88,95],
        'SEAGradualNoise': [80,87],
        'PokerHand': [50,100], 
        'ForestCovertype': [80,100], 
        'Spam': [87,100],
        'KDDCup99': [99.9,100]
        }

baseEhReal = {
        'Sine1': False, 
        'Gauss': False, 
        'Circle': False, 
        'AgrawalAbrupt': False, 
        'AgrawalAbruptNoise': False,
        'AgrawalGradual': False,
        'AgrawalGradualNoise': False,
        'SEAAbrupt': False, 
        'SEAAbruptNoise': False,
        'SEAGradual': False,
        'SEAGradualNoise': False,
        'PokerHand': True, 
        'ForestCovertype': True, 
        'Spam': True, 
        'KDDCup99': True
        }

#V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning
metodos = [
        'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning', 
        'DDM_Original', 
        'LB_Original',
        'DDD_Original']
siglas = [
        'DESDD', 
        'DDM', 
        'LB',
        'DDD']

cores = [
        'k',
        'b',
        'g',
        'r']

marcador_tam = [50,10,10,10]      

for indice, base in enumerate(basess):
    real = baseEhReal[base]
    drift_base = drifts[base]
    rangey = rangesy[base]
    
    DATASETS = []
    Y = []
    DRIFT = []
    DRIFT_X = []
    DRIFT_Y = []
    QTD_DETECCAO = []
    
    DETECCAO_1 = [] #vai ser array de array
    ATRASO_ACUMULADO = [];
    
    for idx, val in enumerate(metodos):
        dataset_aux = pd.read_csv(ROOT_PATH + val + '_' + base + '_pareto__exec_1_drift.csv')
        DATASETS.append(dataset_aux)
        Y.append(dataset_aux['taxa'].values)
        DRIFT.append(dataset_aux.loc[dataset_aux['drift'] == 1])
        DRIFT_X.append(DRIFT[idx]['iteracao'].values)
        DRIFT_Y.append(DRIFT[idx]['taxa'].values)
        QTD_DETECCAO.append(len(DRIFT_X[idx]))
        #CALCULO DAS PRIMEIRAS DETECCOES
        DETECCAO_1.append([]) #Inicializacao
        ATRASO_ACUMULADO.append(0) #Inicializacao
        
        
    
    X = DATASETS[0]['iteracao'].values
    
    
    # Plotagem das figuras 
    def plotagem():
        fig, ax = plt.subplots()
    
        for idx, val in enumerate(metodos): 
            plt.plot(X, Y[idx], '-', label=siglas[idx], color=cores[idx], markersize=marcador_tam[idx])
            if real == False:
                plt.scatter(DRIFT_X[idx], DRIFT_Y[idx], color=cores[idx])
        #Desabilitar as duas linhas a seguir para as bases reais
        if real == False:
            for xc in drift_base:
                plt.axvline(x=xc)
    
        #ax = fig.add_subplot(111)
        #axes = plt.gca()
        #axes.set_xlim([0,1])
        #axes.set_ylim([0,1])
        plt.xlabel('Iteration')
        plt.ylabel('Accuracy')
        #plt.title(' Gráfico Iteração x Acurácia \n Base ' + base)
        plt.legend()
        #plt.show()
        ax.set_ylim(rangey[0], rangey[1])
        #plt.savefig(ROOT_PATH_IMG + 'figura.eps', format='eps', dpi=1000)
        fig.savefig(ROOT_PATH_IMG + 'figura_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')
    
    #plotagem()
    
    
    for idx, val in enumerate(drift_base):
        if (idx+1 < len(drift_base)):
            #Proximo
            proximo = drift_base[idx+1]
            
            #Calcula para cada metodo
            for idm, valm in enumerate(metodos):
                for drift_aux in DRIFT_X[idm]:
                    if (drift_aux >= val and drift_aux < proximo):
                        DETECCAO_1[idm].append(drift_aux)
                        ATRASO_ACUMULADO[idm] = ATRASO_ACUMULADO[idm] + drift_aux - val 
                        break
        else:
            #Calcula para cada metodo
            for idm, valm in enumerate(metodos):
                for drift_aux in DRIFT_X[idm]:
                    if (drift_aux >= val):
                        DETECCAO_1[idm].append(drift_aux)
                        ATRASO_ACUMULADO[idm] = ATRASO_ACUMULADO[idm] + drift_aux - val 
                        break
                
            
    TAXA = []
    FD = [] #FALSE DETECTION
    MD = [] #MISSIN DETECTION
            
    #CALCULOS FINAIS
    for idx, val in enumerate(metodos):  
        MD.append(len(drift_base) - len(DETECCAO_1[idx]))
        FD.append(QTD_DETECCAO[idx] - len(DETECCAO_1[idx]))          
        if real == False:
            TAXA.append(ATRASO_ACUMULADO[idx] / len(drift_base))
        else:
            TAXA.append(0)
            
            
    print('\n\n', base)
    for idx, val in enumerate(metodos):      
        print('\n' + val)
        print('D: ', QTD_DETECCAO[idx])
        print('FD: ', FD[idx])
        print('MD: ', MD[idx])
        print('DRIFTS: ', DRIFT_X[idx])
        print('1_DETECCOES: ', DETECCAO_1[idx])
        print('ATRASO ACUMULADO: ', ATRASO_ACUMULADO[idx])
        print('ADR: ', TAXA[idx])
    
    
    #fig.savefig(path_img + str(i) + '.png', bbox_inches='tight')
