import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM__allbases/'

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

bases = basess


metodos = ['V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning', 'LB_Original', 'DDM_Original']

datasets = {}

resumo = {'metodo':[], 'base': [], 'tempo': []}
dt_resumo = pd.DataFrame(data=resumo)


for metodo in metodos:
    for base in bases:
        print(metodo + '_' + base)
        FILENAME = 'RESULT_' + metodo + '_' + base
        dataset = pd.read_csv(ROOT_PATH + FILENAME)
        datasets[metodo+'_'+base] = dataset
        media = dataset['tempo'].mean()

        resumo['metodo'].append(metodo)
        resumo['base'].append(base)
        resumo['tempo'].append(media)

dt_resumo = pd.DataFrame(data=resumo)
print(dt_resumo)


