package lds.bcc.ldsattendancechecker;

public class AttendanceLifeclassModel{
        private long id;

        public String class_week;
        public String student_number;
        public String student_status;
        public String student_time;
        public String student_name;
        public String student_nickname;
        public String student_leader;
        public String student_network;

        public AttendanceLifeclassModel(String class_week, String student_number, String student_status, String student_time,
                                        String student_name, String student_nickname, String student_leader, String student_network) {
            this.class_week = class_week;
            this.student_number = student_number;
            this.student_status = student_status;
            this.student_time = student_time;
            this.student_name = student_name;
            this.student_nickname = student_nickname;
            this.student_leader = student_leader;
            this.student_network = student_network;
        }

        public AttendanceLifeclassModel() {
        }

        public String getClass_week() {
            return class_week;
        }

        public String getStudent_number() {
            return student_number;
        }

        public String getStudent_status() {
            return student_status;
        }

        public String getStudent_time() {
        return student_time;
    }

        public void setClass_week(String string) {
            this.class_week = string;
        }

        public void setStudent_number(String string) {
            this.student_number = string;
        }

        public void setStudent_status(String string) {
            this.student_status = string;
        }

        public void setStudent_time(String string) {
             this.student_time = string;
        }

        public String getStudent_name() {
            return student_name;
        }

        public String getStudent_nickname() {
            return student_nickname;
        }

        public String getStudent_leader() {
            return student_leader;
        }

        public String getStudent_network() {
            return student_network;
        }

        public void setStudent_name(String name) {
            this.student_name = name;
        }

        public void setStudent_nickname(String nickname) {
            this.student_nickname = nickname;
        }

        public void setStudent_leader(String leader) {
            this.student_leader = leader;
        }

        public void setStudent_network(String network) {
            this.student_network = network;
        }
}

