require(graphics)

# Line   Y_DESDD_RB
# Sine1  Y_DESDD_RB
# Gauss  Y_DESDD_LB_RS
# Circle Y_DESDD_SR

# REAIS Y_DESDD_SR

metodoDESDD_SR <- "V12_SimpleReset_Ambiguidade_iteracao.csv"
metodoDESDD_RB <- "V12_RetreinaTodosComBufferWarning_Ambiguidade_iteracao.csv"
metodoDESDD_LB_SR <- "LB_SelectionV2SimpleResetSystem_Ambiguidade_iteracao.csv"

base <- "Sine1"
metodoDESDD <- metodoDESDD_RB

dadosDESDD <- read.table(paste0("~/Documents/git/regis/mestrado/implementacoes/experimentos/resultados/metodos/comparacao/", base, metodoDESDD), header=T, sep=",")
taxasDESDD <- unlist(dadosDESDD['taxa'])

dadosDDM <- read.table(paste0("~/Documents/git/regis/mestrado/implementacoes/experimentos/resultados/metodos/comparacao/",base, "DDM_Original_iteracao.csv"), header=T, sep=",")
taxasDDM <- unlist(dadosDDM['taxa'])

dadosLB <- read.table(paste0("~/Documents/git/regis/mestrado/implementacoes/experimentos/resultados/metodos/comparacao/",base,"LB_Original_iteracao.csv"), header=T, sep=",")
taxasLB <- unlist(dadosLB['taxa'])


combinados = c(taxasDESDD, taxasDDM, taxasLB)
y <- matrix(combinados ,nrow=10000, ncol=3, 
            dimnames=list(1:10000,c("DESDD","DDM","LB")))

friedman.test(y)
posthoc.friedman.nemenyi.test(y)


boxplot(taxasDESDD, taxasDDM)
boxplot(taxasDESDD, taxasLB)

wilcox.test(taxasDESDD, taxasDDM, paired = TRUE)
wilcox.test(taxasDESDD, taxasLB, paired = TRUE)



