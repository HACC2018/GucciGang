import pyrebase
import pandas as pd
import numpy as np

config = {
    "yes"
  }

def main():
  plants_data_csv = pd.read_csv("native_species.csv")
  plant_name = np.asarray(plants_data_csv['NAME'])
  plant_family = np.asarray(plants_data_csv['FAMILY'])
  plant_common_name = np.asarray(plants_data_csv['COMMON_NAME'])
  plant_conservation_status = np.asarray(plants_data_csv['CONSERVATION_STATUS'])
  plant_native_status = np.asarray(plants_data_csv['NATIVE_STATUS'])

  firebase = pyrebase.initialize_app(config)
  database = firebase.database()

  for i in range(len(plant_name)):
      database.child(plant_common_name[i]).push(
        {
        "name": plant_name[i],
        "family": plant_family[i],
        "common_name": plant_common_name[i],
        "conservation_status": plant_conservation_status[i],
        "native_status": plant_native_status[i]
        }
      )
    
if __name__ == '__main__':
    main()