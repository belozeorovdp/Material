package mbcommon;

import java.io.Serializable;

/**
 * Shared core class MUST be serializable
 * Immutable
 *
 * @author hajo
 *
 */
public final class User implements Serializable {

    private static final long serialVersionUID = Constants.SERIAL_V_ID;

    private final String name;
    private final String passwd;

    public User(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

    @Override
    public String toString() {
        return "{" + name + " " + passwd + "}";
    }

    // Don't need to understand the below right now
    
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result + ((passwd == null) ? 0 : passwd.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (passwd == null) {
            if (other.passwd != null)
                return false;
        } else if (!passwd.equals(other.passwd))
            return false;
        return true;
    }

}
