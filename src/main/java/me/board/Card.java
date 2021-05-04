package me.board;

/**
 * Class representing a Uno card
 */
public class Card
{
    /**
     * Number of the card
     * @see Number
     */
    private Number number;

    /**
     * Color of the card
     * @see Color
     */
    private Color color;

    /**
     * Constructor
     * @param number The number of the card
     * @param color The color of the card
     */
    public Card(Number number, Color color)
    {
        this.number = number;
        this.color = color;
    }

    /**
     * Getter for number attribute
     * @return number attribute
     */
    public Number getNumber()
    {
        return number;
    }

    /**
     * Getter for color attribute
     * @return color attribute
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Url (in String format) of the card's image
     * @return Url of the card's image
     */
    public String getURL()
    {
        switch (getColor())
        {
            case Black:
                switch (getNumber())
                {
                    case Joker:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215278052605972/Changement_couleur.png";

                    case PlusFour:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215349095858368/Plus4.png";

                    default:
                        System.out.println("ERREUR BLACK CARD");
                        return "";
                }

            case Red:
                switch (getNumber())
                {
                    case Zero:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215354317766736/R_0.png";

                    case One:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215358331584542/R_1.png";

                    case Two:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215362899050606/R_2.png";

                    case Three:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215366464340098/R_3.png";

                    case Four:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215369463267399/R_4.png";

                    case Five:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215372462325780/R_5.png";

                    case Six:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215375486419025/R_6.png";

                    case Seven:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215378996789288/R_7.png";

                    case Eight:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215382281060433/R_8.png";

                    case Nine:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215385745555556/R_9.png";

                    case Blocked:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215389742596176/R_interdit.png";

                    case PlusTwo:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215392464699432/R_plus2.png";

                    case Reverse:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215411267895376/R_sens.png";

                    default:
                        System.out.println("ERROR RED CARTE");
                        return "";
                }

            case Blue:
                switch (getNumber())
                {
                    case Zero:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215227578220584/B_0.png";

                    case One:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215231395037294/B_1.png";

                    case Two:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215233836384266/B_2.png";

                    case Three:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215235312517190/B_3.png";

                    case Four:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215236470145094/B_4.png";

                    case Five:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215237472845965/B_5.png";

                    case Six:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215239465140301/B_6.png";

                    case Seven:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215240622637097/B_7.png";

                    case Eight:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215241474211860/B_8.png";

                    case Nine:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215243202134056/B_9.png";

                    case Blocked:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715274477490929704/B_interdit.png";

                    case Reverse:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215271031210075/B_sens.png";

                    case PlusTwo:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715274488283004998/B_plus2.png";

                    default:
                        System.out.println("ERROR RED CARTE");
                        return "";
                }

            case Green:
                switch (getNumber())
                {
                    case Zero:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215419446657074/V_0.png";

                    case One:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215423339102318/V_1.png";

                    case Two:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215426778562620/V_2.png";

                    case Three:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215429995593758/V_3.png";

                    case Four:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215433636249701/V_4.png";

                    case Five:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215436651954186/V_5.png";

                    case Six:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215441223483429/V_6.png";

                    case Seven:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215446164373504/V_7.png";

                    case Eight:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215450526711868/V_8.png";

                    case Nine:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215455362744340/V_9.png";

                    case Blocked:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215460752162936/V_interdit.png";

                    case PlusTwo:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215465844047952/V_plus2.png";

                    case Reverse:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215471024275597/V_sens.png";

                    default:
                        System.out.println("ERROR GREEN CARD");
                }

            case Yellow:
                switch (getNumber())
                {
                    case Zero:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215283018530816/J_0.png";

                    case One:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215287103914005/J_1.png";

                    case Two:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215291013136464/J_2.png";

                    case Three:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215294649466950/J_3.png";

                    case Four:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215298374140015/J_4.png";

                    case Five:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215301528256560/J_5.png";

                    case Six:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215304879505468/J_6.png";

                    case Seven:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215308205326376/J_7.png";

                    case Eight:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215319656038431/J_8.png";

                    case Nine:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215325771202650/J_9.png";

                    case Blocked:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215330145861675/J_interdit.png";

                    case PlusTwo:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215334247890944/J_plus2.png";

                    case Reverse:
                        return "https://cdn.discordapp.com/attachments/696012343418552320/715215343995453552/J_sens.png";

                    default:
                        System.out.println("ERROR YELLOW CARD");
                        return "";
                }

            default:
                System.out.println("ERROR CARD COLOR");
                return "";
        }
    }


    /**
     * @return
     */
    public String toString()
    {
        return "["+getNumber()+", "+getColor()+"]";
    }

}
