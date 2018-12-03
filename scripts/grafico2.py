


# Gráfico do Ensemble Escolhido x Iterações. 
# Colocar os momentos de drift.


import numpy as np
import matplotlib.pyplot as plt

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
        'Line':[0,11], 
        'Sine1': [0,11], 
        'Gauss': [0,11], 
        'Circle': [0,11],
        'Elec': [0,11],
        'Spam': [0,11],
        'KDDCup99': [0,11]
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


# CLASSIFICADOR
metodo = 'V12'

# PASTA_CONJUNTOS
conjunto_homogeneo = metodo + '_HOM/'
conjunto_heterogeneo = metodo + '_HET/'

conjunto = conjunto_homogeneo;

base = 'Sine1'

real = baseEhReal[base]
xcoord = xcoords[base]
rangey = rangesy[base]
limite = limiteBase[base]

#Path
ROOT_PATH = '/Users/regisalbuquerque/Documents/git/regis/mestrado_resultados/comparacao3/pareto/'
ROOT_PATH_IMG = '/Users/regisalbuquerque/Desktop/'

PATH_FILE = ROOT_PATH + conjunto + base + '/' + base + '_pareto__exec_0_it_';

VENCEDORES = []

for it in range(1, limite+1):
    RESULTADO = pd.read_csv(PATH_FILE + str(it) + '.csv')
    X = RESULTADO.loc[RESULTADO['pareto_maior'] == True]
    VENCEDOR = X.index.values[0]
    print('\n\nVENCEDOR:' + str(VENCEDOR))
    VENCEDORES.append(VENCEDOR)
    

ENSEMBLES = RESULTADO.index.values


fig, ax = plt.subplots()

X = range(1, limite+1)

plt.plot(X, VENCEDORES, '-', label='', color='k', markersize=10)


if real == False:
    for xc in xcoord:
        plt.axvline(x=xc)


plt.xlabel('Iteration')
plt.ylabel('Ensemble')
#plt.title(' Gráfico Iteração x Acurácia \n Base ' + base)

ax.set_ylim(rangey[0], rangey[1])

plt.legend()
plt.show()


#plt.savefig(ROOT_PATH_IMG + 'figura.eps', format='eps', dpi=1000)
#fig.savefig(ROOT_PATH_IMG + 'figura_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')


