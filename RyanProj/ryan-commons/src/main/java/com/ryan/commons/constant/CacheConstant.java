package com.ryan.commons.constant;

/**
 * Created by bin on 2015/6/23.
 */
public class CacheConstant {
    public final static String COURIER_USER_KEY_PREFIX = "courierBean_%d";                                          //  courier bean redis key 值
    public final static String COURIER_SESSION_KEY_PREFIX = "courierSession_%s";                                    //  courier session key 值
    public final static String SMS_VALIDATE_KEY = "courierSmsValidate%s_%s_%s";                                     //  sms验证码key 值
    public final static String SMS_BIND_PHONE_KEY = "BindPhone";                                                    //  绑定手机sms验证码key 值
    public final static String SMS_TEMPLATE_KEY = "BindPhone_%d";                                                   //  验证码模板 key 值
    public final static String USER_GPS_KEY = "userGps_%s_%s";                                                      //  用户在缓存中的Gps  (网点代码、工号)
    public final static String USER_GPS_STACK_KEY = "userGpsStack_%s_%s";                                           //  用户在缓存中的Gps堆栈 存放多条数据
    public final static String CITIES_USER_GPS_RELATIONSHIP_KEY = "cities_user_gps_relationship_%s";                //  省市关系key值  return:Set<String> string 为用户最新经纬度的key
    public final static String API_TOKEN_VALUES_KEY = "apiTokenValues";                                             //  三方系统key值  return:string
    public final static String PROVINCES_CITIES_RELATIONSHIP_KEY = "provinces_cities_relationship_%s";              //  省市关系key值  return:Set<String>

    public final static String COLLECTION_ORDER_KEY = "collection_order_key_%d";                                    //  收件的redis key值:collection_order_key_courierId  value值 : 快件Bean对象
    public final static String COLLECTION_ORDER_CITY_KEY = "collection_order_city_key_%s";                          //  城市收件的redis key值:collection_order_city_key_cityCode value值 : 所在城市的收件的list
    public final static String COLLECTION_ORDER_PROVINCE_KEY = "collection_order_province_key_%s";                  //  省市的
    public final static String COLLECTION_ORDER_TIMESTEMP_KEY = "collection_order_timestemp_key";                 //  计时
    public final static String COLLECTION_ORDER_UNROBS_EY = "collection_order_unrobs_key_%s";                       // 用户未抢单
    public final static String ALL_VORDER_LIST_LEY = "all_vOrder_list_%d";                                          //  单个用户全部派件、揽件列表（排序后）param1:uId  value值：List<VOrder>
    public final static String DELIVERY_VORDER_LIST_LEY = "delivery_vOrder_list_%d";                                //  单个用户全部派件列表（排序后）param1:uId  value值：List<VOrder>
    public final static String COLLECT_VORDER_LIST_LEY = "collect_vOrder_list_%d";                                  //  单个用户全部揽件列表（排序后）param1:uId  value值：List<VOrder>
    public final static String HOME_PAGE_DATA_KEY = "home_page_data_%d";                                            //  首页数据
    public final static String YTO_PAGE_DATA_KEY = "yto_page_data_%d";                                              //  我的圆通数据

    public final static String PUSH_FILTER_KEY = "push_filter_key_%d";
    public final static String ONLINE_USER_COURIER = "online_user_courier";                                         //在线用户
    public final static String FETCH_ORDER_LIST = "fetch_order_list";                                               //抢单信息

    public final static String REGION_CODE_NAME = "region_code_name_%s";                                            //region的code与name的键值对
    public final static String REGION_NAME_CODE = "region_name_code_%s";                                            //region的name与code的键值对

}
