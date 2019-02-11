import grafico5
import grafico6

bases = ['Line', 'Sine1', 'Gauss', 'Circle']

metodo1 = 'V12_HOM_LeverageBagging_ADWINChangeDetector'
metodo2 = 'V13_HOM_LeverageBagging_ADWINChangeDetector'
base = 'Line'

for base in bases:
    grafico5.executa(base, metodo1, 'Fixed_Initialization', metodo2, 'Random_Initialization')
    grafico6.executa(base, metodo1, 'Fixed_Initialization', metodo2, 'Random_Initialization')


        

