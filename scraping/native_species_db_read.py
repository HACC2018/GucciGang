import pyrebase

config = {
    "yes"
  }

def main():
  firebase = pyrebase.initialize_app(config)
  database = firebase.database()

  plants = database.child("native_plants/").get()
  for user in plants.each():
    print(user.val())

if __name__ == '__main__':
    main()