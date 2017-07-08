package evolution.common;



import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Infant on 08.07.2017.
 */

@AllArgsConstructor @Getter
public enum PublicationCategory {

    IT(1),
    Technics(2),
    Sport(3),
    Machine_and_Technics(4);

    private final long id;
}