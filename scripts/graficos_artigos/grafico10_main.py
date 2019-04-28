import matplotlib.pyplot as plt
import pandas as pd
import dados as dados
import seaborn as sns


#bases = ['Line', 'Sine1', 'Gauss', 'Circle']
bases = ['Line']

metodos = ['V12_HOM_OnlineBagging_DDM']

titulos = {
           'V12_HOM_OnlineBagging_DDM':'Fixed_Initialization', 
           'V13_HOM_OnlineBagging_DDM': 'Random_Initialization'
           }

base = 'Line'



def calcula_lambdas(path_file, base):
    #Cria os contadores
    PRIMEIRA_IT = pd.read_csv(path_file + '1.csv')
    PRIMEIRA_IT_ORD = PRIMEIRA_IT.sort_values('cod')
    lambdas_df = PRIMEIRA_IT_ORD['cod']
    lambdas = []
    for it in lambdas_df:
        lambdas.append(it)

    return lambdas

def localiza_diversidade(lambda_x, path_file, base):
    DIVERSIDADES = []
    for it in range(1, dados.limiteBase[base]+1):
        RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
        LINHA = RESULTADO.loc[RESULTADO['cod'] == lambda_x]
        DIVERSIDADE = LINHA['diversidade'].values[0]
        DIVERSIDADES.append(DIVERSIDADE)
    return DIVERSIDADES
 

def subplot_grafico10(metodo, base):
    
    PATH_FILE = dados.ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';

    Y_ARRAY = []

    X = calcula_lambdas(PATH_FILE , base);
    for lambda_x in X:
        Y = localiza_diversidade(lambda_x, PATH_FILE, base);
        Y_ARRAY.append(Y)
    
    
    sns.boxplot(data=Y_ARRAY)
    
    return Y_ARRAY, X


for metodo in metodos:
    for base in bases:
        print(metodo + ' - ' + base)
    
        fig, ax = plt.subplots() #Para o primeiro gráfico
        plt.subplot(4,1,1)
        retorno1, retorno2 = subplot_grafico10(metodo, base)
        fig.set_figheight(8)
        fig.set_figwidth(15)
        fig.savefig(dados.ROOT_PATH_IMG + 'grafico10_'  + '_' + titulos[metodo] + '_' + base + '_' + '.eps', format='eps', dpi=1200, bbox_inches='tight')





        

