package com.software.beacon;

/**
 * Created by LENOVO on 25-04-2017.
 */

public class URLS {
   /* static String Query_URL = "http://aishwary.heliohost.org/fetch_result1.php";
    static String SignUp_URL = "http://aishwary.heliohost.org/signup.php";
    static String Login_URL = "http://aishwary.heliohost.org/login.php";
    static String Fetch_Product_URL = "http://ourbeaconproject.heliohost.org/get_result.php";
    static String Fetch_Cart_URL = "http://ourbeaconproject.heliohost.org/get_multiple_result.php";
    static String User_Cart = "http://ourbeaconproject.heliohost.org/update_into_cart.php";
    static String Remove_From_Cart_URL = "http://ourbeaconproject.heliohost.org/remove_from_cart.php";*/

    static String my_url = "https://smart-beacon.herokuapp.com/";
    static String SignUp_URL = my_url + "signup.php";
    static String Login_URL = my_url + "login.php";
    static String Fetch_Product_URL = my_url + "get_result.php";
    static String Fetch_Cart_URL = my_url + "get_multiple_result.php";
    static String User_Cart = my_url + "update_into_cart.php";
    static String Remove_From_Cart_URL = my_url + "remove_from_cart.php";
    static String Write_Feedback_URL = my_url + "write_feedback.php";

    static String Local = my_url + "images/";

    static int mCartItemCount = 0;
    static boolean spinnerFlag = false;
}
