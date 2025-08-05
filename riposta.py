import pandas as pd
import random

# Importa i CSV
magazzino = pd.read_csv("datiMagazzino.csv", sep=";")
negozio = pd.read_csv("datiNegozio.csv", sep=";")
riferita = pd.read_csv("datiRiferita.csv", sep=";")


# Imposta l'opzione per visualizzare tutte le colonne
# pd.set_option('display.max_columns', None)
# pd.set_option('display.max_rows', None)


# Calcola il conteggio delle copie per ciascun ISBN
# conteggio_isbn = riferita.groupby(['isbn']).size().reset_index(name='conteggioCopieLibro')
# print(conteggio_isbn)

# raggruppa per isbn e mostra il primo idcopia
isbn_idcopia = riferita.groupby(['isbn']).agg({'idcopia': 'first'}).reset_index()
# print(isbn_idcopia)

# Salva le prime idcopie in una variabile
prime_idcopie = isbn_idcopia['idcopia']
# print(prime_idcopie)

# Calcola i pesi per i negozi
pesi_negozi = [0.1] * len(negozio)

# Aggiungi due colonne con nomi casuali dei codiceidentifactivo di negozio e li associo alle copie
negozi_casuali = random.choices(negozio['codiceidentificativo'], weights=pesi_negozi, k=len(prime_idcopie))

negozi_copia = pd.DataFrame({
    'codiceidentificativonegozio': negozi_casuali,
    'idcopia':prime_idcopie
})

# print(negozi_copia)

"""
# conto i libri che il negozio ha
conteggio_negozio = negozi_copia.groupby(['codiceidentificativonegozio']).size().reset_index(name='conteggioNegozio')
print(conteggio_negozio)


# media di libri per negozio
media_negozio = conteggio_negozio['conteggioNegozio'].mean()
print(media_negozio)
"""

# Esegui un'operazione di unione tra riferita e negoziCopia basata sull'idcopia
merged_df = pd.merge(riferita, negozi_copia, on='idcopia', how='left', indicator=True)

# Seleziona solo le righe che sono presenti solo in riferita (cioè non presenti in negoziCopia)
copie_isbn = merged_df[merged_df['_merge'] == 'left_only']

# Supponiamo che le colonne da rimuovere siano 'codiceidentificativonegozio' '_merge' e 'isbn'
copie = copie_isbn.drop(['codiceidentificativonegozio', '_merge', 'isbn'], axis=1)

# print(copie)

# Calcola i pesi per i magazzini
pesi_magazzino = [1] * len(magazzino)

# creo una colonna con i magazzini messi casualmente
magazzino_casuali = random.choices(magazzino['codiceidentificativo'], weights=pesi_magazzino, k=len(copie))
# print(magazzino_casuali)

# creo il data frame a cui associo le copie e i magazzini

magazzino_copia = pd.DataFrame({
    'codiceidentificativomagazzino': magazzino_casuali,
    'idcopia': copie['idcopia']
})

# print(magazzino_copia)

"""
# conto le copie per magazzino
conteggio_magazzino = magazzino_copia.groupby(['codiceidentificativomagazzino']).size().reset_index(name='conteggioMagazzino')
print(conteggio_magazzino)

# media di libri per magazzino
media_magazzino = conteggio_magazzino['conteggioMagazzino'].mean()
print(media_magazzino)
"""

# Unione dei due DataFrame basata sull'idcopia
merged_final = pd.merge(magazzino_copia, negozi_copia, on='idcopia', how='outer')

# Sostituisci i valori mancanti con -1
merged_final.fillna(-1, inplace=True)

#print(merged_final)

# Converte le colonne contenenti numeri decimali in interi
merged_final['codiceidentificativomagazzino'] = merged_final['codiceidentificativomagazzino'].astype(int)
merged_final['idcopia'] = merged_final['idcopia'].astype(int)
merged_final['codiceidentificativonegozio'] = merged_final['codiceidentificativonegozio'].astype(int)

# Salva il DataFrame come file CSV
merged_final.to_csv('datiRiposta.csv', index=False, sep=";")
