import java.util.Arrays;
import java.util.Objects;

/**
 * Die Klasse User beschreibt, die Eigenschaften eines Benutzers
 */

public class User {
    /**
     * Die ID eines Users. Diese darf nicht leer sein.
     */
    String userId;
    /**
     * Das Passwort eines Users. Darf ebenfalls nicht leer sein.
     */
    char[] password;

    User() {
        userId = "";
        String str = "changeMe!";
        password = str.toCharArray();
    }

    User(String userId, char[] password) {
        this.userId = userId;
        this.password = password;
    }

    /**
     * <p>Die equals Methode soll ueberpruefen, ob zwei Benutzer gleich sind.</p>
     * @param o der Benutzer mit dem auf Gleichheit geprueft wird.
     * @return true, wenn die beiden User gleich sind, sonst false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Arrays.equals(password, user.password);
    }

    /**
     * <p>Die toString Methode gibt die Benutzerdaten als String zurueck</p>
     * @return Benutzer mit userID und password als String
     */
    @Override
    public String toString() {
        return "Benutzer{" +
                "userId='" + userId + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
