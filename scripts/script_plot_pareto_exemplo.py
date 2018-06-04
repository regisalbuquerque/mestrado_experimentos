# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

#PONTOS
TAM_PONTO = 16;
TAM_PONTO_ATUAL = 16;

#PASTA_CONJUNTOS
conjunto_homogeneo = 'pareto25_hom/'
conjunto_heterogeneo = 'pareto/'

#BASE
base = 'Sine1'
inicio = 1
termino = 10000

    
def pontos_atuais(iteracao, tamanho, cor, cor_pareto, conjunto):
    
    path = '/home/regis/Documents/git/regis/mestrado/implementacoes/resultados/ensembles/'+ conjunto + base + '/csv/' + base + '_pareto_it_'
    
    dataset = pd.read_csv(path + str(iteracao) + '.csv')
    
    dataset1 = dataset.loc[dataset['pareto_member'] == False]
    X = dataset1['diversidade'].values
    Y = dataset1['acc'].values
    
    dataset2 = dataset.loc[dataset['pareto_member'] == True]
    X_pareto = dataset2['diversidade'].values
    Y_pareto = dataset2['acc'].values
    
    if len(X_pareto) > 5:
    
        plt.subplot(111)
        
        plt.scatter(X, Y, s=tamanho, label='Comitê', color=cor)
        plt.scatter(X_pareto, Y_pareto, s=tamanho, label='Comitê(Pareto)', color=cor_pareto)  
        
        axes = plt.gca()
        axes.set_xlim([-0.01,0.52])
        axes.set_ylim([0,1])
        
        plt.xlabel('Diversidade')
        plt.ylabel('Acurácia Prequencial')
        plt.title('Iteração ' + str(iteracao))
        plt.legend()
        
        plt.show()
    

pontos_atuais(3155, TAM_PONTO_ATUAL, 'k', 'b', conjunto_heterogeneo)



