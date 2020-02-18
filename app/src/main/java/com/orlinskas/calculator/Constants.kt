package com.orlinskas.calculator

class Constants

enum class Steps(val lenght: Int) {
    A(10),
    B(15),
    C(20),
    D(25),
    E(30)
}

enum class Isolation(val type: String) {
    Optimal("opt"),
    Econom("eco")
}

enum class Regulation(val isHas: Boolean){
    Yes(true),
    No(false)
}