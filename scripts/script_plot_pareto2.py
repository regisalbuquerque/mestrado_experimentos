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
base = 'Circle'
inicio = 1
termino = 4000

def pontos(inicio, termino, tamanho, cor, path):
    X_PTS_BEFORE = []
    Y_PTS_BEFORE = []
    X_PTS_BEFORE_PARETO = []
    Y_PTS_BEFORE_PARETO = []
    for i in range(inicio, termino):
        dataset = pd.read_csv(path + str(i) + '.csv')
        
        dataset1 = dataset.loc[dataset['pareto_member'] == False]
        X_PTS_BEFORE.extend(dataset1['diversidade'].values)
        Y_PTS_BEFORE.extend(dataset1['acc'].values)
    
        dataset2 = dataset.loc[dataset['pareto_member'] == True]
        X_PTS_BEFORE_PARETO.extend(dataset2['diversidade'].values)
        Y_PTS_BEFORE_PARETO.extend(dataset2['acc'].values)
    
    plt.scatter(X_PTS_BEFORE, Y_PTS_BEFORE, alpha=0.5, s=tamanho, color=cor)
    plt.scatter(X_PTS_BEFORE_PARETO, Y_PTS_BEFORE_PARETO, alpha=0.5, s=tamanho, color=cor)
    
def pontos_atuais(iteracao, tamanho, cor, cor_pareto, path):
    dataset = pd.read_csv(path + str(iteracao) + '.csv')
    
    dataset1 = dataset.loc[dataset['pareto_member'] == False]
    X = dataset1['diversidade'].values
    Y = dataset1['acc'].values
    
    dataset2 = dataset.loc[dataset['pareto_member'] == True]
    X_pareto = dataset2['diversidade'].values
    Y_pareto = dataset2['acc'].values
    
    plt.scatter(X, Y, s=tamanho, label='Comitê', color=cor)
    plt.scatter(X_pareto, Y_pareto, s=tamanho, label='Comitê(Pareto)', color=cor_pareto)    

def subplot(iteracao, conjunto):
    path_csv = '/home/regis/Documents/git/regis/mestrado/implementacoes/resultados/ensembles/'+ conjunto + base + '/csv/' + base + '_pareto_it_'
    path_img = '/home/regis/Documents/git/regis/mestrado/implementacoes/resultados/ensembles/'+ conjunto + base + '/img/' + base + '_pareto_it_'
    
    # TODOS OS PONTOS - ANTERIORES
    pontos(inicio, iteracao, TAM_PONTO, '0.75', path_csv)
    # TODOS OS PONTOS - POSTERIORES
    pontos(iteracao+1, termino+1, TAM_PONTO, '0.75', path_csv)
    
    #APENAS OS PONTOS ATUAIS
    pontos_atuais(iteracao, TAM_PONTO_ATUAL, 'k', 'b', path_csv)
    
    axes = plt.gca()
    axes.set_xlim([-0.01,0.52])
    axes.set_ylim([0,1])
    
    plt.xlabel('Diversidade')
    plt.ylabel('Acurácia Prequencial')
    plt.title('Iteração ' + str(iteracao))
    plt.legend()
    
plt.subplot(241)
subplot(50, conjunto_heterogeneo)   
plt.subplot(242)
subplot(950, conjunto_heterogeneo) 
plt.subplot(243)
subplot(1050, conjunto_heterogeneo) 
plt.subplot(244)
subplot(termino, conjunto_heterogeneo)  

plt.subplot(245)
subplot(50, conjunto_homogeneo)   
plt.subplot(246)
subplot(950, conjunto_homogeneo) 
plt.subplot(247)
subplot(1050, conjunto_homogeneo) 
plt.subplot(248)
subplot(termino, conjunto_homogeneo)  
    
#fig = plt.figure() 
plt.show()

#fig.savefig(path_img + str(iteracao_atual) + '.png', bbox_inches='tight')