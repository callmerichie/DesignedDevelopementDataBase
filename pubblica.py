import pandas as pd
import random

# Leggi i dati della casa editrice da un file CSV
editrice = pd.read_csv("DatiDB/datiCasaEditrice.csv", sep=";")
libro = pd.read_csv("DatiDB/datiLibro.csv", sep=",")

# Aggiungi una colonna con nomi casuali delle case editrici per gli ISBN
editrice_casuali = random.choices(editrice['nome'], k=len(libro))
pubblica = pd.DataFrame({'isbn': libro['isbn'], 'editrice_casuali': editrice_casuali})

# Salva i dati in un file CSV
pubblica.to_csv("datiPubblica.csv", index=False, sep=";")
