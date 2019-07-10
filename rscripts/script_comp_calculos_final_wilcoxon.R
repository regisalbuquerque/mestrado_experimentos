  
  # Calculo de WILCOXON - Teste estatistico

  TAM <- 30
  LIM_P <- 0.05
  
  basess = c('AgrawalAbrupt',
             'AgrawalAbruptNoise',
             'AgrawalGradual',
             'AgrawalGradualNoise',
             'Gauss',
             'SEAAbrupt',
             'SEAAbruptNoise',
             'SEAGradual',
             'SEAGradualNoise',
             'Sine1')
  
  basess_names = c('Agrawal Abrupt',
                   'Agrawal Abrupt Noise',
                   'Agrawal Gradual',
                   'Agrawal Gradual Noise',
                   'Gauss',
                   'SEA Abrupt',
                   'SEA Abrupt Noise',
                   'SEA Gradual',
                   'SEA Gradual Noise',
                   'Sine1')
  
  basesr       = c('ForestCovertype',
                   'KDDCup99',
                   'PokerHand', 
                   'Spam')
  
  basesr_names = c('Forest Covertype',
                   'KDDCup',
                   'Poker-Hand', 
                   'Spam')
  
  metodo_all = c("V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning",
                 "DDD_Original",
                 "DDM_Original", 
                 "LB_Original")
  
  PATH_TEMPO <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/"
  
  #CONFIG SINTETICAS
  metodo = metodo_all
  bases <- basess
  conta_estrelas_num <- 0 # 1 para sinteticas e 0 para real
  bases_names <- basess_names
  PATH <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/";
  

  getTaxasMedias <- function(base, metodo){
    iteracao <- 1
    #if (metodo == "V14_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning")
    #{
      #Procurar a melhor itera????o dentro dos resultados
      #file = paste0(PATH, "RESULT_", metodo, "_", base)
      #tabela <- read.table(file, header=T, sep=",")
      #tabela_ordenada = tabela[order(tabela$taxa_media, decreasing = TRUE),c(1,2,3)]
      #iteracao <- tabela_ordenada$execucao[1]
    #}
    
    file = paste0(PATH, metodo, "_", base, "_pareto__exec_",iteracao,"_drift.csv")
    #print(file)
    tabela <- read.table(file, header=T, sep=",")
    taxas <- unlist(tabela['taxa'])
    return(taxas)
  }
  
  getTempos <- function(base, metodo){
    if (metodo == "DESDD")
    {
      metodo <- "V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning"
    }
    file = paste0(PATH_TEMPO, "RESULT_", metodo, "_", base)
    tabela <- read.table(file, header=T, sep=",")
    taxas <- unlist(tabela['tempo'])
    return(taxas)
  }
  
  imprime <- function(bases_names, metodo, vencedor_index, medias, desvios, tempos, conta_estrelas){
    #Imprime
    cat("\n", bases_names[b], " & " )
    for(it in 1:length(metodo))
    {
      if (vencedor_index == it)
      {
        cat('\\textbf{')
        cat(round(medias[it], digits = 2))
        cat('}')
        for(ite in 1:conta_estrelas)
        {
          if (ite > conta_estrelas_num)
          {
            cat('*')
          }
        }
      } else {
        cat(round(medias[it], digits = 2))
      }
      cat(" (", round(desvios[it], digits = 2), ") & ", round(tempos[it], digits = 2))
      if (it != length(metodo))
      {
        cat(" & ")
      }
    }
    cat(' \\\\ \\hline ')
    #cat("\n", pvalues, " \n ", valores, " \n ")
  }
  
  imprime_medias <- function(bases_names, metodo, medias){
    #Imprime
    cat("\n", bases[b], "\t" )
    for(it in 1:length(metodo))
    {
      cat(medias[it]/100)
      if (it != length(metodo))
      {
        cat("\t")
      }
    }
  }
  
  imprime_taxas <- function(bases_names, metodo, taxas_part){
    #Imprime
    cat("\n", bases[b], "\t" )
    for(it in 1:length(metodo))
    {
      cat("\n", metodo[it], "\n")
      cat(taxas_part[[it]])
      cat("\n")
    }
  }
  
  for(b in 1:length(bases))
  {
  
    DATASET <- bases[b]
    
    taxas_part <- list()
    taxas <- list()
    
    tempos <- c()
    medias <- c()
    desvios <- c()
    
    for(it in 1:length(metodo))
    {
      taxas_aux <- getTaxasMedias(DATASET, metodo[it])
      taxas <- c(taxas, list(taxas_aux))
      taxas_part <- c(taxas_part, list(c()))
      
      tempo_aux <- getTempos(DATASET, metodo[it])
      tempos <- c(tempos, tempo_aux)
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
      medias <- c(medias, mean(taxas_part[[it]]))
      desvios <- c(desvios, sd(taxas_part[[it]]))
    }
    
    # Descobrir quem venceu
    vencedor_index = 1
    vencedor_media = medias[1]
    
    if (length(metodo) >=2 )
    {
      for(it in 2:length(metodo))
      {
        if (medias[it] > vencedor_media)
        {
          vencedor_index = it
          vencedor_media = medias[it]
        }
      }
    }
    
    pvalues <- c()
    valores <- c()
    conta_estrelas <- 0
    
    for(it in 1:length(metodo))
    {
      wiltest = wilcox.test(taxas_part[[vencedor_index]], taxas_part[[it]])
      pvalues <- c(pvalues, wiltest$p.value)
      valor = FALSE
      if (wiltest$p.value < LIM_P )
      {
        valor = TRUE
        conta_estrelas <- conta_estrelas + 1
      }
      valores <- c(valores, valor)
    }
    
    #imprime(bases_names, metodo, vencedor_index, medias, desvios, tempos, conta_estrelas)
    imprime_medias(bases_names, metodo, medias)
    #imprime_taxas(bases_names, metodo, taxas_part)
    
  }

