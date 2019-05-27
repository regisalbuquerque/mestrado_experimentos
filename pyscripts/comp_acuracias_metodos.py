import pandas as pd
import matplotlib.pyplot as plt

ROOT_PATH = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/v1x_Online_DDM_ResetAll_newbases/'

bases = [
        'Line',
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

metodos = ['V14_HOM_OnlineBagging_DDM']

datasets = {}

for metodo in metodos:
    for base in bases:
        FILENAME = 'RESULT_' + metodo + '_' + base
        dataset = pd.read_csv(ROOT_PATH + FILENAME)
        datasets[metodo+'_'+base] = dataset



