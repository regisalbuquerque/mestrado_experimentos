  taxas_DESDD_v12_part <- c() 
  taxas_DESDD_v14_part <- c() 
  taxas_DDM_part <- c() 
  taxas_LB_part <- c() 
 
  DESDD_v12 <- "V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning"
  DESDD_v14 <- "V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning"
  DDM <- "DDM_Original"
  LB <- "LB_Original"
  
  TAM <- 30
  
  DATASET <- "Sine1"
  
  getTaxasMedias <- function(base, metodo){
    path <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_v14_LB_DDM__Online_DDM_BufferAndReset__sinteticas/";
    tabela <- read.table(paste0(path, metodo, "_", base, "_pareto__exec_1_drift.csv"), header=T, sep=",")
    taxas <- unlist(tabela['taxa'])
    return(taxas)
  }
  
  taxas_DESDD_v12 <- getTaxasMedias(DATASET, DESDD_v12)
  taxas_DESDD_v14 <- getTaxasMedias(DATASET, DESDD_v14)
  taxas_DDM <- getTaxasMedias(DATASET, DDM)
  taxas_LB <- getTaxasMedias(DATASET, LB)
  particao = length(taxas_DESDD)/TAM
  particao = as.integer(particao)
  
  for (it in 1:TAM)
  {
     index <- particao*it
     print(paste0("Particao: ", index))  
     taxas_DESDD_v12_part <- c(taxas_DESDD_v12_part, taxas_DESDD_v12[index])
     taxas_DESDD_v14_part <- c(taxas_DESDD_v14_part, taxas_DESDD_v14[index])
     taxas_DDM_part <- c(taxas_DDM_part, taxas_DDM[index])
     taxas_LB_part <- c(taxas_LB_part, taxas_LB[index])
  }
  
  mean(taxas_DESDD_v12_part)
  sd(taxas_DESDD_v12_part)
  
  mean(taxas_DESDD_v14_part)
  sd(taxas_DESDD_v14_part)
  
  mean(taxas_DDM_part)
  sd(taxas_DDM_part)
  
  mean(taxas_LB_part)
  sd(taxas_LB_part)
  
  wilcox.test(taxas_DESDD_v12_part, taxas_DDM_part)
  wilcox.test(taxas_DESDD_v12_part, taxas_LB_part)
  wilcox.test(taxas_DDM_part, taxas_LB_part)
  




