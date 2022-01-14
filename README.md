# MRI_EX3

Esercizio svolto da __Natasha Fabrizio__ Matricola: __717446__ e __Francesco Saverio Cassano__ Matricola: __716133__

## DESCRIZIONE SVOLGIMENTO ESERCIZIO

L'esercizio è stato realizzato prendendo come base ciò che è presente nella repo [Git Hub](https://github.com/pippokill/MRI_2021_22). <br>
E' stata creata la classe _Personal Analyzer_ , ereditando da "_StopwordAnalyzerBase_", che Tokenizza i termini e poi li filtra. Come __Tokenizzatore__, la classe utilizza il _LetterTokenizer_, mentre per quanto riguarda i __filtri__ utilizza _LowerCaseFilter_, _EnglishPossessiveFilter_, _RemoveDuplicateTokenFilter_, _KStemFilter_ ed infine _StopFilter_; quest'ultimo legge la lista delle stopword da file. 
<p>

Per la gestione delle _query_ nella classe __CranSearcher__ viene utilizzato il _PersonalAnalyzer_. Inoltre è stato aggiunto il metodo "_customQueryBoost_" per effettuare i boost dei termini rilevanti e l'eliminazione  dei caratteri non desiderati.
<p>
L'output del sistema si trova in "MRI_EX3\resources\cran", chiamato "result.out". 
Il risultato di "trec_eval" è il seguente:
  
```
runid                 	all	exp0
num_q                 	all	225
num_ret               	all	22500
num_rel               	all	1837
num_rel_ret           	all	1346
map                   	all	0.4662
gm_map                	all	0.3142
Rprec                 	all	0.4384
bpref                 	all	0.7735
recip_rank            	all	0.8803
iprec_at_recall_0.00  	all	0.8905
iprec_at_recall_0.10  	all	0.8664
iprec_at_recall_0.20  	all	0.7408
iprec_at_recall_0.30  	all	0.6334
iprec_at_recall_0.40  	all	0.5517
iprec_at_recall_0.50  	all	0.4811
iprec_at_recall_0.60  	all	0.3900
iprec_at_recall_0.70  	all	0.3227
iprec_at_recall_0.80  	all	0.2183
iprec_at_recall_0.90  	all	0.1510
iprec_at_recall_1.00  	all	0.1336
P_5                   	all	0.5004
P_10                  	all	0.3360
P_15                  	all	0.2596
P_20                  	all	0.2104
P_30                  	all	0.1547
P_100                 	all	0.0598
P_200                 	all	0.0299
P_500                 	all	0.0120
P_1000                	all	0.0060
```
  
---
Natasha Fabrizio - Francesco Saverio Cassano<br>
Esercizio 3 MRI 
