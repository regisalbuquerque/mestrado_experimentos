  
  # Calculo de WILCOXON - Teste estatistico

  TAM <- 30
  LIM_P <- 0.05
  
  bases_sinteticas <- c('AgrawalAbrupt',
             'AgrawalAbruptNoise',
             'AgrawalGradual',
             'AgrawalGradualNoise',
             'Gauss',
             'SEAAbrupt',
             'SEAAbruptNoise',
             'SEAGradual',
             'SEAGradualNoise',
             'Sine1')
  
  bases_reais       <- c('ForestCovertype',
                        'KDDCup99',
                        'PokerHand', 
                        'Spam')
  
  bases_sinteticas_names <- c('Agrawal Abrupt',
                   'Agrawal Abrupt Noise',
                   'Agrawal Gradual',
                   'Agrawal Gradual Noise',
                   'Gauss',
                   'SEA Abrupt',
                   'SEA Abrupt Noise',
                   'SEA Gradual',
                   'SEA Gradual Noise',
                   'Sine1')
  
  bases_reais_names <- c('Forest Covertype',
                   'KDDCup',
                   'Poker-Hand', 
                   'Spam')
  
  bases <- c(bases_sinteticas, bases_reais)
  bases_names <- c(bases_sinteticas_names, bases_reais_names)
  
  metodo_desdd <- c("V12_HOM_OnlineBagging_DDM_RetreinaTodosComBufferWarning")
  baselines <- c("DDD_Original",
                 "DDM_Original", 
                 "LB_Original")
  
  metodos <- c(metodo_desdd, baselines)

  PATH_TEMPO <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/"
  PATH <- "/Users/regisalbuquerque/Documents/drive/regis/mestrado/resultados/comp_v12_LB_DDM_DDD__allbases/";

  #buscaDados <- function(metodos, bases)
  #{
    dataset_metodos <- c()
    dataset_bases <- c()
    dataset_taxas <- list()
    dataset_taxas_part <- list()
    dataset_medias_part <- c()
    dataset_sd_part <- c()
    
    for(base in bases)
    {
      for(metodo in metodos)
      {
        dataset_metodos <- c(dataset_metodos, metodo)
        dataset_bases <- c(dataset_bases, base)
        
        file = paste0(PATH, metodo, "_", base, "_pareto__exec_1_drift.csv")
        tabela <- read.table(file, header=T, sep=",")
        taxas <- unlist(tabela['taxa'])
        
        dataset_taxas <- c(dataset_taxas, list(taxas))
        
        #Calcula a particao
        particao = length(taxas)/TAM
        particao = as.integer(particao)
        taxas_part = c()
        for (it in 1:TAM)
        {
          index <- particao*it
          taxas_part <- c(taxas_part, taxas[index])
        }
        
        dataset_taxas_part <- c(dataset_taxas_part, list(taxas_part))
        
        dataset_medias_part <- c(dataset_medias_part, mean(taxas_part))
        dataset_sd_part <- c(dataset_sd_part, sd(taxas_part))
      }
    }
    
    
    #Imprime
    for(it in 1:length(dataset_metodos))
    {
      cat(dataset_metodos[it],",",dataset_bases[it],",")
      for(taxa in dataset_taxas_part[[it]])
      {
        cat(taxa/100, ",")
      }
      cat("\n")
    }
    
    
  #}
  
  #buscaDados(metodos,bases)
  

  
  

