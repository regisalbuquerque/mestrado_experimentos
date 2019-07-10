import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import dados_comuns as dados
import statistics 

ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/'
TAM = 30

bases = dados.bases_sinteticas



#Adiciona os métodos (DESDD + baselines)
metodos = ['V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning']
for baseline in dados.baselines:
    metodos.append(baseline)





def busca_dados(metodos, bases):


    
    resumo = {'metodo':[], 'base': [], 'taxas': [], 'taxas_part': [], 'media': [], 'media_part': []}

    for base in bases:
        for metodo in metodos:
            dataset_aux = pd.read_csv(ROOT_PATH + metodo + '_' + base + '_pareto__exec_1_drift.csv')
            resumo['metodo'].append(metodo)
            resumo['base'].append(base)
            taxas = dataset_aux['taxa'].values
            resumo['taxas'].append(dataset_aux['taxa'].values)
            resumo['media'].append(dataset_aux['taxa'].mean())
            taxas_part = []
            #Calcula a particao
            particao = int(len(dataset_aux['taxa'].values)/TAM)
            for it in range(1,TAM+1):
                index = particao*it
                taxas_part.append(taxas[index-1])
            resumo['taxas_part'].append(taxas_part)
            resumo['media_part'].append(statistics.mean(taxas_part))
     
    return pd.DataFrame(data=resumo)
            
dt_resumo = busca_dados(metodos, bases)

print(dt_resumo)


