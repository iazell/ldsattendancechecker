package lds.bcc.ldsattendancechecker;

class StudentModel {

    private long id;

    public String student_number;
    public String student_name;
    public String student_nickname;
    public String student_birthdate;
    public String student_contact;
    public String student_leader;
    public String student_contactleader;
    public String student_network;
    public String student_class;


    public StudentModel(String student_number, String student_name,
                        String student_nickname, String student_birthdate, String student_contact,
                        String student_leader, String student_contactleader, String student_network,
                        String student_class ) {
            this.student_number = student_number;
            this.student_name = student_name;
            this.student_nickname = student_nickname;
            this.student_birthdate = student_birthdate;
            this.student_contact = student_contact;
            this.student_leader = student_leader;
            this.student_contactleader = student_contactleader;
            this.student_network = student_network;
            this.student_class = student_class;
    }

    public StudentModel() {
    }

    public String getStudent_number() {
        return student_number;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_nickname() {
        return student_nickname;
    }

    public String getStudent_birthdate() {
        return student_birthdate;
    }

    public String getStudent_contact() {
        return student_contact;
    }

    public String getStudent_leader() {
        return student_leader;
    }

    public String getStudent_contactleader() {
        return student_contactleader;
    }

    public String getStudent_network() {
        return student_network;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_number(String string) {
    }

    public void setStudent_name(String string) {
    }

    public void setStudent_nickname(String string) {
    }

    public void setStudent_birthdate(String string) {
    }

    public void setStudent_contact(boolean b) {
    }

    public void setStudent_leader(String string) {
    }

    public void setStudent_contactleader(String string) {
    }

    public void setStudent_network(String string) {
    }

    public void setStudent_class(String string) {
    }
}
