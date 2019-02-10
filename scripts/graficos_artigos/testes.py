import pandas as pd
import matplotlib.pyplot as plt

drifts = {
           'Line':[1000], 
           'Sine1': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
           'Gauss': [1000,2000,3000,4000,5000,6000,7000,8000,9000], 
           'Circle': [1000,2000,3000],
           'Elec': [],
           'Spam': [],
           'KDDCup99': []
           }

limiteBase = {
        'Line': 2000, 
        'Sine1': 10000, 
        'Gauss': 10000, 
        'Circle': 4000,
        'Elec': -1,
        'Spam': -1,
        'KDDCup99': -1
        }

path_file = '/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/Teste_v12_v13/pareto/V12_HOM_LeverageBagging_ADWINChangeDetector/Line/V12_HOM_LeverageBagging_ADWINChangeDetector_Line_pareto__exec_1_it_'
ROOT_PATH_IMG = '/Users/regisalbuquerque/Desktop/'
base = 'Line'


fig, ax = plt.subplots()
plt.subplot(211)
     
X = range(1, limiteBase[base]+1)
    

#Cria os contadores
PRIMEIRA_IT = pd.read_csv(path_file + '1.csv')
PRIMEIRA_IT_ORD = PRIMEIRA_IT.sort_values('cod')
frequencias = [0]*len(PRIMEIRA_IT.index.values) #INICIALIZA OS CONTADORES DE FREQUENCIAS
lambdas_df = PRIMEIRA_IT_ORD['cod']
lambdas = []
for it in lambdas_df:
    lambdas.append(it)
    
escolhas_lambdas = []
    

# C O N T A G E M
for it in range(1, limiteBase[base]+1):
    RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
    X = RESULTADO.loc[RESULTADO['pareto_maior'] == True]
    lambda_escolhido = X['cod'].values[0]
    VENCEDOR = lambdas.index(lambda_escolhido)
    frequencias[VENCEDOR] = frequencias[VENCEDOR] + 1
    escolhas_lambdas.append(lambda_escolhido)
  
TOP1_FREQ = max(frequencias)    
TOP1_INDEX = frequencias.index(max(frequencias)) 
TOP1_LAMBDA = lambdas[TOP1_INDEX]  



DIVERSIDADES = []
for it in range(1, limiteBase[base]+1):
    RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
    LINHA = RESULTADO.loc[RESULTADO['cod'] == TOP1_LAMBDA]
    DIVERSIDADE = LINHA['diversidade'].values[0]
    DIVERSIDADES.append(DIVERSIDADE)



    
    
    
ORDENADO = PRIMEIRA_IT.sort_values('cod')    
ordem = ORDENADO.index.values
diversidades = ORDENADO['cod']
for it in ordem:
    diversidades_ord.append(round(diversidades[it], 6))
    

    
INDEX_TOP1 = frequencias.index(max(frequencias))
frequencias[INDEX_TOP1] = -1


DIVERSIDADES = []
for it in range(1, limiteBase[base]+1):
    RESULTADO = pd.read_csv(path_file + str(it) + '.csv')
    X = RESULTADO.loc[indice]
    DIVERSIDADE = X['diversidade']
    DIVERSIDADES.append(DIVERSIDADE)


Y_TOP1 = DIVERSIDADES




plt.plot(X_STEP, Y_TOP1_STEP, '-', label='FIRST', color='k', markersize=10)
X_TOP1_ESCOLHAS, Y_TOP1_ESCOLHAS = calcula_pontos_escolhidos(INDEX_TOP1, Y_TOP1, escolhas)
plt.plot(X_TOP1_ESCOLHAS, Y_TOP1_ESCOLHAS, 'x', label='FIRST(CHOOSEN)', color='k', markersize=20)


#plt.plot(X_STEP, Y_TOP2_STEP, ':', label='SECOND', color='b', markersize=10)
#plt.plot(X_STEP, Y_TOP3_STEP, '-', label='THIRD', color='r', markersize=10)

 # DRIFTS 
if baseEhReal[base] == False:
    for xc in drifts[base]:
        plt.axvline(x=xc)

axes = plt.gca()
#axes.set_xlim([-0.01, 0.52])
axes.set_ylim(range_div[base])

plt.xlabel('Iteration')
plt.ylabel('Diversity')
plt.title("Teste")
plt.legend()
plt.subplots_adjust(hspace=0.6)

#plt.show()
    
fig.set_figheight(8)
fig.set_figwidth(15)
fig.savefig(ROOT_PATH_IMG + 'grafico6_' + '_' + base + '.eps', format='eps', dpi=1200, bbox_inches='tight')