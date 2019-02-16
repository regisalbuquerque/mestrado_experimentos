import matplotlib.pyplot as plt


import dados as dados

bases = ['Line', 'Sine1', 'Gauss', 'Circle']

metodo1 = 'V12_HOM_LeverageBagging_ADWINChangeDetector'
metodo2 = 'V13_HOM_LeverageBagging_ADWINChangeDetector'
base = 'Line'


#    grafico5.executa(base, metodo1, 'Fixed_Initialization', metodo2, 'Random_Initialization')
#    grafico6.executa(base, metodo1, 'Fixed_Initialization', metodo2, 'Random_Initialization')
    
    
fig, ax = plt.subplots()

#subplot_grafico7(metodo, base, titulo, inicio, termino):

plt.subplot(211)
dados.subplot_grafico7(metodo1, base, 'Fixed_Initialization', 1, 1000)
plt.subplot(212)
dados.subplot_grafico7(metodo1, base, 'Fixed_Initialization', 1001, 2000)

#plt.show()

fig.set_figheight(8)
fig.set_figwidth(15)
fig.savefig(dados.ROOT_PATH_IMG + 'grafico7_' + '_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')


        

