import random
import pandas as pd

# importazione CSV
scaffali = pd.read_csv("datiScaffale.csv", sep=";")
negozi = pd.read_csv("datiNegozio.csv", sep=";")
magazzini = pd.read_csv("datiMagazzino.csv", sep=";")

# Calcola i pesi per i negozi e i magazzini
pesi_negozi = [0.5] * len(negozi)
pesi_magazzini = [3] * len(magazzini)

# Aggiungi due colonne con nomi casuali dei codiceidentifactico di negozio, magazzino e li associo agli scaffali
negozi_casuali = random.choices(negozi['codiceidentificativo'], weights=pesi_negozi, k=len(scaffali))
magazzini_casuali = random.choices(magazzini['codiceidentificativo'], weights=pesi_magazzini, k=len(scaffali))


possiede = pd.DataFrame({
    'codiceidentificativonegozio': negozi_casuali,
    'codiceidentificativomagazzino': magazzini_casuali,
    'codiceposizione': scaffali['codiceposizione']
})
possiede.to_csv("datiPossiede.csv", index=False, sep=";")

# Carica il file CSV
possiede = pd.read_csv("datiPossiede.csv", sep=";")

# Raggruppa i dati per codiceidentificativonegozio e codiceidentificativomagazzino, quindi conta le occorrenze
conteggioNegozio = possiede.groupby(['codiceidentificativonegozio']).size().reset_index(name='conteggioNegozio')
conteggioMagazzino = possiede.groupby(['codiceidentificativomagazzino']).size().reset_index(name='conteggioMagazzino')


# Stampa il conteggio
print(conteggioNegozio)
print(conteggioMagazzino)
