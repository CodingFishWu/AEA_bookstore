package other;

import java.security.Permission;
import java.util.*;

/**
 * Created by fish on 3/25/16.
 */
public class MyPermission extends Permission{
    private String action;
    /**
     * Constructs a permission with the specified name.
     *
     * @param name name of the Permission object being created.
     */
    public MyPermission(String name, String anAction) {
        super(name);
        action = anAction;
    }

    @Override
    public boolean implies(Permission permission) {
        if (!(permission instanceof  MyPermission)) return false;
        MyPermission other = (MyPermission) permission;
        if (action.equals("allowed")) {
            if (other.action.equals("allowed")) {
                return badCategorySet().containsAll(other.badCategorySet());
            }
            else if (other.action.equals("forbidden")) {
                Iterator<String> iterator = badCategorySet().iterator();
                Set<String> otherSet = other.badCategorySet();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    if (otherSet.contains(next)) {
                        return false;
                    }
                }
                return true;
            }
        }
        else if (action.equals("forbidden")) {
            if (other.equals("forbidden")) {
                return other.badCategorySet().containsAll(badCategorySet());
            }
            else if (other.equals("allowed")) {
                Iterator<String> iterator = other.badCategorySet().iterator();
                Set<String> mySet = badCategorySet();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    if (mySet.contains(next)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!getClass().equals(obj.getClass())) return false;
        MyPermission other = (MyPermission)obj;
        if (!Objects.equals(action, other.action)) return false;
        if ("allowed".equals(action) || "forbidden".equals(action))
            return badCategorySet().equals(other.badCategorySet());
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), action);
    }

    @Override
    public String getActions() {
        return action;
    }

    private Set<String> badCategorySet() {
        Set<String> set = new HashSet<String>();
        set.addAll(Arrays.asList(getName().split(",")));
        return set;
    }
}
