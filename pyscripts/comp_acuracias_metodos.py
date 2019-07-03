import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

#ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_HDDM_DDD_allbases/'
ROOT_PATH = '/home/regis/Documents/drive/regis/mestrado/resultados/comp_HDDM_DDD_allbases/'

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

bases = basesr

#'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning', 
#'V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning',

metodos = ['V14_HOM_OnlineBagging_HDDM_A_Test_RetreinaTodosComBufferWarning',
           'V12_HOM_OnlineBagging_HDDM_A_Test_RetreinaTodosComBufferWarning',
           'LB_Original', 
           'DDM_Original',
           'DDD_Original']

datasets = {}

resumo = {'metodo':[], 'base': [], 'media_acc': [], 'std_acc': []}
dt_resumo = pd.DataFrame(data=resumo)


for metodo in metodos:
    for base in bases:
        print(metodo + '_' + base)
        FILENAME = 'RESULT_' + metodo + '_' + base
        dataset = pd.read_csv(ROOT_PATH + FILENAME)
        datasets[metodo+'_'+base] = dataset
        media = dataset['taxa_media'].mean()
        std = dataset['taxa_media'].std()

        resumo['metodo'].append(metodo)
        resumo['base'].append(base)
        resumo['media_acc'].append(media)
        resumo['std_acc'].append(std)

dt_resumo = pd.DataFrame(data=resumo)
print(dt_resumo)


