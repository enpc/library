db.createUser(
  {
    user: "usr",
    pwd: "usr",
    roles: [ { role: "readWrite", db: "library" } ]
  }
)