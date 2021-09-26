package education.vt.elearning;

public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = "education.vt.elearning.action.main";
        public static String STARTFOREGROUND_ACTION = "education.vt.elearning.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "education.vt.elearning.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}