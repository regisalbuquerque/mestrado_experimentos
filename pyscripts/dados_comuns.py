import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

bases_reais = [ 'PokerHand', 
                'ForestCovertype', 
                'Spam', 
                'KDDCup99']

bases_sinteticas = [ 'AgrawalAbrupt',
                     'AgrawalAbruptNoise',
                     'AgrawalGradual',
                     'AgrawalGradualNoise',
                     'Circle',
                     'Gauss',
                     'SEAAbrupt',
                     'SEAAbruptNoise',
                     'SEAGradual',
                     'SEAGradualNoise',
                     'Sine1' ]

limiteBase = {
        'Sine1': 10000, 
        'Gauss': 10000, 
        'Circle': 4000, 
        'AgrawalAbrupt': 10000, 
        'AgrawalAbruptNoise': 10000,
        'AgrawalGradual': 10000,
        'AgrawalGradualNoise': 10000,
        'SEAAbrupt': 10000, 
        'SEAAbruptNoise': 10000,
        'SEAGradual': 10000,
        'SEAGradualNoise': 10000,
        
        'Spam': 9324,
        'KDDCup99': 489844,
        'PokerHand': 829200, 
        'ForestCovertype': 581012 
        }

drifts = {
        'Sine1': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'Gauss': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'Circle': [1000,2000,3000],
        'AgrawalAbrupt': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'AgrawalAbruptNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'AgrawalGradual': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'AgrawalGradualNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'SEAAbrupt': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
        'SEAAbruptNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'SEAGradual': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        'SEAGradualNoise': [1000,2000,3000,4000,5000,6000,7000,8000,9000],
        
        'Spam': [],
        'KDDCup99': [],
        'PokerHand': [], 
        'ForestCovertype': [] 
       }

rangesy = {
        'Sine1': [70,100], 
        'Gauss': [70,100], 
        'Circle': [87,100],
        'AgrawalAbrupt': [70,92], 
        'AgrawalAbruptNoise': [60,90],
        'AgrawalGradual': [60,87],
        'AgrawalGradualNoise': [60,85],
        'SEAAbrupt': [90,95], 
        'SEAAbruptNoise': [78,87],
        'SEAGradual': [88,95],
        'SEAGradualNoise': [80,87],
        'PokerHand': [50,100], 
        'ForestCovertype': [80,100], 
        'Spam': [87,100],
        'KDDCup99': [99.9,100]
        }

baseEhReal = {
        'Sine1': False, 
        'Gauss': False, 
        'Circle': False, 
        'AgrawalAbrupt': False, 
        'AgrawalAbruptNoise': False,
        'AgrawalGradual': False,
        'AgrawalGradualNoise': False,
        'SEAAbrupt': False, 
        'SEAAbruptNoise': False,
        'SEAGradual': False,
        'SEAGradualNoise': False,
        'PokerHand': True, 
        'ForestCovertype': True, 
        'Spam': True, 
        'KDDCup99': True
        }

#V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning

metodos = [ 'V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning', 
            'V14_HOM_OnlineBagging_HDDM_A_Test_RetreinaTodosComBufferWarning']

baselines = [ 'DDM_Original', 
              'LB_Original',
              'DDD_Original']

    

