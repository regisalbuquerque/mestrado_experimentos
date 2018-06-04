medias_DESDD = c(96.59, 93.13, 86.94, 95.81, 96.79, 92.06, 99.9730)
medias_DDM = c(96.19, 89.15, 80.74, 95.82, 91.00, 85.97, 99.9564)
medias_LB = c(95.93, 84.75, 76.54, 93.92, 94.35, 90.36, 99.9630)

lista = list(medias_DESDD, medias_DDM, medias_LB)

combinados = c(medias_DESDD, medias_DDM, medias_LB)
y <- matrix(combinados ,nrow=7, ncol=3, 
  dimnames=list(1:7,c("DESDD","DDM","LB")))

friedman.test(y)
posthoc.friedman.nemenyi.test(y)

posthoc.kruskal.nemenyi.test(lista)
posthoc.friedman.nemenyi.test(y)

wilcox.test(medias_DESDD, medias_DDM, paired = TRUE)
wilcox.test(medias_DESDD, medias_LB, paired = TRUE)

