  taxas_DESDD_part <- c() 
  taxas_DDM_part <- c() 
  taxas_LB_part <- c() 
 
  DESDD <- "V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning"
  DDM <- "DDM_Original"
  LB <- "LB_Original"
  
  TAM <- 30
  DATASET <- "Sine1"
  
  getTaxasMedias <- function(base, metodo){
    path <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/";
    tabela <- read.table(paste0(path, metodo, "_", base, "_pareto__exec_1_drift.csv"), header=T, sep=",")
    taxas <- unlist(tabela['taxa'])
    return(taxas)
  }
  
  taxas_DESDD <- getTaxasMedias(DATASET, DESDD)
  taxas_DDM <- getTaxasMedias(DATASET, DDM)
  taxas_LB <- getTaxasMedias(DATASET, LB)
  particao = length(taxas_DESDD)/TAM
  particao = as.integer(particao)
  
  for (it in 1:TAM)
  {
     index <- particao*it
     print(paste0("Particao: ", index))  
     taxas_DESDD_part <- c(taxas_DESDD_part, taxas_DESDD[index])
     taxas_DDM_part <- c(taxas_DDM_part, taxas_DDM[index])
     taxas_LB_part <- c(taxas_LB_part, taxas_LB[index])
  }
  
  mean(taxas_DESDD_part)
  sd(taxas_DESDD_part)
  
  mean(taxas_DDM_part)
  sd(taxas_DDM_part)
  
  mean(taxas_LB_part)
  sd(taxas_LB_part)
  
  wilcox.test(taxas_DESDD_part, taxas_DDM_part)
  wilcox.test(taxas_DESDD_part, taxas_LB_part)
  wilcox.test(taxas_DDM_part, taxas_LB_part)
  




