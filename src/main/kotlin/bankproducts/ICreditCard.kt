package bankproducts

interface ICreditCard: ICard {
    fun getDebt(): Double
}