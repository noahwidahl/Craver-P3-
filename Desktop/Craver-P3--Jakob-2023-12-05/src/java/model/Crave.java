
package model;

public class Crave {
    //Declaring, close relation to DB table names, doesnt have too.
    private int RoleID;
    private String RoleDescription;
    
    //Default constructor    
    public Crave() {
        this.RoleID = 0;
        this.RoleDescription = "";
    }
        
    //Overload contructor    
    public Crave(int RoleID, String RoleDescription) {
        this.RoleID = RoleID;
        this.RoleDescription = RoleDescription;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getRoleDescription() {
        return RoleDescription;
    }

    public void setRoleDescription(String RoleDescription) {
        this.RoleDescription = RoleDescription;
    }

    @Override
    public String toString() {
        return "Crave{" + "RoleID=" + RoleID + ", RoleDescription=" + RoleDescription + '}';
    }
        
        
}
