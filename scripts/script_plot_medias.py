# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from matplotlib import style

style.use('fivethirtyeight')

base = 'KDDCup99'

path_csv = '/home/regis/Documents/git/regis/mestrado/implementacoes/experimentos/resultados/ensembles/medias/' + base; 
path_img = '/home/regis/Documents/git/regis/mestrado/implementacoes/experimentos/resultados/ensembles/medias/imgs/' 

datasetHOMO = pd.read_csv(path_csv + '_homogeneo_resumo.csv')
datasetHETE = pd.read_csv(path_csv + '_heterogeneo_resumo.csv')

LAMBDA = datasetHOMO['cod'].values
X = range(0, 15)

DIV_HOMO = datasetHOMO['ambiguidade_media'].values
ACC_HOMO = datasetHOMO['taxa_media'].values

DIV_HETE = datasetHETE['ambiguidade_media'].values
ACC_HETE = datasetHETE['taxa_media'].values


def plotar(ax, X_het, Y_het, X_hom, Y_hom, x_lim1, x_lim2, y_lim1, y_lim2, x_label, y_label, titulo):
    ax.plot(X_het, Y_het, '--', label='Heterogêneos', color='b')
    ax.plot(X_hom, Y_hom, label='Homogêneos', color='k')

    ax.set_xlim([x_lim1, x_lim2])
    ax.set_ylim([y_lim1, y_lim2])

    ax.set_xlabel(x_label)
    ax.set_ylabel(y_label)
    ax.set_title(titulo)
    ax.legend()
    
def plotar_set_ticks(ax, TICKS, LABELS):
    ax.set_xticks(TICKS)
    ax.set_xticklabels(LABELS, rotation='45')



# PLOT --- Lambda x Diversidade
fig = plt.figure(num=None, figsize=(12, 8), dpi=120, facecolor='w', edgecolor='k', tight_layout=True)

'''
ax1 = plt.subplot2grid((2,2), (0,0), rowspan=1, colspan=1)
ax2 = plt.subplot2grid((2,2), (1,0), rowspan=1, colspan=1)
ax3 = plt.subplot2grid((2,2), (0,1), rowspan=2, colspan=1)
'''
'''
ax1 = plt.subplot2grid((1,3), (0,0), rowspan=1, colspan=1)
ax2 = plt.subplot2grid((1,3), (0,1), rowspan=1, colspan=1)
ax3 = plt.subplot2grid((1,3), (0,2), rowspan=1, colspan=1)
'''
ax1 = plt.subplot2grid((6,3), (0,0), rowspan=4, colspan=1)
ax2 = plt.subplot2grid((6,3), (0,1), rowspan=4, colspan=1)
ax3 = plt.subplot2grid((6,3), (0,2), rowspan=4, colspan=1)

plotar(ax1, X, DIV_HETE, X, DIV_HOMO, 0, 15, 0, 0.5, 'x - Lambda', 'y - Diversidade', 'Base ' + base + '\nLambda x Diversidade ')
plotar_set_ticks(ax1, X, LAMBDA)

plotar(ax2, X, ACC_HETE, X, ACC_HOMO, 0, 15, 0, 100, 'x - Lambda', 'y - Acurácia', 'Base ' + base + '\nLambda x Acurácia ')
plotar_set_ticks(ax2, X, LAMBDA)

plotar(ax3, DIV_HETE, ACC_HETE, DIV_HOMO, ACC_HOMO, 0, 0.5, 0, 100, 'x - Diversidade', 'y - Acurácia', 'Base ' + base + '\nDiversidade x Acurácia ')

plt.show()  

fig.savefig(path_img + base + '_medias_fig.png', bbox_inches='tight')
    

    