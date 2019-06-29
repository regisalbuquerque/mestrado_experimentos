# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
#from matplotlib import style
import pandas as pd

#style.use('default')

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
        'AgrawalAbrupt': [80,100], 
        'AgrawalAbruptNoise': [80,100],
        'AgrawalGradual': [80,100],
        'AgrawalGradualNoise': [80,100],
        'SEAAbrupt': [80,100], 
        'SEAAbruptNoise': [80,100],
        'SEAGradual': [80,100],
        'SEAGradualNoise': [80,100],
        'PokerHand': [80,100], 
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


base = 'Circle'
real = baseEhReal[base]
xcoord = drifts[base]
rangey = rangesy[base]

#Path
ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_v14_LB_DDM__Online_DDM_BufferAndReset__sinteticas/'
ROOT_PATH_IMG = '/Users/regisalbuquerque/Desktop/'

dataset_DESDD = pd.read_csv(ROOT_PATH + 'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning' + '_' + base + '_pareto__exec_1_drift.csv')
dataset_DDM = pd.read_csv(ROOT_PATH + 'DDM_Original' + '_' + base + '_pareto__exec_1_drift.csv')
dataset_LB = pd.read_csv(ROOT_PATH + 'LB_Original' + '_' + base + '_pareto__exec_1_drift.csv')


X = dataset_DESDD['iteracao'].values;

Y_DESDD    = dataset_DESDD['taxa'].values;
Y_DDM = dataset_DDM['taxa'].values;
Y_LB  = dataset_LB['taxa'].values;


DRIFT_DESDD = dataset_DESDD.loc[dataset_DESDD['drift'] == 1]
DESDD_X_DRIFT = DRIFT_DESDD['iteracao'].values;
DESDD_Y_DRIFT = DRIFT_DESDD['taxa'].values;
DESDD_QTD_DETECACAO = len(DESDD_X_DRIFT)

DRIFT_DDM = dataset_DDM.loc[dataset_DDM['drift'] == 1]
DDM_X_DRIFT = DRIFT_DDM['iteracao'].values;
DDM_Y_DRIFT = DRIFT_DDM['taxa'].values;
DDM_QTD_DETECCAO = len(DDM_X_DRIFT)

DRIFT_LB = dataset_LB.loc[dataset_LB['drift'] == 1]
LB_X_DRIFT = DRIFT_LB['iteracao'].values;
LB_Y_DRIFT = DRIFT_LB['taxa'].values;
LB_QTD_DETECCAO = len(LB_X_DRIFT)


fig, ax = plt.subplots()


plt.plot(X, Y_DESDD, '-', label='DESDD', color='k', markersize=50)
if real == False:
    plt.scatter(DESDD_X_DRIFT, DESDD_Y_DRIFT, label='DESDD Detection', color='k')

plt.plot(X, Y_DDM, '--', label='DDM', color='b', markersize=10)
if real == False:
    plt.scatter(DDM_X_DRIFT, DDM_Y_DRIFT, label='DDM Detection', color='b')

plt.plot(X, Y_LB, ':', label='LB', color='g', markersize=10)
if real == False:
    plt.scatter(LB_X_DRIFT, LB_Y_DRIFT, label='LB Detection', color='g')

#Desabilitar as duas linhas a seguir para as bases reais
if real == False:
    for xc in xcoord:
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
#fig.savefig(ROOT_PATH_IMG + 'figura_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')

print('DRIFTS:')
print(len(drifts))
print(drifts)

#CALCULO DAS PRIMEIRAS DETECCOES
DESDD_1_DETECCAO = []
DESDD_ATRASO_ACUMULADO = 0;
DDM_1_DETECCAO = []
DDM_ATRASO_ACUMULADO = 0;
LB_1_DETECCAO = []
LB_ATRASO_ACUMULADO = 0;
for idx, val in enumerate(xcoord):
    if (idx+1 < len(xcoord)):
        #Proximo
        proximo = xcoord[idx+1]
        
        for desdd in DESDD_X_DRIFT:
            if (desdd >= val and desdd < proximo):
                DESDD_1_DETECCAO.append(desdd)
                DESDD_ATRASO_ACUMULADO = DESDD_ATRASO_ACUMULADO + desdd - val 
                break
        for ddm in DDM_X_DRIFT:
            if (ddm >= val and ddm < proximo):
                DDM_1_DETECCAO.append(ddm)
                DDM_ATRASO_ACUMULADO = DDM_ATRASO_ACUMULADO + ddm - val
                break
        for lb in LB_X_DRIFT:
            if (lb >= val and lb < proximo):
                LB_1_DETECCAO.append(lb)
                LB_ATRASO_ACUMULADO = LB_ATRASO_ACUMULADO + lb - val
                break
    else:
        for desdd in DESDD_X_DRIFT:
            if (desdd >= val):
                DESDD_1_DETECCAO.append(desdd)
                DESDD_ATRASO_ACUMULADO = DESDD_ATRASO_ACUMULADO + desdd - val 
                break
        for ddm in DDM_X_DRIFT:
            if (ddm >= val):
                DDM_1_DETECCAO.append(ddm)
                DDM_ATRASO_ACUMULADO = DDM_ATRASO_ACUMULADO + ddm - val
                break
        for lb in LB_X_DRIFT:
            if (lb >= val):
                LB_1_DETECCAO.append(lb)
                LB_ATRASO_ACUMULADO = LB_ATRASO_ACUMULADO + lb - val
                break
        
        
TAXA_DESDD = DESDD_ATRASO_ACUMULADO / len(xcoord)
TAXA_DDM = DDM_ATRASO_ACUMULADO / len(xcoord)
TAXA_LB = LB_ATRASO_ACUMULADO / len(xcoord)

FD_DESDD = DESDD_QTD_DETECACAO - len(xcoord)
FD_DDM = DDM_QTD_DETECCAO - len(xcoord)
FD_LB = LB_QTD_DETECCAO - len(xcoord)
        
        

print('\n\nDESDD:')
print('Deteccoes: ',DESDD_QTD_DETECACAO)
print('Falsas Deteccoes: ',FD_DESDD)
print('DRIFTS: ', DESDD_X_DRIFT)
print('1_DETECCOES: ', DESDD_1_DETECCAO)
print('Atraso Acumulado: ', DESDD_ATRASO_ACUMULADO)
print('Taxa: ', TAXA_DESDD)

print('\nDDM:')
print('Deteccoes: ', DDM_QTD_DETECCAO)
print('Falsas Deteccoes: ',FD_DDM)
print('DRIFTS: ', DDM_X_DRIFT)
print('1_DETECCOES: ', DDM_1_DETECCAO)
print('Atraso Acumulado: ', DDM_ATRASO_ACUMULADO)
print('Taxa: ', TAXA_DDM)

print('\nLB:')
print('Deteccoes: ', LB_QTD_DETECCAO)
print('Falsas Deteccoes: ',FD_LB)
print('DRIFTS: ', LB_X_DRIFT)
print('1_DETECCOES: ', LB_1_DETECCAO)
print('Atraso Acumulado: ', LB_ATRASO_ACUMULADO)
print('Taxa: ', TAXA_LB)


#fig.savefig(path_img + str(i) + '.png', bbox_inches='tight')
