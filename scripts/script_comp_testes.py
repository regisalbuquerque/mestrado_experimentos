# Importing the libraries
import numpy as np
import pandas as pd
from scipy import stats 
from scipy.stats import ttest_1samp, wilcoxon, ttest_ind, mannwhitneyu

base = 'Circle'

path_file = '/home/regis/Documents/git/regis/mestrado/implementacoes/experimentos/resultados/metodos/comparacao/' + base


dataset_DESDD_SR = pd.read_csv(path_file + 'V12_SimpleReset_Ambiguidade_iteracao.csv')
dataset_DESDD_RB = pd.read_csv(path_file + 'V12_RetreinaTodosComBufferWarning_Ambiguidade_iteracao.csv')
#dataset_DESDD_LB_RS  = pd.read_csv(path_file + 'LB_SelectionV2SimpleResetSystem_Ambiguidade_iteracao.csv')
dataset_DDM = pd.read_csv(path_file + 'DDM_Original_iteracao.csv')
dataset_LB = pd.read_csv(path_file + 'LB_Original_iteracao.csv')

Y_DESDD_SR = dataset_DESDD_SR['taxa'].values;
Y_DESDD_RB = dataset_DESDD_RB['taxa'].values;
#Y_DESDD_LB_RS = dataset_DESDD_LB_RS['taxa'].values;


# Line   Y_DESDD_RB
# Sine1  Y_DESDD_RB
# Gauss  Y_DESDD_LB_RS
# Circle Y_DESDD_SR

# REAIS Y_DESDD_SR


Y_DESDD = Y_DESDD_SR; # <<-- ALTERAR AQUI!!!
Y_DDM = dataset_DDM['taxa'].values;
Y_LB  = dataset_LB['taxa'].values;

# Comparação com DDM

statistic_t, p_value_t = ttest_ind(Y_DESDD, Y_DDM)
statistic_n, p_value_n = mannwhitneyu(Y_DESDD, Y_DDM)
statistic_w, p_value_w = wilcoxon(Y_DESDD, Y_DDM)
statistic_w2, p_value_w2 = stats.ranksums(Y_DESDD, Y_DDM) 

# Comparação com LB
print(p_value_t)
print(p_value_n)
print(p_value_w)
print(p_value_w2)


