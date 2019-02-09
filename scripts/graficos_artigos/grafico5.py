
# Gráfico do Ensemble X Frequência. HISTOGRAMA 

import matplotlib.pyplot as plt

import dados as dados

#style.use('default')

def executa(base, metodo1, titulo1, metodo2, titulo2):

    fig, ax = plt.subplots()
    
    plt.subplot(211)
    dados.subplot_grafico5(metodo1, base, titulo1)
    plt.subplot(212)
    dados.subplot_grafico5(metodo2, base, titulo2)
    
    #plt.show()
    
    fig.set_figheight(8)
    fig.set_figwidth(15)
    fig.savefig(dados.ROOT_PATH_IMG + 'grafico5_' + '_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')


