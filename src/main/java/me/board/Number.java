package me.board;

public enum Number
{
    Zero(0), One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9),
    PlusTwo(20),
    Reverse(-1),
    Blocked(10),
    Joker(11),
    PlusFour(40);

    private int number;

    Number(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }
}
