import grafico5

bases = ['Line', 'Sine1', 'Gauss', 'Circle']

metodo1 = 'V12_HOM_OnlineBagging_DDM'
metodo2 = 'V13_HOM_OnlineBagging_DDM'
base = 'Line'

for base in bases:
    grafico5.executa(base, metodo1, 'Fixed_Initialization', metodo2, 'Random_Initialization')



        

