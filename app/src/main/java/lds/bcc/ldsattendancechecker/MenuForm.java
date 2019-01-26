package lds.bcc.ldsattendancechecker;

public class MenuForm {

    private int photoid;
    private String menuName;


    public MenuForm() {
    }


    public MenuForm(String menuName, int photoid) {

        this.menuName = menuName;
        this.photoid = photoid;

    }


    public int getPhotoid() {
        return photoid;
    }

    public String getMenuName() {
        return menuName;
    }

}