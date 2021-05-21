package Server;

public class BaseURL {

        public static String baseUrl = "http://192.168.43.229:10/";


        //input history
        public static String inputHistory = baseUrl + "history/input";
        public static String hapusHistory = baseUrl + "history/hapus/";
        public static String history = baseUrl + "perintah/lihat-history/";

        //users
        public static String registrasi = baseUrl + "user/registrasi";
        public static String MenuLogin = baseUrl + "user/login";
        public static String konfirmasiSandi = baseUrl + "user/konfirm-sandi";
        public static String controlling = baseUrl + "perintah/update-perintah/";
}

