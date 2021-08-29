import json

import pandas as pd

pd.options.display.width = 0


def convertNewsletter(newsletter: str) -> bool:
    if newsletter == 'nein':
        return False
    if newsletter == 'ja':
        return True


df: pd.DataFrame = pd.read_csv(filepath_or_buffer='./Adressen.csv',
                               sep=";",
                               encoding='ISO-8859-1',
                               quotechar="'")
df = df.rename(
    columns={'Nr.': 'id',
             'Vorname': 'firstName',
             'Nachname': 'lastName',
             'Geburtsdatum': 'birthday',
             'Straße': 'street',
             'Hausnummer': 'houseNumber',
             'Postleitzahl': 'postalCode',
             'Stadt': 'city',
             'Telefon': 'phone',
             'Mobil': 'mobile',
             'EMail': 'email',
             'Newsletter': 'newsletter'})
df['newsletter'] = df['newsletter'].apply(convertNewsletter)
df = df.where(pd.notnull(df), None)

with open(file='addresses.json', mode='w') as f:
    f.write(json.dumps(df.to_dict(orient='records')))
