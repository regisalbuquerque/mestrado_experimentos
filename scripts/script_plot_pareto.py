# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

base = 'Circle'
inicio = 2500
termino = 2700
#descricao = ' - Conjuntos Heterogêneos'
descricao = ' - Conjuntos Homogêneos'

path_csv = '/home/regis/Documents/git/regis/mestrado/implementacoes/resultados/ensembles/pareto25_hom/' + base + '/csv/' + base + '_pareto_it_'
path_img = '/home/regis/Documents/git/regis/mestrado/implementacoes/resultados/ensembles/pareto25_hom/' + base + '/img/' + base + '_pareto_it_'


for i in range(inicio, termino+1):
    # Importing the dataset
    dataset = pd.read_csv(path_csv + str(i) + '.csv')
    
    dataset1 = dataset.loc[dataset['pareto_member'] == False]
    
    X = dataset1['diversidade'].values
    Y = dataset1['acc'].values
    
    dataset2 = dataset.loc[dataset['pareto_member'] == True]
    
    X_pareto = dataset2['diversidade'].values
    Y_pareto = dataset2['acc'].values

    
    fig = plt.figure()
    
    plt.scatter(X, Y, label='Ensemble', color='k')
    plt.scatter(X_pareto, Y_pareto, label='Ensemble(pareto)', color='b')
    
    #texto = ""
    #for index, row in dataset2.iterrows():
    #  texto = texto + "λ=" + str(row['cod']) + " (" + str("%.2f" % round(row['diversidade'],2)) + "," + str("%.2f" % round(row['acc'],2)) + ") Acertou=" + str(row['acertou']) + "\n"
    
    #ax = fig.add_subplot(111)
    
    #plt.text(0.4, 0.3,texto,
    # horizontalalignment='left',
    # verticalalignment='center',
    # transform = ax.transAxes)
    
    
    axes = plt.gca()
    axes.set_xlim([0,1])
    axes.set_ylim([0,1])
    
    plt.xlabel('Diversidade')
    plt.ylabel('Acurácia Prequencial')
    #plt.title('Espaço Objetivo' + descricao + '\nBase ' + base  +' - Iteração ' + str(i))
    plt.title('Iteração ' + str(i))
    plt.legend()
    plt.show()
    
    fig.savefig(path_img + str(i) + '.png', bbox_inches='tight')