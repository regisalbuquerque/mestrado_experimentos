
# Gráfico do Ensemble Escolhido x Iterações. 

import matplotlib.pyplot as plt

import dados as dados

#style.use('default')


def executa(base, metodo):

    fig, ax = plt.subplots()
    
    plt.subplot(111)
    dados.subplot_grafico2(metodo, dados.conjunto_heterogeneo, base)
    #plt.subplot(212)
    #dados.subplot_grafico2(metodo, dados.conjunto_homogeneo, base)
    
    #plt.show()
    
    fig.set_figheight(3)
    fig.set_figwidth(15)
    fig.savefig(dados.ROOT_PATH_IMG + 'grafico2_' + metodo + '_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')


