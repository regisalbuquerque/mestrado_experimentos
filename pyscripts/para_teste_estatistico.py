import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import dados_comuns as dados

ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/'

bases = dados.bases_reais

resumo = {'metodo':[], 'base': [], 'media_acc': [], 'std_acc': []}
dt_resumo = pd.DataFrame(data=resumo)

datasets = {}


metodos = ['V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning']
for baseline in dados.baselines:
    metodos.append(baseline)

def executa(metodo, base):
    print(metodo)
    print(base)
    FILENAME = 'RESULT_' + metodo + '_' + base
    dataset = pd.read_csv(ROOT_PATH + FILENAME)
    datasets[metodo+'_'+base] = dataset
    media = dataset['taxa_media'].mean()
    std = dataset['taxa_media'].std()

    resumo['metodo'].append(metodo)
    resumo['base'].append(base)
    resumo['media_acc'].append(media)
    resumo['std_acc'].append(std)
    

for base in bases:
    for metodo in metodos:
        executa(metodo, base)

        
dt_resumo = pd.DataFrame(data=resumo)
print(dt_resumo)


