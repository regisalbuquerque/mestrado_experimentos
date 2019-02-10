import grafico6

metodos = ['V12_HOM_LeverageBagging_ADWINChangeDetector', 'V13_HOM_LeverageBagging_ADWINChangeDetector']
bases = ['Line', 'Sine1', 'Gauss', 'Circle']

metodo1 = 'V12_HOM_LeverageBagging_ADWINChangeDetector'
metodo2 = 'V13_HOM_LeverageBagging_ADWINChangeDetector'
base = 'Line'
grafico6.executa(base, metodo1, 'Fixed_Initialization', metodo2, 'Random_Initialization')
        

