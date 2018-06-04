# Ler os dados 

  medias.line.DESDD <- medias.sine1.DESDD <- medias.gauss.DESDD <- medias.circle.DESDD <- medias.spam.DESDD <- medias.elec.DESDD <- medias.kdd.DESDD <- c()
  medias.line.DDM   <- medias.sine1.DDM   <- medias.gauss.DDM   <- medias.circle.DDM   <- medias.spam.DDM   <- medias.elec.DDM   <- medias.kdd.DDM   <- c()
  medias.line.LB    <- medias.sine1.LB    <- medias.gauss.LB    <- medias.circle.LB    <- medias.spam.LB    <- medias.elec.LB    <- medias.kdd.LB    <- c()
  
  getTaxasMedias <- function(base, seed){
    tabela <- read.table(paste0("~/Documents/git/regis/mestrado/implementacoes/experimentos/resultados/metodos/comparacao/", base, "__seed_", seed ,"___resumo.csv"), header=T, sep=",")
    taxas <- unlist(tabela['taxa_media'])
    return(taxas)
  }
  
  # Para cada seed
  for (seed in 1:10)
  {
    # BASE LINE
    line_taxas <- getTaxasMedias("Line", seed)
    medias.line.DESDD <- c(medias.line.DESDD, line_taxas[2])
    medias.line.DDM <- c(medias.line.DDM, line_taxas[4])
    medias.line.LB <- c(medias.line.LB, line_taxas[5])
    
    # BASE SINE1
    line_taxas <- getTaxasMedias("Sine1", seed)
    medias.sine1.DESDD <- c(medias.sine1.DESDD, line_taxas[2])
    medias.sine1.DDM <- c(medias.sine1.DDM, line_taxas[4])
    medias.sine1.LB <- c(medias.sine1.LB, line_taxas[5])
    
    # BASE GAUSS
    line_taxas <- getTaxasMedias("Gauss", seed)
    medias.gauss.DESDD <- c(medias.gauss.DESDD, line_taxas[3]) #DESDD_LB_SR = 3
    medias.gauss.DDM <- c(medias.gauss.DDM, line_taxas[4])
    medias.gauss.LB <- c(medias.gauss.LB, line_taxas[5])
    
    # BASE CIRCLE
    line_taxas <- getTaxasMedias("Circle", seed)
    medias.circle.DESDD <- c(medias.circle.DESDD, line_taxas[1]) #DESDD_SR = 1
    medias.circle.DDM <- c(medias.circle.DDM, line_taxas[4])
    medias.circle.LB <- c(medias.circle.LB, line_taxas[5])
    
    # BASE SPAM
    #line_taxas <- getTaxasMedias("Spam", seed)
    medias.spam.DESDD <- c(medias.spam.DESDD, line_taxas[1])
    medias.spam.DDM <- c(medias.spam.DDM, line_taxas[2])
    medias.spam.LB <- c(medias.spam.LB, line_taxas[3])
    
    # BASE ELEC
    #line_taxas <- getTaxasMedias("Elec", seed)
    medias.elec.DESDD <- c(medias.elec.DESDD, line_taxas[1])
    medias.elec.DDM <- c(medias.elec.DDM, line_taxas[2])
    medias.elec.LB <- c(medias.elec.LB, line_taxas[3])
    
    # BASE KDD
    #line_taxas <- getTaxasMedias("KDDCup99", seed)
    medias.kdd.DESDD <- c(medias.kdd.DESDD, line_taxas[1])
    medias.kdd.DDM <- c(medias.kdd.DDM, line_taxas[2])
    medias.kdd.LB <- c(medias.kdd.LB, line_taxas[3])
  }
  
# Calcular as médias

  medias_DESDD = c(mean(medias.line.DESDD), mean(medias.sine1.DESDD), mean(medias.gauss.DESDD), mean(medias.circle.DESDD), mean(medias.spam.DESDD), mean(medias.elec.DESDD), mean(medias.kdd.DESDD))
  medias_DDM = c(mean(medias.line.DDM), mean(medias.sine1.DDM), mean(medias.gauss.DDM), mean(medias.circle.DDM), mean(medias.spam.DDM), mean(medias.elec.DDM), mean(medias.kdd.DDM))
  medias_LB = c(mean(medias.line.LB), mean(medias.sine1.LB), mean(medias.gauss.LB), mean(medias.circle.LB), mean(medias.spam.LB), mean(medias.elec.LB), mean(medias.kdd.LB))  
  

# Realizar o teste estatístico

lista = list(medias_DESDD, medias_DDM, medias_LB)

combinados = c(medias_DESDD, medias_DDM, medias_LB)
y <- matrix(combinados ,nrow=7, ncol=3, 
  dimnames=list(1:7,c("DESDD","DDM","LB")))

friedman.test(y)
posthoc.friedman.nemenyi.test(y)

posthoc.kruskal.nemenyi.test(lista)
posthoc.friedman.nemenyi.test(y)

wilcox.test(medias.line.DESDD, medias.line.DDM)
wilcox.test(medias.line.DESDD, medias.line.LB)

wilcox.test(medias.sine1.DESDD, medias.sine1.DDM)
wilcox.test(medias.sine1.DESDD, medias.sine1.LB)

wilcox.test(medias.gauss.DESDD, medias.gauss.DDM)
wilcox.test(medias.gauss.DESDD, medias.gauss.LB)

wilcox.test(medias.circle.DESDD, medias.circle.DDM)
wilcox.test(medias.circle.DESDD, medias.circle.LB)

wilcox.test(medias.spam.DESDD, medias.spam.DDM)
wilcox.test(medias.spam.DESDD, medias.spam.LB)

wilcox.test(medias.elec.DESDD, medias.elec.DDM)
wilcox.test(medias.elec.DESDD, medias.elec.LB)

wilcox.test(medias.kdd.DESDD, medias.kdd.DDM)
wilcox.test(medias.kdd.DESDD, medias.kdd.LB)





