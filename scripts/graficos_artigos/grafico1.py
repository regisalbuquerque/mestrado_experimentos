
# Gráfico da Acurácia_Prequencial x Diversidade.

# Analise de Pareto para algumas iterações.
# Destacar o ensemble escolhido no gráfico.

import matplotlib.pyplot as plt

import dados as dados

def executa(base, metodo):

    
    fig, ax = plt.subplots()
    
    
    plt.subplot(141)
    dados.subplot_grafico1(50, metodo, dados.conjunto_heterogeneo, base)
    plt.subplot(142)
    dados.subplot_grafico1(950, metodo, dados.conjunto_heterogeneo, base)
    plt.subplot(143)
    dados.subplot_grafico1(1050, metodo, dados.conjunto_heterogeneo, base)
    plt.subplot(144)
    dados.subplot_grafico1(dados.termino(base), metodo, dados.conjunto_heterogeneo, base)
    
#    plt.subplot(245)
#    dados.subplot_grafico1(50, metodo, dados.conjunto_homogeneo, base)
#    plt.subplot(246)
#    dados.subplot_grafico1(950, metodo, dados.conjunto_homogeneo, base)
#    plt.subplot(247)
#    dados.subplot_grafico1(1050, metodo, dados.conjunto_homogeneo, base)
#    plt.subplot(248)
#    dados.subplot_grafico1(dados.termino(base), metodo, dados.conjunto_homogeneo, base)
    
    
    #plt.show()
    fig.set_figheight(3)
    fig.set_figwidth(15)
    #fig.suptitle('Gráfico 1: ' + base + ' ' + metodo, fontsize=16)
    fig.savefig(dados.ROOT_PATH_IMG + 'grafico1_' + metodo + '_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')