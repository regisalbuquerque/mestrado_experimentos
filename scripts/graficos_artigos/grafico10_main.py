import matplotlib.pyplot as plt
import pandas as pd
import dados as dados
import seaborn as sns


#bases = ['Line', 'Sine1', 'Gauss', 'Circle']
bases = ['Line']

metodos = ['V12_HOM_OnlineBagging_DDM', 'V13_HOM_OnlineBagging_DDM']

titulos = {
           'V12_HOM_OnlineBagging_DDM':'Fixed_Initialization', 
           'V13_HOM_OnlineBagging_DDM': 'Random_Initialization'
           }

base = 'Line'
 

def subplot_grafico10(metodo, base):
    
    PATH_FILE = dados.ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';

    X = range(1, dados.limiteBase[base]+1)
    Y = dados.localiza_vencedores(PATH_FILE, base);
    
    sns.boxplot(Y)
    


for metodo in metodos:
    for base in bases:
        print(metodo + ' - ' + base)
    
        fig, ax = plt.subplots() #Para o primeiro gráfico
        plt.subplot(4,1,1)
        subplot_grafico10(metodo, base)
        fig.set_figheight(8)
        fig.set_figwidth(15)
        fig.savefig(dados.ROOT_PATH_IMG + 'grafico10_'  + '_' + titulos[metodo] + '_' + base + '_' + '.eps', format='eps', dpi=1200, bbox_inches='tight')





        

