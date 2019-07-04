  
  # Calculo de WILCOXON - Teste estatistico

  PATH <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_v14_LB_DDM_DDD__Online_DDM_BufferAndReset__sinteticas/";
  #PATH <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/";
  
  TAM <- 30
  LIM_P <- 0.05
  
  #"V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning"
  
  metodo_all = c("V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning",
                 "V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning", 
                 "DDM_Original", 
                 "LB_Original",
                 "DDD_Original")

  metodo = metodo_all
  
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
  
  bases <- basess

  getTaxasMedias <- function(base, metodo){
    file = paste0(PATH, metodo, "_", base, "_pareto__exec_1_drift.csv")
    tabela <- read.table(file, header=T, sep=",")
    taxas <- unlist(tabela['taxa'])
    return(taxas)
  }
  
 
  
  for(b in 1:length(bases))
  {
  
    DATASET <- bases[b]
    
    taxas_part <- list()
    taxas <- list()
    
    medias <- c()
    desvios <- c()
    
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
       #print(paste0("Particao: ", index))  
       
       for(itm in 1:length(metodo))
       {
         taxas_part[[itm]] <- c(taxas_part[[itm]], taxas[[itm]][index])
       }
    }
    
    for(it in 1:length(metodo))
    {
      medias <- c(medias, mean(taxas_part[[it]]))
      desvios <- c(desvios, sd(taxas_part[[it]]))
    }
    
    # Descobrir quem venceu
    vencedor_index = 1
    vencedor_media = medias[1]
    
    for(it in 2:length(metodo))
    {
      if (medias[it] > vencedor_media)
      {
        vencedor_index = it
        vencedor_media = medias[it]
      }
    }
    
    pvalues <- c()
    valores <- c()
    
    for(it in 1:length(metodo))
    {
      wiltest = wilcox.test(taxas_part[[vencedor_index]], taxas_part[[it]])
      pvalues <- c(pvalues, wiltest$p.value)
      valor = FALSE
      if (wiltest$p.value < LIM_P )
        valor = TRUE
      valores <- c(valores, valor)
    }
    cat("\n", "\n", "--------------------------------------", "\n", DATASET, "\n", metodo, "\n" ,medias, "\n", desvios, "\n", pvalues, "\n", valores)
  }



