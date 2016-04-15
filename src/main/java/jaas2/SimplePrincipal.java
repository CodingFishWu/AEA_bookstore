package jaas2;


import java.security.Principal;
import java.util.Objects;

/**
 * Created by fish on 3/22/16.
 */
public class SimplePrincipal implements Principal {
    private String role;

    public SimplePrincipal() {

    }

    public SimplePrincipal(String role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return role;
    }

    public boolean equals(Object otherObject) {
        if(this== otherObject) return true;
        if (otherObject == null) return false;

        if (getClass() != otherObject.getClass()) return false;
        // stop here

        SimplePrincipal other = (SimplePrincipal) otherObject;

        return Objects.equals(getName(), other.getName());
    }

    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
