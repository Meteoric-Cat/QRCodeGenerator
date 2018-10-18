/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import org.json.simple.JSONObject;

/**
 *
 * @author Meteoric
 */
public class Convertor {
    private static Convertor instance = new Convertor();
    
    private final String SERVER_DM_KEY = "ServerDomainName";
    private final String PRODUCT_ID_KEY = "ProductID";
    private final String PRODUCT_NAME_KEY = "ProductName";
    private final String MANUFACTURER_NAME_KEY = "ManufacturerName";
    private final String M_DATE_KEY = "ManufacturingDay";
    private final String E_DATE_KEY = "ExpirationDay";
    private final String STATE_KEY = "ProductState";

    private Convertor(){
    }
    
    public static Convertor getInstance() {
        return instance;
    }
    
    public String[] convertProductToArray(JSONObject productInfo) {
        String[] result = new String[6];
        result[0] = (String) productInfo.get(PRODUCT_ID_KEY);
        result[1] = (String) productInfo.get(PRODUCT_NAME_KEY);
        result[2] = (String) productInfo.get(MANUFACTURER_NAME_KEY);
        result[3] = (String) productInfo.get(M_DATE_KEY);
        result[4] = (String) productInfo.get(E_DATE_KEY);
        result[5] = (String) productInfo.get(STATE_KEY);        
        return result;        
    }
    
    public JSONObject convertProductToJSON(String... data) {
        if (data.length < 6) {
            return null;
        }
        
        JSONObject result = new JSONObject();
        result.put(SERVER_DM_KEY, data[0]);
        result.put(PRODUCT_ID_KEY, data[1]);
        result.put(PRODUCT_NAME_KEY, data[2]);
        result.put(MANUFACTURER_NAME_KEY, data[3]);
        result.put(M_DATE_KEY, data[4]);
        result.put(E_DATE_KEY, data[5]);
        return result;        
    }    
}
