# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
#from matplotlib import style
import pandas as pd

#style.use('default')

xcoords = {
           'Line':[1000], 
           'Sine1': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
           'Gauss': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
           'Circle': [1000,2000,3000],
           'Elec': [],
           'Spam': [],
           'KDDCup99': []
           }

rangesy = {
        'Line':[91,100], 
        'Sine1': [70,100], 
        'Gauss': [70,100], 
        'Circle': [87,100],
        'Elec': [80,100],
        'Spam': [87,100],
        'KDDCup99': [99.9,100]
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


base = 'Sine1'
real = baseEhReal[base]
xcoord = xcoords[base]
rangey = rangesy[base]

#Path
ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/Teste_v12_v13_com_drift/'
path_file = ROOT_PATH 
ROOT_PATH_IMG = '/Users/regisalbuquerque/Desktop/'


dataset_DESDD = pd.read_csv(path_file + 'V12_HOM_LeverageBagging_ADWINChangeDetector_' + base + '_pareto__exec_1_drift.csv')


X = dataset_DESDD['iteracao'].values;
Y_DESDD    = dataset_DESDD['taxa'].values;


DRIFT_DESDD = dataset_DESDD.loc[dataset_DESDD['drift'] == 1]
DESDD_X_DRIFT = DRIFT_DESDD['iteracao'].values;
DESDD_Y_DRIFT = DRIFT_DESDD['taxa'].values;
DESDD_QTD_DETECACAO = len(DESDD_X_DRIFT)

fig, ax = plt.subplots()


plt.plot(X, Y_DESDD, '-', label='DESDD', color='k', markersize=50)
if real == False:
    plt.scatter(DESDD_X_DRIFT, DESDD_Y_DRIFT, label='DESDD Detection', color='k')

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
fig.savefig(ROOT_PATH_IMG + 'figura_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')

print('DRIFTS:')
print(len(xcoords))
print(xcoords)

#CALCULO DAS PRIMEIRAS DETECCOES
DESDD_1_DETECCAO = []
DESDD_ATRASO_ACUMULADO = 0;

for idx, val in enumerate(xcoord):
    if (idx+1 < len(xcoord)):
        #Proximo
        proximo = xcoord[idx+1]
        
        for desdd in DESDD_X_DRIFT:
            if (desdd >= val and desdd < proximo):
                DESDD_1_DETECCAO.append(desdd)
                DESDD_ATRASO_ACUMULADO = DESDD_ATRASO_ACUMULADO + desdd - val 
                break
    else:
        for desdd in DESDD_X_DRIFT:
            if (desdd >= val):
                DESDD_1_DETECCAO.append(desdd)
                DESDD_ATRASO_ACUMULADO = DESDD_ATRASO_ACUMULADO + desdd - val 
                break
        
TAXA_DESDD = DESDD_ATRASO_ACUMULADO / len(xcoord)

FD_DESDD = DESDD_QTD_DETECACAO - len(xcoord)
        
        

print('\n\nDESDD:')
print('Deteccoes: ',DESDD_QTD_DETECACAO)
print('Falsas Deteccoes: ',FD_DESDD)
print('DRIFTS: ', DESDD_X_DRIFT)
print('1_DETECCOES: ', DESDD_1_DETECCAO)
print('Atraso Acumulado: ', DESDD_ATRASO_ACUMULADO)
print('Taxa: ', TAXA_DESDD)


#fig.savefig(path_img + str(i) + '.png', bbox_inches='tight')
