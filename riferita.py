import pandas as pd
import random

# Caricamento dei dati dalla tabella dei libri
copia = pd.read_csv("datiCopia.csv", sep=";")
libro = pd.read_csv("datiLibro.csv", sep=",")

# Aggiungi una colonna con nomi casuali dei ISBN
isbn_casuali = random.choices(libro['isbn'], k=len(copia))
riferita  = pd.DataFrame({'idcopia': copia['idcopia'], 'isbn': isbn_casuali})

# Salvataggio dei dati in un nuovo file CSV
riferita.to_csv("datiRiferita.csv", index=False, sep=";")
