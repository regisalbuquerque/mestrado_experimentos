  taxas_DESDD_part <- c() 
  taxas_DDM_part <- c() 
  taxas_LB_part <- c() 
 
  DESDD <- "_comp_1__V12_RetreinaTodosComBufferWarning_Ambiguidade_DDM_iteracao"
  DDM <- "_comp_1__DDM_Original_iteracao"
  LB <- "_comp_1__LB_Original_iteracao"
  
  TAM <- 30
  DATASET <- "Kdd"
  
  getTaxasMedias <- function(base, metodo){
    path <- "~/Documents/git/regis/mestrado/implementacoes/resultados/metodos/comparacao/";
    tabela <- read.table(paste0(path, base, metodo, ".csv"), header=T, sep=",")
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
  
  wilcox.test(taxas_DESDD_part, taxas_DDM_part)
  wilcox.test(taxas_DESDD_part, taxas_LB_part)
  wilcox.test(taxas_DDM_part, taxas_LB_part)
  




