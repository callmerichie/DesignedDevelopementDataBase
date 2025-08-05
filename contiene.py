import pandas as pd

# File CSV contenenti i codici di posizione degli scaffali e dei ripiani
scaffali = 'datiScaffale.csv'
ripiani = 'datiRipiano.csv'
"""
# Leggi i dati dagli scaffali utilizzando un approccio personalizzato
with open(scaffali, 'r') as file:
    scaffali_lines = file.readlines()

scaffali_data = [line.strip() for line in scaffali_lines[1:]]  # Ignora l'intestazione e rimuovi i caratteri di newline

# Leggi i dati dai ripiani utilizzando un approccio personalizzato
with open(ripiani, 'r') as file:
    ripiani_lines = file.readlines()

ripiani_data = [line.strip() for line in ripiani_lines[1:]]  # Ignora l'intestazione e rimuovi i caratteri di newline

# Creazione dei DataFrame
scaffali_df = pd.DataFrame([line.split(',') for line in scaffali_data], columns=scaffali_lines[0].strip().split(','))
ripiani_df = pd.DataFrame([line.split(',') for line in ripiani_data], columns=ripiani_lines[0].strip().split(','))

#print(scaffali_df.head())
#print(ripiani_df.head())
"""
# Leggi i dati dagli scaffali utilizzando pandas
scaffali_df = pd.read_csv(scaffali)

# Leggi i dati dai ripiani utilizzando pandas
ripiani_df = pd.read_csv(ripiani)

# Ordina i ripiani per codice di posizione
ripiani_df.sort_values(by='idripiano', inplace=True)

# Associa i ripiani agli scaffali (per ogni 6 ripiani)
associazioni = []
for i in range(0, len(ripiani_df), 6):
    for j in range(6):
        associazioni.append((scaffali_df.iloc[i // 6]['codiceposizione'], ripiani_df.iloc[i + j]['idripiano']))


# Scrivi i dati delle associazioni in un nuovo file CSV
associazioni_df = pd.DataFrame(associazioni, columns=['codiceposizione', 'idripiano'])
associazioni_df.to_csv('datiContiene.csv', index=False)
