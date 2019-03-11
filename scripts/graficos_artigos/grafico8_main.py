import matplotlib.pyplot as plt
import pandas as pd
import dados as dados

#bases = ['Line', 'Sine1', 'Gauss', 'Circle']
bases = ['Line']

metodos = ['V12_HOM_LeverageBagging_ADWINChangeDetector', 'V13_HOM_LeverageBagging_ADWINChangeDetector']

titulos = {
           'V12_HOM_LeverageBagging_ADWINChangeDetector':'Fixed_Initialization', 
           'V13_HOM_LeverageBagging_ADWINChangeDetector': 'Random_Initialization'
           }

metodo1 = 'V12_HOM_LeverageBagging_ADWINChangeDetector' #'Fixed_Initialization'
metodo2 = 'V13_HOM_LeverageBagging_ADWINChangeDetector' #'Random_Initialization'
base = 'Line'
 

def subplot_grafico8(metodo, base):
    
    PATH_FILE = dados.ROOT_PATH2 + metodo + '/' + base + '/' + metodo + '_' + base + '_pareto__exec_1_it_';

    X = range(1, dados.limiteBase[base]+1)
    Y = dados.localiza_vencedores(PATH_FILE, base);
    
    plt.plot(X, Y, '-', label='', color='k', markersize=10)
    
     # DRIFTS 
    if dados.baseEhReal[base] == False:
        for xc in dados.drifts[base]:
            plt.axvline(x=xc)
    
    axes = plt.gca()
    #axes.set_xlim([-0.01, 0.52])
    axes.set_ylim(dados.ranges_ensemble[base])
    
    plt.xlabel('Iteration')
    plt.ylabel('Ensemble')
    #plt.title('Gráfico 2: ' + base + ' - ' + metodo + conjunto)
    #plt.legend()
    plt.subplots_adjust(hspace=0.6)


for metodo in metodos:
    for base in bases:
        print(metodo + ' - ' + base)
    
        fig, ax = plt.subplots() #Para o primeiro gráfico
        plt.subplot(4,1,1)
        subplot_grafico8(metodo, base)
        fig.set_figheight(8)
        fig.set_figwidth(15)
        fig.savefig(dados.ROOT_PATH_IMG + 'grafico8_'  + '_' + titulos[metodo] + '_' + base + '_' + '.eps', format='eps', dpi=1200, bbox_inches='tight')





        

