package com.tommy.wxzj
class Patient(ID: String, name: String, sex: String, birthday: String) {

    var ID: String
        internal set
    var name: String
        internal set
    var sex: String
        internal set
    var birthday: String
        internal set

    init {

        this.ID = ID
        this.name = name
        this.sex = sex
        this.birthday = birthday
    }
}