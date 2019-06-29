  
  # Calculo de WILCOXON - Teste estatistico

  PATH <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_v14_LB_DDM__Online_DDM_BufferAndReset__sinteticas/";
  
  TAM <- 30
  
  metodo = c("V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning", 
             "V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning",
             "DDM_Original", 
             "LB_Original")
  basess = c('Sine1',
             'Gauss',
             'Circle',
             'AgrawalAbrupt',
             'AgrawalAbruptNoise',
             'AgrawalGradual',
             'AgrawalGradualNoise',
             'SEAAbrupt',
             'SEAAbruptNoise',
             'SEAGradual',
             'SEAGradualNoise')
  basesr = c('PokerHand',
             'ForestCovertype', 
             'Spam',
             'KDDCup99')

  taxas_part <- list()
  taxas <- list()
  
  getTaxasMedias <- function(base, metodo){
    file = paste0(PATH, metodo, "_", base, "_pareto__exec_1_drift.csv")
    tabela <- read.table(file, header=T, sep=",")
    taxas <- unlist(tabela['taxa'])
    return(taxas)
  }
  DATASET <- "Sine1"
  
  for(it in 1:length(metodo))
  {
    taxas_aux <- getTaxasMedias(DATASET, metodo[it])
    taxas <- c(taxas, list(taxas_aux))
    taxas_part <- c(taxas_part, list(c()))
  }

  particao = length(taxas[[1]])/TAM
  particao = as.integer(particao)
  
  for (it in 1:TAM)
  {
     index <- particao*it
     print(paste0("Particao: ", index))  
     
     for(itm in 1:length(metodo))
     {
       taxas_part[[itm]] <- c(taxas_part[[itm]], taxas[[itm]][index])
     }
  }
  
  for(it in 1:length(metodo))
  {
    print(mean(taxas_part[[it]]))
    print(sd(taxas_part[[it]]))
  }
  

  #wilcox.test(taxas_DESDD_v12_part, taxas_DDM_part)
  #wilcox.test(taxas_DESDD_v12_part, taxas_LB_part)
  #wilcox.test(taxas_DDM_part, taxas_LB_part)
  




