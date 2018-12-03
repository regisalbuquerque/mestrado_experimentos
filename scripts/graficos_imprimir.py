import grafico1 
import grafico2
import grafico3
import grafico4

metodos = ['V12', 'V14']
bases = ['Line', 'Sine1', 'Gauss', 'Circle']

for metodo in metodos:
    for base in bases:
        print(metodo + ' - ' + base)
        grafico1.executa(base, metodo)
        grafico2.executa(base, metodo)
        grafico3.executa(base, metodo)
        grafico4.executa(base, metodo)
        

