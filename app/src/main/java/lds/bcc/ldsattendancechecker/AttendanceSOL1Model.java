package lds.bcc.ldsattendancechecker;

public class AttendanceSOL1Model{
        private long id;

        public String class_week;
        public String student_number;
        public String student_status;
        public String student_time;

        public AttendanceSOL1Model(String class_week, String student_number, String student_status, String student_time) {
            this.class_week = class_week;
            this.student_number = student_number;
            this.student_status = student_status;
            this.student_time = student_time;
        }

        public AttendanceSOL1Model() {
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
}

