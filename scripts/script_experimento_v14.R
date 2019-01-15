  taxas_DESDD_part <- c() 
  taxas_DDM_part <- c() 
  taxas_LB_part <- c() 
 
  DESDD_Online <- "RESULT_V14_HOM_OnlineBagging_DDM_"
  DESDD_LB <- "RESULT_V14_HOM_LeverageBagging_ADWINChangeDetector_"
  
  DATASET <- "KDDCup99"
  
  getDados <- function(base, metodo, coluna){
    path <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/v14_hom/";
    tabela <- read.table(paste0(path, metodo, base), header=T, sep=",")
    dados <- unlist(tabela[coluna])
    return(dados)
  }
  
  taxas_DESDD_Online <- getDados(DATASET, DESDD_Online, 'taxa_media')
  taxas_DESDD_LB <- getDados(DATASET, DESDD_LB, 'taxa_media')

  mean(taxas_DESDD_Online)
  sd(taxas_DESDD_Online)
  
  mean(taxas_DESDD_LB)
  sd(taxas_DESDD_LB)
  
  tempo_DESDD_Online <- getDados(DATASET, DESDD_Online, 'tempo')
  tempo_DESDD_LB <- getDados(DATASET, DESDD_LB, 'tempo')
  
  mean(tempo_DESDD_Online)
  sd(tempo_DESDD_Online)
  
  mean(tempo_DESDD_LB)
  sd(tempo_DESDD_LB)
  
  
  

